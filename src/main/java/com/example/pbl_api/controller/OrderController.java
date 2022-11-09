package com.example.pbl_api.controller;

import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.model.UserOrderModel;
import com.example.pbl_api.service.impl.BillService;
import com.example.pbl_api.service.impl.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;


    @GetMapping("{id}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable(name = "id") long userId){
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId) ,HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> handleOrder(@RequestBody ObjectNode json){
        long orderId = json.get("orderId").asLong();
        int statusId = json.get("statusId").asInt();
        UserOrderModel rs= orderService.handleOrder(orderId,statusId);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<?> order(@RequestBody ObjectNode json){
        ObjectMapper mapper = new ObjectMapper();
        List<ProductModel> productModelList = Arrays.asList(mapper.convertValue( json.get("products"), ProductModel[].class));
        List<Integer> productsAmountList = Arrays.asList(mapper.convertValue( json.get("amount"),Integer[].class));
        double total = json.get("total").asDouble();
        boolean type = json.get("type").asBoolean();
        int idUser = json.get("idUser").asInt();
//        System.out.println(productsAmountList);
        return new ResponseEntity<>(orderService.order(productModelList,idUser,total,type,productsAmountList), HttpStatus.OK);
//        return new ResponseEntity<>("OK", HttpStatus.OK);

    }
}
