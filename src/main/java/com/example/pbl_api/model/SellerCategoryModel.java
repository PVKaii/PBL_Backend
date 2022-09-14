package com.example.pbl_api.model;

import com.example.pbl_api.entity.Category;
import com.example.pbl_api.entity.SellerCategory;

import java.util.List;
import java.util.Set;

public class SellerCategoryModel {
    private int id;
    private String name;

    private List<CategoryModel> category;

    public SellerCategoryModel(SellerCategory sellerCategory) {
        this.id = sellerCategory.getId();
        this.name = sellerCategory.getName();
    }

    public SellerCategoryModel() {
    }

    public SellerCategoryModel(SellerCategory sellerCategory, Set<Category> categorySet) {
        this.id = sellerCategory.getId();
        this.name = sellerCategory.getName();
        this.category=categorySet.stream().map(category -> new CategoryModel(category.getId(),category.getName())).toList();
    }


    public List<CategoryModel> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryModel> category) {
        this.category = category;
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
}
