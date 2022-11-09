package com.example.pbl_api.model;

import com.example.pbl_api.entity.BillDetail;
import com.example.pbl_api.entity.OrderDetail;
import com.example.pbl_api.entity.Product;
import com.example.pbl_api.entity.UserOrder;

import javax.persistence.*;

public class OrderDetailModel {

    private long id;

    private int amount;

    private ProductModel product;



    public OrderDetailModel(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.amount = orderDetail.getAmount();
        this.product = new ProductModel(orderDetail.getProduct());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }


}
