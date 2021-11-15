package ru.online.shop.entity;

import javax.persistence.*;

@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 80)
    private String userName;

    private String text;

    private Integer rate;

    public Integer getId() {
        return id;
    }

    public Comment(String userName, String text, Integer rate) {
        this.userName = userName;
        this.text = text;
        this.rate = rate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Comment() {

    }
}
