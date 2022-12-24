package com.example.pbl_api.service;

import com.example.pbl_api.entity.User;
import com.example.pbl_api.model.CartModel;
import com.example.pbl_api.model.OrderStatusModel;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.model.UserOrderModel;

import java.util.List;

public interface IOrderService {
    UserOrderModel order(List<Long> cartList, int idUser, double total, boolean type,String payment);

    UserOrderModel handleOrder(long orderId,String status);

    List<UserOrderModel> getOrdersByUserId(long userId);

    UserOrderModel findOrderByPayment(String payment);

    List<UserOrderModel> getAllPendingOrder();
}
