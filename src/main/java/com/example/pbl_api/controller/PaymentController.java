package com.example.pbl_api.controller;

import com.example.pbl_api.model.PaymentModel;
import com.example.pbl_api.service.impl.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("")
    public ResponseEntity<?> createPayment(@RequestBody PaymentModel paymentModel , HttpServletRequest request){
        String ipAdress;
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getRemoteAddr();
                return new ResponseEntity<>(paymentService.createPayment(paymentModel,ipAdress),HttpStatus.OK);
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
            return new ResponseEntity<>(ipAdress,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
