package com.example.pbl_api.entity;

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



    @OneToMany(mappedBy = "category")
    private Set<OptionGroup> optionGroupSet;

    @OneToMany(mappedBy = "category")
    private Set<Product> productSet;


    public Category() {
    }

    public Category(String name) {
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


    public Set<OptionGroup> getOptionGroupSet() {
        return optionGroupSet;
    }

    public void setOptionGroupSet(Set<OptionGroup> optionGroupSet) {
        this.optionGroupSet = optionGroupSet;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }
}
