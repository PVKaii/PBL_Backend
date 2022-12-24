package com.example.pbl_api.controller;

import com.example.pbl_api.model.CartModel;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.model.UserOrderModel;
import com.example.pbl_api.service.impl.BillService;
import com.example.pbl_api.service.impl.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;


    @GetMapping("{id}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable(name = "id") long userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId),httpHeaders ,HttpStatus.OK);
    }

    @GetMapping("pending")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getOrdersByUser(){
        return new ResponseEntity<>(orderService.getAllPendingOrder(),HttpStatus.OK);
    }

    @PutMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> handleOrder(@RequestBody ObjectNode json){
        long orderId = json.get("orderId").asLong();
        String status = json.get("status").asText();
        UserOrderModel rs= orderService.handleOrder(orderId,status);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<?> order(@RequestBody ObjectNode json){
        ObjectMapper mapper = new ObjectMapper();
        List<CartModel> cartList = Arrays.asList(mapper.convertValue( json.get("cart"), CartModel[].class));
        double total = json.get("total").asDouble();
        boolean type = json.get("type").asBoolean();
        int idUser = json.get("idUser").asInt();
//        System.out.println(productsAmountList);
//        return new ResponseEntity<>(orderService.order(cartList,idUser,total,type), HttpStatus.OK);
//        return new ResponseEntity<>("OK", HttpStatus.OK);
        return null;
    }
}
