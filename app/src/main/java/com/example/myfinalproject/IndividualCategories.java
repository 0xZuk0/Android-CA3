package com.example.myfinalproject;

public class IndividualCategories {
    private String categoryName;
    private int categoryImage;

    public IndividualCategories(String name, int image) {
        this.categoryName = name;
        this.categoryImage = image;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public int getCategoryImage() {
        return this.categoryImage;
    }
}
