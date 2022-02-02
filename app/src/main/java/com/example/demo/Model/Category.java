package com.example.demo.Model;

import java.io.Serializable;

public class Category implements Serializable {
    String category;
    String quotes;
    String imageURl;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public Category(String quotes) {
        this.quotes = quotes;
    }

    public Category(String category, String imageURl) {
        this.category = category;
        this.imageURl = imageURl;
    }
}
