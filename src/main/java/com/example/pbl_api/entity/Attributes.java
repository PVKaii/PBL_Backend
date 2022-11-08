package com.example.pbl_api.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "attributes")
public class Attributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "option_group_id",nullable = false)
    private OptionGroup optionGroup;

    @ManyToMany(mappedBy = "attributesSet")
    private Set<Product> productSet;


    public Attributes() {
    }

    public Attributes(String name, OptionGroup optionGroup) {
        this.name = name;
        this.optionGroup = optionGroup;
    }

    public Attributes(int id) {
        this.id = id;
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

    public OptionGroup getOptionGroup() {
        return optionGroup;
    }

    public void setOptionGroup(OptionGroup optionGroup) {
        this.optionGroup = optionGroup;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }
}
