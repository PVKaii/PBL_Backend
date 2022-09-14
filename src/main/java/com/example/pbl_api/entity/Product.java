package com.example.pbl_api.entity;

import com.example.pbl_api.model.ProductModel;

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
    @JoinColumn(name = "category")
    private Category category;

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

    public Product(ProductModel product,Category category) {
        this.name = product.getName();
        this.information = product.getInformation();
        this.price = product.getPrice();
        this.status = product.getStatus();
        this.description = product.getDescription();
        this.popular = product.getPopular();
        this.rate = product.getRate();
        this.category = category;
//        this.brand = brand;
//        this.warranty = warranty;
//        this.attributesSet = attributesSet;
    }

    public void setProduct(ProductModel product){
        this.name = product.getName();
        this.information = product.getInformation();
        this.price = product.getPrice();
        this.status = product.getStatus();
        this.description = product.getDescription();
        this.popular = product.getPopular();
        this.rate = product.getRate();
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
