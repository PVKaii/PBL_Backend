package com.example.pbl_api.service.impl;

import com.example.pbl_api.entity.Cart;
import com.example.pbl_api.entity.Product;
import com.example.pbl_api.entity.User;
import com.example.pbl_api.model.CartModel;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.repository.CartRepository;
import com.example.pbl_api.repository.ProductRepository;
import com.example.pbl_api.repository.UserRepository;
import com.example.pbl_api.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public CartModel addNewProductToUserCart(ProductModel product, long userId) {
        Cart cart = cartRepository.findCartByProductIdAndUserId(product.getId(),userId);
        if(cart!=null){
            cart.setAmount(cart.getAmount()+1);
            cartRepository.save(cart);
            return new CartModel(cart);
        }
        else{
            Cart newCart = new Cart(userRepository.findUserById(userId),productRepository.findProductById(product.getId()),1);
            cartRepository.save(newCart);
            return new CartModel(newCart);
        }
    }

    @Override
    public void removeProductFromUserCart(ProductModel product, long userId) {
        Cart cart = cartRepository.findCartByProductIdAndUserId(product.getId(),userId);
        if(cart.getAmount()>1){
            cart.setAmount(cart.getAmount()-1);
            cartRepository.save(cart);
        }
        else{
            cartRepository.deleteCartByProductIdAndUserId(product.getId(),userId);
        }

    }

    @Override
    public List<CartModel> getAllProductsFromUserCart(long userId) {
        List<CartModel> cartProducts = cartRepository.findCartsByUserId(userId)
                .stream().map(cart -> new CartModel(cart)).toList();
        return cartProducts;
    }

    @Override
    public void deleteCartsById(List<Long> listId) {
        List<Cart> listCart = listId.stream().map(id -> new Cart(id)).toList();
        cartRepository.deleteAll(listCart);
    }

    @Override
    public void deleteCartById(long id) {
        cartRepository.delete(new Cart(id));
    }
}
