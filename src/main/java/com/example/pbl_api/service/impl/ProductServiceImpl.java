package com.example.pbl_api.service.impl;

import com.example.pbl_api.entity.Product;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.repository.ProductRepository;
import com.example.pbl_api.repository.SellerCategoryRepository;
import com.example.pbl_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerCategoryRepository sellerCategoryRepository;

    @Override
    public List<ProductModel> getAllProducts() {
        List<ProductModel> productModels=((List<Product>) productRepository.findAll()).stream().map(
          product -> new ProductModel(product)
        ).toList();
//        List<ProductModel> productModels= new ArrayList<>();
//        for (Product p:
//                (List<Product>) productRepository.findAll()) {
//
//        }
        return productModels;
    }

    @Override
    public List<ProductModel> getProductsByCategory(int idCategory) {
        return null;
    }

    @Override
    public List<ProductModel> getProductsByFilter() {
        return null;
    }

    @Override
    public ProductModel findProductModelById(long id) {
        return new ProductModel(productRepository.findProductById(id));
    }

    @Override
    public Product findProductById(long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public ProductModel saveProduct(Product product) {
        product.setSellerCategory(sellerCategoryRepository.findSellerCategoryById(product.getSellerCategory().getId()));
        Product productRs= productRepository.save(product);
        return new ProductModel(productRs);
    }

    @Override
    public ProductModel deleteProduct(long id) {
        Product productRs= productRepository.findProductById(id);
        productRepository.deleteById(id);
        return new ProductModel(productRs);
    }
}
