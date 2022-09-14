package com.example.pbl_api.service;

import com.example.pbl_api.entity.Product;
import com.example.pbl_api.model.ProductModel;

import java.util.List;

public interface ProductService {
    List<ProductModel> getAllProducts();

    List<ProductModel> getProductsByCategory(int idCategory);

    List<ProductModel> getProductsByFilter(int id,List<Integer> filters );

    ProductModel findProductModelById(long id);

    Product findProductById(long id);

    ProductModel saveProduct(ProductModel product);

    ProductModel updateProduct(long id,ProductModel product);

    ProductModel deleteProduct(long id);




}
