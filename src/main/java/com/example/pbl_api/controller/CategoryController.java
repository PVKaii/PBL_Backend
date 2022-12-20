package com.example.pbl_api.controller;


import com.example.pbl_api.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("")
public class CategoryController {

    @Autowired
    ProductService productService;
    @GetMapping("/")
    public ResponseEntity<?> getAllCategories(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(productService.getAllCategories(), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategoriesName(){
        return new ResponseEntity<>(productService.getAllCategoriesName(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getOptionGroupsByCategory(@PathVariable(name = "id")int id){
        return new ResponseEntity<>(productService.getOpionGroupsByCategory(id), HttpStatus.OK);
    }


    @GetMapping("/group/{id}")
    public ResponseEntity<?> getAttributesByGroup(@PathVariable(name = "id")int id){
        return new ResponseEntity<>(productService.getAttributesByOptionGroup(id), HttpStatus.OK);
    }


}
