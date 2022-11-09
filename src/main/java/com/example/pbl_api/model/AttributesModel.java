package com.example.pbl_api.model;

import com.example.pbl_api.entity.Attributes;

import java.util.List;

public class AttributesModel {
    private int id;
    private String name;
    private String optionGroup;


    public AttributesModel(Attributes attributes) {
        this.id = attributes.getId();
        this.name = attributes.getName();
        this.optionGroup = attributes.getOptionGroup().getName();
    }

    public AttributesModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AttributesModel() {
    }

    public String getOptionGroup() {
        return optionGroup;
    }

    public void setOptionGroup(String optionGroup) {
        this.optionGroup = optionGroup;
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

//    public OptionGroupModel getOptionGroup() {
//        return optionGroup;
//    }
//
//    public void setOptionGroup(OptionGroupModel optionGroup) {
//        this.optionGroup = optionGroup;
//    }

}
