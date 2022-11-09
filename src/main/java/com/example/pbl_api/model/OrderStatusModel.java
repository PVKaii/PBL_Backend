package com.example.pbl_api.model;

import com.example.pbl_api.entity.OrderStatus;

import java.util.List;

public class OrderStatusModel {
    private int id;

    private String name;




    public OrderStatusModel(int id) {
        this.id = id;
    }

    public OrderStatusModel(String name) {
        this.name = name;
    }

    public OrderStatusModel(OrderStatus orderStatus) {
        this.id = orderStatus.getId();
        this.name = orderStatus.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
