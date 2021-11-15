package ru.online.shop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer stock;

    @Column(length = 80)
    private String name;

    private String description;

    private Integer prise;

    @OneToMany()
    private List<Category> categories;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;


    public Product(Integer stock, String name, String description, Integer prise) {

        this.stock = stock;
        this.name = name;
        this.description = description;
        this.prise = prise;
    }


    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer amountInStock) {
        this.stock = amountInStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrise() {
        return prise;
    }

    public void setPrise(Integer prise) {
        this.prise = prise;
    }

    public Integer getId() {

        return id;
    }

    public Product() {

    }
}
