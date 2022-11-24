package com.example.pbl_api.service.impl;

import com.example.pbl_api.config.PaypalConfig;
import com.example.pbl_api.config.VnpayPaymentConfig;
import com.example.pbl_api.entity.UserOrder;
import com.example.pbl_api.model.PaymentModel;
import com.example.pbl_api.model.UserOrderModel;
import com.example.pbl_api.repository.UserOrderRepository;
import com.example.pbl_api.service.IPaymentService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class PaymentService implements IPaymentService {

    @Autowired
    VnpayPaymentConfig config;

    @Autowired
    private APIContext apiContext;

    @Autowired
    private PaypalConfig paypalConfig;

    @Autowired
    private UserOrderRepository userOrderRepository;


    @Override
    public String createVnpayPayment(PaymentModel payment,String ipAddress) throws UnsupportedEncodingException {
//        payment.createId();
//
//        String vnp_Version = config.vnp_Version;
//        String vnp_Command = config.vnp_Command;
//        String vnp_OrderInfo = payment.getOrderInfor();
//        String orderType = config.vnp_OrderType;
//        String vnp_TxnRef = payment.getId();
//        String vnp_IpAddr = ipAddress;
//        System.out.println(ipAddress);
//        String vnp_TmnCode = config.vnp_TmnCode;
//
//        long amount = payment.getAmount() * 100;
//        Map vnp_Params = new HashMap();
//        vnp_Params.put("vnp_Version", vnp_Version);
//        vnp_Params.put("vnp_Command", vnp_Command);
//        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
//        vnp_Params.put("vnp_Amount", String.valueOf(amount));
//        vnp_Params.put("vnp_CurrCode", config.vnp_CurrCode);
//        String bank_code = payment.getBankCode();
//        if (bank_code != null && !bank_code.isEmpty()) {
//            vnp_Params.put("vnp_BankCode", bank_code);
//        }
//        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
//        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
//        vnp_Params.put("vnp_OrderType", orderType);
//
//        String locate = config.vnp_Locale;
//        if (locate != null && !locate.isEmpty()) {
//            vnp_Params.put("vnp_Locale", locate);
//        } else {
//            vnp_Params.put("vnp_Locale", "vn");
//        }
//        vnp_Params.put("vnp_ReturnUrl", config.vnp_ReturnUrl);
//        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
//        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//        String vnp_CreateDate = formatter.format(cld.getTime());
//
//        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
//        cld.add(Calendar.MINUTE, 15);
//        String vnp_ExpireDate = formatter.format(cld.getTime());
//////        Add Params of 2.1.0 Version
////        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
//////        Billing
////        vnp_Params.put("vnp_Bill_Mobile", "123456789");
////        vnp_Params.put("vnp_Bill_Email", "pvk224@gmail.com");
////        String fullName = ("Van Khai").trim();
////        if (fullName != null && !fullName.isEmpty()) {
////            int idx = fullName.indexOf(' ');
////            String firstName = fullName.substring(0, idx);
////            String lastName = fullName.substring(fullName.lastIndexOf(' ') + 1);
////            vnp_Params.put("vnp_Bill_FirstName", firstName);
////            vnp_Params.put("vnp_Bill_LastName", lastName);
////
////        }
////        vnp_Params.put("vnp_Bill_Address","DN");
////        vnp_Params.put("vnp_Bill_City", "DN");
////        vnp_Params.put("vnp_Bill_Country", "Viet Nam");
////        if ("pending" != null && !("pending").isEmpty()) {
////            vnp_Params.put("vnp_Bill_State", "pending");
////        }
////        // Invoice
////        vnp_Params.put("vnp_Inv_Phone", "123456789");
////        vnp_Params.put("vnp_Inv_Email", "pvkk224@gmail.com");
////        vnp_Params.put("vnp_Inv_Customer", "pvk");
////        vnp_Params.put("vnp_Inv_Address", "");
////        vnp_Params.put("vnp_Inv_Company", "");
////        vnp_Params.put("vnp_Inv_Taxcode", "");
////        vnp_Params.put("vnp_Inv_Type", "");
//        //Build data to hash and querystring
//        List fieldNames = new ArrayList(vnp_Params.keySet());
//        Collections.sort(fieldNames);
//        StringBuilder hashData = new StringBuilder();
//        StringBuilder query = new StringBuilder();
//        Iterator itr = fieldNames.iterator();
//        while (itr.hasNext()) {
//            String fieldName = (String) itr.next();
//            String fieldValue = (String) vnp_Params.get(fieldName);
//            if ((fieldValue != null) && (fieldValue.length() > 0)) {
//                //Build hash data
//                hashData.append(fieldName);
//                hashData.append('=');
//                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
//                //Build query
//                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
//                query.append('=');
//                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
//                if (itr.hasNext()) {
//                    query.append('&');
//                    hashData.append('&');
//                }
//            }
//        }
//        String queryUrl = query.toString();
//        System.out.println(hashData);
//        String vnp_SecureHash = config.Sha256(config.vnp_SecureHash + hashData);
//        queryUrl += "&vnp_SecureHashType=SHA256&vnp_SecureHash=" + vnp_SecureHash;
//        String paymentUrl = config.vnp_PayUrl + "?" + queryUrl;
////        com.google.gson.JsonObject job = new JsonObject();
////        job.addProperty("code", "00");
////        job.addProperty("message", "success");
////        job.addProperty("data", paymentUrl);
////        Gson gson = new Gson();
////        resp.getWriter().write(gson.toJson(job));

//        return paymentUrl;
        return null;
    }

    @Override
    public Payment createPaypalPayment(PaymentModel paymentModel) throws UnsupportedEncodingException, PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(paypalConfig.PAYPAL_CURRENCY);
//        amount.setTotal(String.format("%.2f", paymentModel.getAmount()));
        amount.setTotal(String.valueOf(paymentModel.getAmount()));
        Transaction transaction = new Transaction();
        transaction.setDescription(paymentModel.getOrderInfor());
        transaction.setAmount(amount);
        List transactions = new ArrayList<>();
        transactions.add(transaction);
        Payer payer = new Payer();
        payer.setPaymentMethod(paypalConfig.PAYPAL_METHOD);
        Payment payment = new Payment();
        payment.setIntent(paypalConfig.PAYPAL_INTENT);
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(paypalConfig.PAYPAL_CANCEL_URL);
        String returnUrl = paypalConfig.PAYPAL_RETURN_URL+"?id=";
        for (int i = 0; i < paymentModel.getListCart().size(); i++) {
            returnUrl = returnUrl+String.valueOf(paymentModel.getListCart().get(i));
            if(i<paymentModel.getListCart().size()-1) returnUrl = returnUrl+',';
        }
        returnUrl = returnUrl+"&total="+paymentModel.getAmount()+"&idUser="+paymentModel.getUserId();
        redirectUrls.setReturnUrl(returnUrl);
        payment.setRedirectUrls(redirectUrls);
        apiContext.setMaskRequestId(true);
        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

    @Override
    public Flux<ServerSentEvent<String>> sentEventPayResult(long userId) {




//        return Flux.interval(Duration.ofSeconds(1))
//                .map(sequence ->{
//                    System.out.println("123");
//                    return ServerSentEvent.<String> builder()
//                            .id(String.valueOf(sequence))
//                            .event("event")
//                            .data("SSE" + LocalTime.now().toString())
//                            .build();
//                });
        return null;
    }

    @Override
    public SseEmitter sentEventPayResultEmitter(String paymentId) {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
//        SseEmitter.SseEventBuilder event = SseEmitter.event()
//                .data("SSE MVC - " + LocalTime.now().toString())
//                .id(String.valueOf("data"))
//                .name("event");
//        if(id==1){
//            System.out.println("!23");
//            emitter.send(event);
//            emitter.complete();
//        }
//        else if(id==2){
//            System.out.println("456");
//            emitter.send(event);
//            emitter.complete();
//        }
//        System.out.println("789");
//        emitter.complete();

        sseMvcExecutor.execute(() -> {
            try {
                while (true){
                    UserOrder userOrder= userOrderRepository.findUserOrderByPayment(paymentId);
                    String rs="";
                    if(userOrder==null) rs="pending";
                    else rs = "payed";
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data("SSE MVC - " + rs)
                            .id(String.valueOf(paymentId))
                            .name("event");
                    emitter.send(event);
                    emitter.complete();
                    Thread.sleep(2000);
                }
            } catch (Exception ex) {
                ex.getStackTrace();
                emitter.completeWithError(ex);
            }
        });

        return emitter;
    }
}
