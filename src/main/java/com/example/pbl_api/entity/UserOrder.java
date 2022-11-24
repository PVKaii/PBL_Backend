package com.example.pbl_api.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user_order")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "day_order")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate dayOrder;

    @Column(name = "payment")
    private String payment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "userOrder")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "userOrder",cascade = CascadeType.REMOVE)
    private Set<OrderDetail> orderDetailSet;


    public UserOrder(LocalDate dayOrder, User user, OrderStatus orderStatus,String payment) {
        this.dayOrder = dayOrder;
        this.user = user;
        this.orderStatus = orderStatus;
        this.payment=payment;
    }

    public UserOrder() {

    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDayOrder() {
        return dayOrder;
    }

    public void setDayOrder(LocalDate dayOrder) {
        this.dayOrder = dayOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderDetail> getOrderDetailSet() {
        return orderDetailSet;
    }

    public void setOrderDetailSet(Set<OrderDetail> orderDetailSet) {
        this.orderDetailSet = orderDetailSet;
    }
}
