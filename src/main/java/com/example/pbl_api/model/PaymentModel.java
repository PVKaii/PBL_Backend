package com.example.pbl_api.model;

import java.util.List;
import java.util.UUID;

public class PaymentModel {
    private String id;
    private long amount;
    private String orderInfor;
    private List<Long> listCart;

    private int userId;

    public PaymentModel(long amount, String orderInfor, List<Long> listCart,int id) {
        this.amount = amount;
        this.orderInfor = orderInfor;
        this.listCart= listCart;
        this.userId=id;
    }

    public PaymentModel() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getOrderInfor() {
        return orderInfor;
    }

    public void setOrderInfor(String orderInfor) {
        this.orderInfor = orderInfor;
    }

    public List<Long> getListCart() {
        return listCart;
    }

    public void setListCart(List<Long> listCart) {
        this.listCart = listCart;
    }

    public void createId(){
        UUID id = UUID.randomUUID();
        this.id="1111";
    }
}
