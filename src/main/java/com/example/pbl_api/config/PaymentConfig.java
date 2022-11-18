package com.example.pbl_api.config;


import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Component
public class PaymentConfig {
    public static final String vnp_TmnCode = "LHLI5EDO";
    public static final String vnp_Command = "pay";
    public static final String vnp_SecureHash = "UXEOUJJVRKMNNLFAZDHMRKVHFLROHHDJ";
    public static final String vnp_Version = "2.1.0";
    public static final String vnp_CurrCode = "VND";
    public static final String vnp_ReturnUrl = "http://localhost:5000/payment/result";
    public static final String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String vnp_OrderType= "130000";
    public static final String vnp_Locale= "vn";



    public static String hmacSHA512(final String key, final String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }








}
