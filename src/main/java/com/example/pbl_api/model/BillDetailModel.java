package com.example.pbl_api.model;

import com.example.pbl_api.entity.Bill;
import com.example.pbl_api.entity.BillDetail;
import com.example.pbl_api.entity.Product;

import javax.persistence.*;

public class BillDetailModel {

    private long id;

    private OrderDetailModel orderDetail;

    private double totalPayable;



    public BillDetailModel(BillDetail billDetail) {
        this.id = billDetail.getId();
        this.orderDetail = new OrderDetailModel(billDetail.getOrderDetail());
        this.totalPayable=billDetail.getTotalPayable();
    }

    public OrderDetailModel getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailModel orderDetail) {
        this.orderDetail = orderDetail;
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



}
