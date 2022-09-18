package com.example.pbl_api.model;

import com.example.pbl_api.entity.Bill;
import com.example.pbl_api.entity.BillDetail;
import com.example.pbl_api.entity.Product;

import javax.persistence.*;

public class BillDetailModel {

    private long id;

    private ProductModel product;



    public BillDetailModel(BillDetail billDetail) {
        this.id = billDetail.getId();
        this.product = new ProductModel(billDetail.getProduct());
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
