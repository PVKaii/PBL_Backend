package com.example.pbl_api.model;

import com.example.pbl_api.entity.Category;
import com.example.pbl_api.entity.OptionGroup;

import java.util.List;
import java.util.Set;

public class CategoryModel {
    private int id;
    private String name;

    private List<OptionGroupModel> optionGroup;

    public CategoryModel(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public CategoryModel(int id, String name, Set<OptionGroup> optionGroupSet) {
        this.id = id;
        this.name = name;
        this.optionGroup=optionGroupSet.stream().map(optionGroup1 -> new OptionGroupModel(optionGroup1,optionGroup1.getAttributesSet())).toList();

    }

    public CategoryModel() {
    }

    public List<OptionGroupModel> getOptionGroup() {
        return optionGroup;
    }

    public void setOptionGroup(List<OptionGroupModel> optionGroup) {
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


}
