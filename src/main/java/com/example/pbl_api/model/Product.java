package com.example.pbl_api.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "status",nullable = false)
    private Boolean status;

    @Column(name = "description")
    private String descreption;

    @Column(name = "popular")
    private long popular ;

    @Column(name = "rate")
    private double rate;

    @ManyToOne
    @JoinColumn(name = "seller_Category",nullable = false)
    private SellerCategory sellerCategory;

    @ManyToOne
    @JoinColumn(name = "brand",nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "warranty",nullable = false)
    private Warranty warranty;

    @ManyToMany
    @JoinTable(
            name = "product_attributes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attributes_id")
    )
    private Set<Attributes> attributesSet;

}
