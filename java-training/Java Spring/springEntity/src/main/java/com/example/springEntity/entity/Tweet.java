package com.example.springEntity.entity;

import java.io.Serializable;

public class Tweet implements Serializable {
    private String text;
    private String author;

    public Tweet(){};

    public Tweet(String text, String author) {
        this.text = text;
        this.author = author;
    }

    // getters and setters

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
