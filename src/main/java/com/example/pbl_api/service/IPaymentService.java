package com.example.pbl_api.service;

import com.example.pbl_api.model.PaymentModel;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.UnsupportedEncodingException;

public interface IPaymentService {
    String createVnpayPayment(PaymentModel payment,String ipAddress) throws UnsupportedEncodingException;

    Payment createPaypalPayment(PaymentModel paymentModel) throws UnsupportedEncodingException, PayPalRESTException;

    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

    Flux<ServerSentEvent<String>> sentEventPayResult(long userId);

    SseEmitter sentEventPayResultEmitter(String paymentId);
}
