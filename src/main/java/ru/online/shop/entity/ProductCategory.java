package ru.online.shop.entity;

import javax.persistence.*;

//сгенерировал энтити автоматически
@Table(name = "product_categories", indexes = {
        @Index(name = "uk_9e3hqm0qh65p3kklc0bstubya", columnList = "categories_id", unique = true)
})
@Entity
public class ProductCategory {
    //Проблема: Hibernate генерирует Unique key на поле categories_id.
    //Решить можно только если БД будет создаваться скриптом?
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    @ManyToOne(optional = false)
    @JoinColumn(name = "categories_id", nullable = false, unique = false)
    private Category categories;

    public Category getCategory() {
        return categories;
    }

    public void setCategory(Category categories) {
        this.categories = categories;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}