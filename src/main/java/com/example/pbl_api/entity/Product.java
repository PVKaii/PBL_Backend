package com.example.pbl_api.entity;

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


    @Column(name = "information")
    private String information;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "status",nullable = false)
    private Boolean status;

    @Column(name = "description")
    private String description;


    @Column(name = "popular")
    private long popular ;

    @Column(name = "rate")
    private double rate;

    @ManyToOne
    @JoinColumn(name = "seller_Category")
    private SellerCategory sellerCategory;

    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "warranty")
    private Warranty warranty;

    @ManyToMany
    @JoinTable(
            name = "product_attributes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attributes_id")
    )
    private Set<Attributes> attributesSet;



    public Product() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPopular() {
        return popular;
    }

    public void setPopular(long popular) {
        this.popular = popular;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public SellerCategory getSellerCategory() {
        return sellerCategory;
    }

    public void setSellerCategory(SellerCategory sellerCategory) {
        this.sellerCategory = sellerCategory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }

    public Set<Attributes> getAttributesSet() {
        return attributesSet;
    }

    public void setAttributesSet(Set<Attributes> attributesSet) {
        this.attributesSet = attributesSet;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }


}
