package ru.online.shop.entity;

import javax.persistence.*;

// Сгенерировал энтити автоматически
@Table(name = "product_comments")
@Entity
public class ProductComment {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "comments_id")
    private Comment comments;

    public ProductComment() {
    }

    public ProductComment(Product product, Comment comments) {
        this.product = product;
        this.comments = comments;
    }

    public Comment getComments() {
        return comments;
    }

    public void setComments(Comment comments) {
        this.comments = comments;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}