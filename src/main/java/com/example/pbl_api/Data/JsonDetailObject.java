package com.example.pbl_api.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JsonDetailObject {

    private String name;

    private String price;

    private List<String> image;

    private String description;

    private List<List<String>> descriptionDetail;

    public JsonDetailObject(String name, String price, List<String> image, String description, List<List<String>> descriptionDetail) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.descriptionDetail = descriptionDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<List<String>> getDescription_detail() {
        return descriptionDetail;
    }

    public void setDescription_detail(List<List<String>> descriptionDetail) {
        this.descriptionDetail = descriptionDetail;
    }
}
