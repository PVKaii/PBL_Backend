package com.example.pbl_api.service;

import com.example.pbl_api.model.AttributesModel;
import com.example.pbl_api.model.CategoryModel;
import com.example.pbl_api.model.OptionGroupModel;

import java.util.List;

public interface ICategoryService {

     List<CategoryModel>  getAllCategories();

    List<OptionGroupModel> getOpionGroupsByCategory(int id);

    List<AttributesModel> getAttributesByOptionGroup(int id);
}
