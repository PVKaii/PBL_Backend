package com.example.pbl_api.service;

import com.example.pbl_api.model.PaymentModel;

import java.io.UnsupportedEncodingException;

public interface IPaymentService {
    String createPayment(PaymentModel payment,String ipAddress) throws UnsupportedEncodingException;
}
