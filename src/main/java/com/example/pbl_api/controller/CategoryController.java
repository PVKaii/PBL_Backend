package com.example.pbl_api.controller;


import com.example.pbl_api.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getOptionGroupsByCategory(@PathVariable(name = "id")int id){
        return new ResponseEntity<>(categoryService.getOpionGroupsByCategory(id), HttpStatus.OK);
    }


    @GetMapping("/group/{id}")
    public ResponseEntity<?> getAttributesByGroup(@PathVariable(name = "id")int id){
        return new ResponseEntity<>(categoryService.getAttributesByOptionGroup(id), HttpStatus.OK);
    }


}
