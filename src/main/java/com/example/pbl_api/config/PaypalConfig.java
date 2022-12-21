package com.example.pbl_api.config;

import com.example.pbl_api.util.Url;
import org.springframework.stereotype.Component;

@Component
public class PaypalConfig {
    public static final String PAYPAL_CURRENCY = "USD";
    public static final String PAYPAL_METHOD = "paypal";
    public static final String PAYPAL_INTENT = "sale";
    public static final String PAYPAL_RETURN_URL = Url.BE_URL+"/payment/paypal/success";
    public static final String PAYPAL_CANCEL_URL = "http://google.com";

}
