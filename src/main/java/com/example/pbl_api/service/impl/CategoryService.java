package com.example.pbl_api.service.impl;

import com.example.pbl_api.entity.Category;
import com.example.pbl_api.model.AttributesModel;
import com.example.pbl_api.model.CategoryModel;
import com.example.pbl_api.model.OptionGroupModel;
import com.example.pbl_api.repository.AttributesRepository;
import com.example.pbl_api.repository.CategoryRepository;
import com.example.pbl_api.repository.OptionGroupRepository;
import com.example.pbl_api.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Autowired
    OptionGroupRepository optionGroupRepository;

    @Autowired
    AttributesRepository attributesRepository;


    @Override
    public List<CategoryModel> getAllCategories() {
        List<CategoryModel> categoryModels =((List<Category>) categoryRepository.findAll())
                .stream().map(categoryModel -> new CategoryModel(categoryModel.getId(),categoryModel.getName(),categoryModel.getOptionGroupSet())).toList();
        return categoryModels;
    }


    @Override
    public List<OptionGroupModel> getOpionGroupsByCategory(int id) {
        List<OptionGroupModel> optionGroupModels =optionGroupRepository.findOptionGroupsByCategoryId(id)
                .stream().map(optionGroup -> new OptionGroupModel(optionGroup,optionGroup.getAttributesSet())).toList();
        return optionGroupModels;
    }

    @Override
    public List<AttributesModel> getAttributesByOptionGroup(int id) {
        List<AttributesModel> attributesModels =attributesRepository.findAttributesByOptionGroupId(id)
                .stream().map(attributes -> new AttributesModel(attributes)).toList();
        return attributesModels;
    }
}
