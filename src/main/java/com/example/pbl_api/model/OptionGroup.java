package com.example.pbl_api.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "option_group")
public class OptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @OneToMany(mappedBy = "optionGroup")
    private Set<Attributes> attributesSet;
}
