package ru.online.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.online.shop.config.CustomEntityManager;
import ru.online.shop.entity.Category;
import ru.online.shop.entity.Product;
import ru.online.shop.entity.ProductCategory;
import ru.online.shop.repositoriy.ProductCategoryRepository;
import ru.online.shop.repositoriy.ProductRepository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository, ProductRepository productRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
    }


    public ProductCategory read(Integer id) {
        return productCategoryRepository.getById(id);
    }

    public List<Product> getProductListByCategoryId(Integer categoryId) {
        javax.persistence.EntityManager manager = CustomEntityManager.getEntityManager();
        Query qr = manager.createNativeQuery("select * from product_categories where categories_id =" + categoryId, ProductCategory.class);
        List<ProductCategory> productCategoryList = new ArrayList<>();

        for (Object pr : qr.getResultList()) {
            productCategoryList.add((ProductCategory) pr);
        }

        return productCategoryList.stream().map(productCategory -> productCategory.getProduct()).collect(Collectors.toList());
    }


    public void createProductCategoryRelation(Product product, Category category) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProduct(product);
        productCategory.setCategory(category);
    }
}
