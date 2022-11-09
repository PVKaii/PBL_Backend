package com.example.pbl_api.entity;


import javax.persistence.*;

@Entity
@Table(name = "bill_detail")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "total_payable")
    private double totalPayable;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id",referencedColumnName = "id")
    private OrderDetail orderDetail;


    @ManyToOne
    @JoinColumn(name = "bill_id",nullable = false)
    private Bill bill;


    public BillDetail(Bill bill,double totalPayable,OrderDetail orderDetail) {
        this.bill = bill;
        this.totalPayable=totalPayable;
        this.orderDetail=orderDetail;
    }

    public BillDetail() {

    }


    public double getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(double totalPayable) {
        this.totalPayable = totalPayable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
