package com.example.pbl_api.model;

import java.util.UUID;

public class PaymentModel {
    private String id;
    private long amount;
    private String orderInfor;
    private String bankCode;

    public PaymentModel(long amount, String orderInfor, String bankCode) {
        this.amount = amount;
        this.orderInfor = orderInfor;
        this.bankCode = bankCode;
    }

    public PaymentModel() {
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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void createId(){
        UUID id = UUID.randomUUID();
        this.id="VNPay1";
    }
}
