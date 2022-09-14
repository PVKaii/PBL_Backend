package com.example.pbl_api.service;

import com.example.pbl_api.model.AttributesModel;
import com.example.pbl_api.model.CategoryModel;
import com.example.pbl_api.model.OptionGroupModel;
import com.example.pbl_api.model.SellerCategoryModel;

import java.util.List;

public interface CategoryService {
    List<SellerCategoryModel> getAllSellercategories();

     List<CategoryModel>  getAllCategories();

    List<CategoryModel> getCategoriesBySellerCategory(int id);

    List<OptionGroupModel> getOpionGroupsByCategory(int id);

    List<AttributesModel> getAttributesByOptionGroup(int id);
}
