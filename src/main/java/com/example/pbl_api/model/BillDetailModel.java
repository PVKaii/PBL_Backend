package com.example.pbl_api.model;

import com.example.pbl_api.entity.Bill;
import com.example.pbl_api.entity.BillDetail;
import com.example.pbl_api.entity.Product;

import javax.persistence.*;

public class BillDetailModel {

    private long id;

    private ProductModel product;

    private int amount;

    private double totalPayable;



    public BillDetailModel(BillDetail billDetail) {
        this.id = billDetail.getId();
        this.product = new ProductModel(billDetail.getProduct());
        this.amount= billDetail.getAmount();
        this.totalPayable=billDetail.getTotalPayable();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(double totalPayable) {
        this.totalPayable = totalPayable;
    }

    public BillDetailModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }


}
