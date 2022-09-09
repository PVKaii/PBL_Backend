package com.example.pbl_api.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "seller_category")
public class SellerCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sellerCategory")
    private Set<Category> categorySet;

    @OneToMany(mappedBy = "sellerCategory")
    private Set<Product> productSet;

    public SellerCategory(String name) {
        this.name = name;
    }

    public SellerCategory() {

    }
}
