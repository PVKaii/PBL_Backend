package com.example.pbl_api.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "total")
    private double total;

    @Column(name = "day")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate day;

    @Column(name = "type")
    private boolean type;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private UserOrder userOrder;

    @OneToMany(mappedBy = "bill",cascade = CascadeType.REMOVE)
    private Set<BillDetail> billDetailSet;

    public Bill(double total, LocalDate day, UserOrder userOrder, boolean type) {
        this.total = total;
        this.day = day;
        this.userOrder = userOrder;
        this.type=type;
    }

    public Bill() {

    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
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

    public UserOrder getOrder() {
        return userOrder;
    }

    public void setOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public Set<BillDetail> getBillDetailSet() {
        return billDetailSet;
    }

    public void setBillDetailSet(Set<BillDetail> billDetailSet) {
        this.billDetailSet = billDetailSet;
    }
}
