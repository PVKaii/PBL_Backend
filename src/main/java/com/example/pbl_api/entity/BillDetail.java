package com.example.pbl_api.entity;


import javax.persistence.*;

@Entity
@Table(name = "bill_detail")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "total_payable")
    private double totalPayable;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "bill_id",nullable = false)
    private Bill bill;


    public BillDetail(Product product, Bill bill,int amount,double totalPayable) {
        this.product = product;
        this.bill = bill;
        this.amount=amount;
        this.totalPayable=totalPayable;
    }

    public BillDetail() {

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
