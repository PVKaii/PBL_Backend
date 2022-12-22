package com.example.pbl_api.controller;


import com.example.pbl_api.model.CartModel;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/{id}")
    public ResponseEntity<?> addNewProductToUserCart(@RequestBody ProductModel product,@PathVariable(name = "id") long userId){
        CartModel cart = cartService.addNewProductToUserCart(product,userId);
        return  new ResponseEntity<>(cart, HttpStatus.OK);
    };

    @PutMapping("/{id}")
    public ResponseEntity<?> removeProductFromUserCart(@RequestBody ProductModel product,@PathVariable(name = "id") long userId){
        cartService.removeProductFromUserCart(product,userId);
        return  new ResponseEntity<>( HttpStatus.OK);
    };

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllProductsFromUserCart(@PathVariable(name = "id") long userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        List<CartModel> cartProducts = cartService.getAllProductsFromUserCart(userId);
       return new ResponseEntity<>( cartProducts,httpHeaders,HttpStatus.OK);
    };


    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteCartById(@PathVariable long id){
        cartService.deleteCartById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    };


}
