package com.example.pbl_api.model;

import com.example.pbl_api.entity.Bill;
import com.example.pbl_api.entity.BillDetail;

import java.util.Date;
import java.util.List;

public class BillModel {
    private long id;

    private double total;

    private Date day;

    private UserModel user;

    private List<BillDetailModel> billDetails;

    public BillModel() {
    }

    public BillModel(Bill bill) {
        this.id = bill.getId();
        this.total = bill.getTotal();
        this.day = bill.getDay();
        this.user = new UserModel(bill.getUser());
        this.billDetails=bill.getBillDetailSet().stream()
                .map(billDetail -> new BillDetailModel(billDetail)).toList();
    }



    public List<BillDetailModel> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetailModel> billDetails) {
        this.billDetails = billDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
