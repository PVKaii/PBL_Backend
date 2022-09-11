package com.example.pbl_api.controller;


import com.example.pbl_api.entity.Product;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProducts(@RequestBody Product product){
       ProductModel result= productService.saveProduct(product);
        return  new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editProducts(@RequestBody Product product,@PathVariable(name = "id") long id){
        Product productEdit= productService.findProductById(id);
        if(productEdit==null) throw new RuntimeException();
        else {
            productEdit.setName(product.getName());
            productEdit.setInformation(product.getInformation());
            productEdit.setPrice(product.getPrice());
            productEdit.setDescription(product.getDescription());
            productEdit.setStatus(product.getStatus());
            productEdit.setPopular(product.getPopular());
            productEdit.setRate(product.getRate());
            productEdit.setSellerCategory(product.getSellerCategory());
            ProductModel result = productService.saveProduct(productEdit);
            System.out.println("rs");
            return  new ResponseEntity<>(result,HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducts(@PathVariable(name = "id") long id){
        Product productDelete= productService.findProductById(id);
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
