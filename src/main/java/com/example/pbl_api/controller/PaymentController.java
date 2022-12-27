package com.example.pbl_api.controller;

import com.example.pbl_api.model.PaymentModel;
import com.example.pbl_api.service.impl.CartService;
import com.example.pbl_api.service.impl.OrderService;
import com.example.pbl_api.service.impl.PaymentService;
import com.example.pbl_api.util.Url;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.view.RedirectView;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @PostMapping("/vnpay")
    public ResponseEntity<?> createVnpayPayment(@RequestBody PaymentModel paymentModel , HttpServletRequest request){
        String ipAdress;
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getRemoteAddr();
                return new ResponseEntity<>(paymentService.createVnpayPayment(paymentModel,ipAdress),HttpStatus.OK);
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
            return new ResponseEntity<>(ipAdress,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/paypal")
    public ResponseEntity<?> createPaypalPayment(@RequestBody PaymentModel paymentModel){
        try {
            Payment payment = paymentService.createPaypalPayment(paymentModel);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    List<String> result= new ArrayList<>();
                    result.add(links.getHref());
                    result.add(payment.getId());
                    return new ResponseEntity<>(result ,HttpStatus.OK);
                }
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }




    @GetMapping("paypal/result")
    public SseEmitter paymentResult(@RequestParam("id") String id) throws IOException {
       return paymentService.sentEventPayResultEmitter(id);
    }

    @GetMapping("/paypal/success")
    public RedirectView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId
    , @RequestParam("id")List<Long> listId,@RequestParam("total") Double total , @RequestParam("idUser")Integer idUser
                                   ){
        RedirectView redirectView = new RedirectView();
        try {
            Payment payment = paymentService.executePayment(paymentId, payerId);
            System.out.println(payment);
            if(payment.getState().equals("approved")){
                orderService.order(listId,idUser,total,true,payment.getId());
                cartService.deleteCartsById(listId);
                redirectView.setUrl(Url.FE_URL+"/paymentsuccess");
                return redirectView;
            }
        } catch (PayPalRESTException e) {

        }
        redirectView.setUrl(Url.BE_URL);
        return redirectView;
    }
}
