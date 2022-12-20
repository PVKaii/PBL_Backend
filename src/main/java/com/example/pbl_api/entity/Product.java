package com.example.pbl_api.entity;

import com.example.pbl_api.model.ProductModel;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",unique = true)
    private String name;


    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private Set<ProductImage> imageSet;

    @Column(name = "price",nullable = false)
    private long price;

    @Column(name = "status",nullable = false)
    private Boolean status;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;



    @Column(name = "rate")
    private double rate;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;


    @ManyToMany
    @JoinTable(
            name = "product_attributes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attributes_id")
    )
    private Set<Attributes> attributesSet;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetailSet;

    @OneToMany(mappedBy = "product")
    private Set<Cart> cartSet;

    public Product(String name, List<Attributes> attributesList) {
        this.name = name;
        this.status=true;
        this.price=15000;
        this.attributesSet=attributesList.stream().collect(Collectors.toSet());
    }

    public Product() {

    }

    public Product(String name, long price, String description,
                   Set<Attributes> attributesSet,
                   Brand brand,Category category,Set<ProductImage> productImageSet) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.attributesSet = attributesSet;
        this.status=true;
        this.category=category;
        this.brand=brand;
        this.imageSet = productImageSet;
    }

    public Product(ProductModel product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.status = product.getStatus();
        this.description = product.getDescription();
        this.rate = product.getRate();
        this.category = new Category(product.getCategory().getId());
        this.brand=new Brand(product.getBrand().getId());
//        this.warranty = warranty;
        this.attributesSet = product.getAttributes().stream()
                .map(attributesModel -> new Attributes(attributesModel.getId()))
                .collect(Collectors.toSet());
    }

    public void setProduct(ProductModel product){
        this.name = product.getName();
        this.price = product.getPrice();
        this.status = product.getStatus();
        this.description = product.getDescription();
        this.rate = product.getRate();
        this.category = new Category(product.getCategory().getId());
        this.brand=new Brand(product.getBrand().getId());
        this.attributesSet = product.getAttributes().stream()
                .map(attributesModel -> new Attributes(attributesModel.getId()))
                .collect(Collectors.toSet());
    }

    public Product(long id) {
        this.id = id;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
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


    public Set<Attributes> getAttributesSet() {
        return attributesSet;
    }

    public void setAttributesSet(Set<Attributes> attributesSet) {
        this.attributesSet = attributesSet;
    }



    public Set<Cart> getCartSet() {
        return cartSet;
    }

    public void setCartSet(Set<Cart> cartSet) {
        this.cartSet = cartSet;
    }

    public Set<ProductImage> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<ProductImage> imageSet) {
        this.imageSet = imageSet;
    }
}
