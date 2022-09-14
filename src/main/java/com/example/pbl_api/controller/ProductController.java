package com.example.pbl_api.controller;


import com.example.pbl_api.entity.Product;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("")
    public ResponseEntity<?> getProducts(@RequestParam(name = "category",required = false) Integer idCategory,@RequestParam(name = "filter",required = false) List<Integer> filters){
        System.out.println(filters);
        if(idCategory==null){
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        }
        else if(idCategory!=null&&filters==null) {
            return new ResponseEntity<>(productService.getProductsByCategory(idCategory), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(productService.getProductsByFilter(idCategory,filters), HttpStatus.OK);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<?> addProducts(@RequestBody ProductModel product){
       ProductModel result= productService.saveProduct(product);
        return  new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editProducts(@RequestBody ProductModel product,@PathVariable(name = "id") long id){
        ProductModel result= productService.updateProduct(id,product);
        return  new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducts(@PathVariable(name = "id") long id){
        ProductModel productDelete= productService.findProductModelById(id);
        if(productDelete==null) throw new RuntimeException();
        else {
            ProductModel result= productService.deleteProduct(productDelete.getId());
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProducts(@PathVariable(name = "id") long id){
        ProductModel product= productService.findProductModelById(id);
        if(product ==null) throw new RuntimeException();
        else return new ResponseEntity<>(product,HttpStatus.OK);
    }

}
