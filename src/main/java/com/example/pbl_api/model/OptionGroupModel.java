package com.example.pbl_api.model;

import com.example.pbl_api.entity.Attributes;
import com.example.pbl_api.entity.OptionGroup;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class OptionGroupModel implements Serializable {
    private int id;
    private String name;
    private CategoryModel category;

    private List<AttributesModel> attributes;

    public OptionGroupModel(OptionGroup optionGroup) {
        this.id = optionGroup.getId();
        this.name = optionGroup.getName();
        this.category = new CategoryModel(optionGroup.getCategory());
    }

    public OptionGroupModel(OptionGroup optionGroup, Set<Attributes> attributes) {
        this.id = optionGroup.getId();
        this.name = optionGroup.getName();
//        this.category = new CategoryModel(optionGroup.getCategory());
        this.attributes = attributes.stream().map(attributes1 -> new AttributesModel(attributes1)).toList();
    }

    public List<AttributesModel> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributesModel> attributes) {
        this.attributes = attributes;
    }

    public OptionGroupModel() {
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

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }
}
