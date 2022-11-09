package com.example.pbl_api.model;

import com.example.pbl_api.entity.Bill;
import com.example.pbl_api.entity.BillDetail;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class BillModel {
    private long id;

    private double total;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate day;

    private UserModel user;

    private boolean type;

    private List<BillDetailModel> billDetails;

    public BillModel() {
    }

    public BillModel(Bill bill) {
        this.id = bill.getId();
        this.total = bill.getTotal();
        this.day = bill.getDay();
        this.type=bill.isType();
        this.user = new UserModel(bill.getOrder().getUser());
        this.billDetails=bill.getBillDetailSet().stream()
                .map(billDetail -> new BillDetailModel(billDetail)).toList();
    }

    public BillModel(long id, double total, LocalDate day, boolean type) {
        this.id = id;
        this.total = total;
        this.day = day;
        this.type = type;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
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

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
