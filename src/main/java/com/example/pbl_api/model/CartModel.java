package com.example.pbl_api.model;

import com.example.pbl_api.entity.Cart;

public class CartModel {
    private long id;
    private UserModel user;
    private ProductModel product;

    private int amount;


    public CartModel(Cart cart) {
        this.id = cart.getId();
        this.user = new UserModel(cart.getUser());
        this.product = new ProductModel(cart.getProduct());
        this.amount= cart.getAmount();
    }



    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

//    public long getId() {
//        return id;
//    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
