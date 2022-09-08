package com.example.pbl_api.model;

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
}
