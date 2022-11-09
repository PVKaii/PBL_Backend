package com.example.pbl_api.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order_status")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "orderStatus")
    private Set<UserOrder> userOrderSet;

    public OrderStatus() {
    }

    public OrderStatus(int id) {
        this.id = id;
    }

    public OrderStatus(String name) {
        this.name = name;
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

    public Set<UserOrder> getOrderSet() {
        return userOrderSet;
    }

    public void setOrderSet(Set<UserOrder> userOrderSet) {
        this.userOrderSet = userOrderSet;
    }
}
