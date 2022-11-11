package com.example.pbl_api.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public ProductImage(String url, Product product) {
        this.url = url;
        this.product = product;
    }

    public ProductImage() {
    }
}
