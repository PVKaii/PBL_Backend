package com.example.pbl_api.model;

import com.example.pbl_api.entity.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class UserOrderModel {

    private long id;


    private LocalDate dayOrder;


    private UserModel user;

    private BillModel bill;

    private OrderStatusModel orderStatus;

    private List<OrderDetailModel> orderDetailSet;
    private String payment;


    public UserOrderModel() {
    }

    public UserOrderModel(UserOrder userOrder) {
        this.id = userOrder.getId();
        this.dayOrder = userOrder.getDayOrder();
        this.user = new UserModel(userOrder.getUser());
        this.bill = new BillModel(userOrder.getBill());
        this.orderStatus = new OrderStatusModel(userOrder.getOrderStatus());
        this.orderDetailSet = userOrder.getOrderDetailSet().stream()
                .map(orderDetail -> new OrderDetailModel(orderDetail)).toList();
        this.payment = userOrder.getPayment();
    }

    public UserOrderModel(UserOrder userOrder,Bill bill,List<OrderDetail> orderDetailSet,String payment) {
        this.id = userOrder.getId();
        this.dayOrder = userOrder.getDayOrder();
        this.user = new UserModel(userOrder.getUser());
        this.bill = new BillModel(bill.getId(),bill.getTotal(),bill.getDay(),bill.isType());
        this.orderStatus = new OrderStatusModel(userOrder.getOrderStatus());
        this.orderDetailSet = orderDetailSet.stream()
                .map(orderDetail -> new OrderDetailModel(orderDetail)).toList();
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public BillModel getBill() {
        return bill;
    }

    public void setBill(BillModel bill) {
        this.bill = bill;
    }

    public OrderStatusModel getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusModel orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetailModel> getOrderDetailSet() {
        return orderDetailSet;
    }

    public void setOrderDetailSet(List<OrderDetailModel> orderDetailSet) {
        this.orderDetailSet = orderDetailSet;
    }
}
