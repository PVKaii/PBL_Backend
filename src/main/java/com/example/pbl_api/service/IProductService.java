package com.example.pbl_api.service;

import com.example.pbl_api.entity.Product;
import com.example.pbl_api.model.AttributesModel;
import com.example.pbl_api.model.CategoryModel;
import com.example.pbl_api.model.OptionGroupModel;
import com.example.pbl_api.model.ProductModel;

import java.util.List;

public interface IProductService {
    List<ProductModel> getAllProducts();

    List<ProductModel> getProductsByCategory(int idCategory);

    List<ProductModel> getProductsByFilter(int id,List<Integer> filters );

    ProductModel findProductModelById(long id);

    Product findProductById(long id);

    ProductModel saveProduct(ProductModel product);

    ProductModel updateProduct(long id,ProductModel product);

    ProductModel deleteProduct(long id);


    List<CategoryModel>  getAllCategories();

    List<String>  getAllCategoriesName();

    List<OptionGroupModel> getOpionGroupsByCategory(int id);

    List<AttributesModel> getAttributesByOptionGroup(int id);

}
