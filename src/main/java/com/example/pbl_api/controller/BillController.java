package com.example.pbl_api.controller;


import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.service.impl.BillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("bill")
public class BillController {

    @Autowired
    BillService billService;


    @GetMapping("")
    public ResponseEntity<?> getAllBills(){
        return new ResponseEntity<>(billService.getAllBills(), HttpStatus.OK);
    }

    @PostMapping ("add")
    public ResponseEntity<?> saveBill(@RequestBody ObjectNode json){
        ObjectMapper mapper = new ObjectMapper();
        List<ProductModel> productModelList = Arrays.asList(mapper.convertValue( json.get("products"), ProductModel[].class));
        List<Integer> productsAmountList = Arrays.asList(mapper.convertValue( json.get("amount"),Integer[].class));
        double total = json.get("total").asDouble();
        boolean type = json.get("type").asBoolean();
        int idUser = json.get("idUser").asInt();
        System.out.println(productsAmountList);
        return new ResponseEntity<>(billService.saveBill(productModelList,idUser,total,type,productsAmountList), HttpStatus.OK);
//        return new ResponseEntity<>("OK", HttpStatus.OK);

    }
}
