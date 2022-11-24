package com.example.pbl_api.config;

import org.springframework.stereotype.Component;

@Component
public class PaypalConfig {
    public static final String PAYPAL_CURRENCY = "USD";
    public static final String PAYPAL_METHOD = "paypal";
    public static final String PAYPAL_INTENT = "sale";
    public static final String PAYPAL_RETURN_URL = "http://localhost:5000/payment/paypal/success";
    public static final String PAYPAL_CANCEL_URL = "http://google.com";

}
