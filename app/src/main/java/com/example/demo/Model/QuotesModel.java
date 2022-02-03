package com.example.demo.Model;

public class QuotesModel {
    String text;
    String author;
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



    public QuotesModel(String text, String author) {
        this.text = text;
        this.author = author;
    }
}
