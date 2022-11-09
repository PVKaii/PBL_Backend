package com.example.pbl_api.service;

import com.example.pbl_api.model.OrderStatusModel;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.model.UserOrderModel;

import java.util.List;

public interface IOrderService {
    UserOrderModel order(List<ProductModel> productList, int idUser, double total, boolean type, List<Integer> productsAmountList);

    UserOrderModel handleOrder(long orderId,int StatusId);

    List<UserOrderModel> getOrdersByUserId(long userId);
}
