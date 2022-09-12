package com.example.pbl_api.model;

import com.example.pbl_api.entity.Product;

import java.util.List;
import java.util.Set;

public class ProductModel {
    private long id;
    private String name;
    private String information;
    private String description;
    private double price;
    private Boolean status;
    private long popular;
    private double rate;
    private String brand;
    private String Category;
    private List<String> attributes;

    public ProductModel(Product product) {
        this.id=product.getId();
        this.name=product.getName();
        this.information=product.getInformation();
        this.price=product.getPrice();
        this.status=product.getStatus();
        this.popular=product.getPopular();
        this.rate=product.getRate();
        this.Category=product.getCategory().getName();
        this.description=product.getDescription();
        this.attributes=product.getAttributesSet().stream().map(attributes1 -> attributes1.getName()).toList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public long getPopular() {
        return popular;
    }

    public void setPopular(long popular) {
        this.popular = popular;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
