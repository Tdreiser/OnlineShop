package ru.online.shop.service;

import org.springframework.stereotype.Service;
import ru.online.shop.entity.Comment;
import ru.online.shop.entity.Product;
import ru.online.shop.entity.ProductComment;
import ru.online.shop.repositoriy.CommentRepository;
import ru.online.shop.repositoriy.ProductCommentRepository;
import ru.online.shop.repositoriy.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCommentService {
    private final ProductCommentRepository productCommentRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    public ProductCommentService(ProductCommentRepository productCommentRepository, ProductRepository productRepository, CommentRepository commentRepository) {
        this.productCommentRepository = productCommentRepository;
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
    }

    public ProductComment getProductCommentRelation(Integer id) {
        return productCommentRepository.getById(id);
    }

    public void createProductCommentRelation(Integer productId, Integer commentId) {
        Product product = productRepository.getById(productId);
        Comment comment = commentRepository.getById(commentId);
        ProductComment productComment = new ProductComment(product, comment);
        productCommentRepository.save(productComment);

    }

    public Integer countValueByProductId(Integer id) {
        return productCommentRepository.countByProductId(id);
    }


    public List<Integer> getCommentIdsByProductId(Integer id) {

        List<Integer> idList = productCommentRepository.getProductCommentsByProductId(id).stream()
                .map(productComment -> productComment.getComments().getId()).collect(Collectors.toList());
        return idList;

    }
}
