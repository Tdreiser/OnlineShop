package ru.online.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.online.shop.config.DraftCurrencyConverter;
import ru.online.shop.entity.Comment;
import ru.online.shop.entity.Product;
import ru.online.shop.service.*;

import javax.management.BadAttributeValueExpException;
import java.util.*;
import java.util.stream.Collectors;


@RestController
public class Controller {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final CommentService commentService;
    private final ProductCategoryService productCategoryService;
    private final ProductCommentService productCommentService;
    private final DraftCurrencyConverter draftCurrencyConverter;

    @Autowired
    public Controller(CategoryService categoryService, ProductService productService, CommentService commentService, ProductCategoryService productCategoryService, ProductCommentService productCommentService, DraftCurrencyConverter draftCurrencyConverter) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.commentService = commentService;
        this.productCategoryService = productCategoryService;
        this.productCommentService = productCommentService;
        this.draftCurrencyConverter = draftCurrencyConverter;
    }

    @PostMapping("/product/{id}/comment")//todo: Добавить в табличку ProductComment id Comment / id Product
    public ResponseEntity<?> saveComment(@PathVariable("id") Integer productId,
                                         @RequestParam("user_name") String userName,
                                         @RequestParam("text") String text,
                                         @RequestParam("rate") Integer rate) throws BadAttributeValueExpException {
        //Провалидируем хотя бы рейтинг,который должен быть в пределах от 0-5
        try {
            if (rate > 5 || rate < 0) {
                throw new BadAttributeValueExpException("Рейтинг должен быть в пределах от 0 до 5");
            }
        } catch (BadAttributeValueExpException er) {
            er.printStackTrace();
            //Сразу выходим при не соовтествии
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Comment comment = new Comment(userName, text, rate);
        commentService.saveComment(comment);
        productCommentService.createProductCommentRelation(productId, comment.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/products")//Todo: Если будет время перенести уменьшеть код.
    public ResponseEntity<?> getProductsByCategoryWithCurrency(@RequestParam(value = "categories") List<String> categories,
                                                               @RequestParam(value = "currency") String currency) {


        //Собираем Товары по соотвествующим категориям в двумерный список List<List<Product>>
        List<List<Product>> productLists = categories.stream()
                .map(name -> categoryService.getCategoryByName(name))
                .map(category -> category.getId())
                .map(categoryId -> productCategoryService.getProductListByCategoryId(categoryId))
                .collect(Collectors.toList());

        Map<Map<String, Integer>, String> mapProductNamePriceRate = new HashMap<>();
        Map<String, Integer> mapProductNamePrice = new HashMap<>();
        //Проходим по спискам тянем из них id Product и из связи ProductComment считаем средний рейтинг по коментариям
        for (List<Product> productList : productLists) {
            for (Product product : productList) {

                Integer summaryRate = productCommentService.getCommentIdsByProductId(product.getId())
                        .stream()
                        .map(commentId -> commentService.getCommentById(commentId))
                        .map(comment -> comment.getRate())
                        .reduce(0, (a, b) -> a + b);

                //собираем в Map значения {{Имя_товара: стоимость}:средний рейтинг}
                mapProductNamePrice.put(product.getName(), product.getPrise());

                //float rate = (float) summaryRate /(float) productCommentService.countValueByProductId(product.getId());
                String formatedRate = String.format("%.2f.", 0,111);//форматирование среднего рейтинга

                mapProductNamePriceRate.put(mapProductNamePrice, formatedRate);
            }
            String currency1= draftCurrencyConverter.convertRawCurrency(currency);
        }
        return new ResponseEntity<>(mapProductNamePriceRate, HttpStatus.OK);
    }

    //покупка(уменьшение на складе) товара с указанным id на указанную величину
    @PutMapping("/product/{id}/buy")
    public ResponseEntity<?> reduceProductValueInStock(@PathVariable("id") Integer productId,
                                                       @RequestParam("number_of_purchases") Integer numberOfPurchases) {
        return productService.updateProductStockValue(productId, numberOfPurchases)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
