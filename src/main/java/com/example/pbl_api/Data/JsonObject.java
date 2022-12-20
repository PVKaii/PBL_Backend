package com.example.pbl_api.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JsonObject {

    private String name;

    private List<JsonDetailObject> products;

    public JsonObject(String name_product_type, List<JsonDetailObject> products) {
        this.name = name_product_type;
        this.products = products;
    }

    public String getName_product_type() {
        return name;
    }

    public void setName_product_type(String name_product_type) {
        this.name = name_product_type;
    }

    public List<JsonDetailObject> getProducts() {
        return products;
    }

    public void setProducts(List<JsonDetailObject> products) {
        this.products = products;
    }
}
