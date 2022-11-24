package com.example.pbl_api.service;

import com.example.pbl_api.model.CartModel;
import com.example.pbl_api.model.ProductModel;

import java.util.List;

public interface ICartService {
    CartModel addNewProductToUserCart(ProductModel product, long userId);

    void removeProductFromUserCart(ProductModel product,long userId);

    List<CartModel> getAllProductsFromUserCart(long userId);

    void deleteCartsById(List<Long> listId);

}
