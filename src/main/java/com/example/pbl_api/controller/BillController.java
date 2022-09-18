package com.example.pbl_api.controller;


import com.example.pbl_api.entity.BillDetail;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.service.impl.BillServiceImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("bill")
public class BillController {

    @Autowired
    BillServiceImpl billService;


    @GetMapping("")
    public ResponseEntity<?> getAllBills(){
        return new ResponseEntity<>(billService.getAllBills(), HttpStatus.OK);
    }

    @PostMapping ("add")
    public ResponseEntity<?> saveBill(@RequestBody ObjectNode json){
        ObjectMapper mapper = new ObjectMapper();
        List<ProductModel> productModelList = Arrays.asList(mapper.convertValue( json.get("products"), ProductModel[].class));
        double total = json.get("total").asDouble();
        int idUser = json.get("idUser").asInt();
        return new ResponseEntity<>(billService.saveBill(productModelList,idUser,total), HttpStatus.OK);
    }
}
