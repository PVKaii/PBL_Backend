package com.example.pbl_api.service.impl;

import com.example.pbl_api.entity.Category;
import com.example.pbl_api.entity.Product;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.repository.CategoryRepository;
import com.example.pbl_api.repository.ProductRepository;
import com.example.pbl_api.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

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
        System.out.println("s2");
        List<ProductModel> productModels=productRepository.findProductsByCategoryId(idCategory)
                .stream().map(product -> new ProductModel(product)).toList();
        return productModels;
    }

    @Override
    public List<ProductModel> getProductsByFilter(int idCatgory,List<Integer> filters) {
        int sizeFilter=filters.size();
        for (int i = 0; i < 5-sizeFilter; i++) {
            filters.add(null);
        }
        System.out.println(sizeFilter);
        List<ProductModel> productModels=productRepository.findProductsByFilter(
                idCatgory,
                filters.get(0),filters.get(1),filters.get(2),filters.get(3),filters.get(4),
                sizeFilter-1
                )
                .stream().map(product -> new ProductModel(product)).toList();
        return productModels;
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
    public ProductModel saveProduct(ProductModel product) {
        Product productRs= productRepository.save(new Product(
                product));
        return new ProductModel(productRs);
    }

    @Override
    public ProductModel updateProduct(long id, ProductModel product) {
        Product productEdit = productRepository.findProductById(id);
        if(productEdit==null) throw new RuntimeException();
        else{
//            productEdit.setName(product.getName());
//            productEdit.setInformation(product.getInformation());
//            productEdit.setPrice(product.getPrice());
//            productEdit.setDescription(product.getDescription());
//            productEdit.setStatus(product.getStatus());
//            productEdit.setPopular(product.getPopular());
//            productEdit.setRate(product.getRate());
            productEdit.setProduct(product);
            Product result = productRepository.save(productEdit);
            return new ProductModel(result);
        }
    }

    @Override
    public ProductModel deleteProduct(long id) {
        Product productRs= productRepository.findProductById(id);
        productRepository.deleteById(id);
        return new ProductModel(productRs);
    }
}
