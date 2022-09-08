package com.example.pbl_api.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    private Set<Product> productSet;

    public Brand(String name) {
        this.name = name;
    }

    public Brand() {

    }
}
