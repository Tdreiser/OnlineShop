package ru.online.shop.entity;

import javax.persistence.*;


@Entity
@Table
public class Category {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 80)
    private String name;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
