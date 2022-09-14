package com.example.pbl_api.model;

import com.example.pbl_api.entity.Category;

public class CategoryModel {
    private int id;
    private String name;
    private SellerCategoryModel sellerCategory;

    public CategoryModel(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.sellerCategory = new SellerCategoryModel(category.getSellerCategory());
    }

    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SellerCategoryModel getSellerCategory() {
        return sellerCategory;
    }

    public void setSellerCategory(SellerCategoryModel sellerCategory) {
        this.sellerCategory = sellerCategory;
    }
}
