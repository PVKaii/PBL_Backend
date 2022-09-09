package com.example.pbl_api.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "seller_category_id",nullable = false)
    private SellerCategory sellerCategory;

    @OneToMany(mappedBy = "category")
    private Set<OptionGroup> optionGroupSet;

    public Category() {
    }

    public Category(String name, SellerCategory sellerCategory) {
        this.name = name;
        this.sellerCategory = sellerCategory;
    }
}
