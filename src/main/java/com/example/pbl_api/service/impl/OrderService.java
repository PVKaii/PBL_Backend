package com.example.pbl_api.service.impl;

import com.example.pbl_api.entity.*;
import com.example.pbl_api.model.OrderStatusModel;
import com.example.pbl_api.model.ProductModel;
import com.example.pbl_api.model.UserOrderModel;
import com.example.pbl_api.repository.*;
import com.example.pbl_api.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    UserOrderRepository userOrderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    BillDetailRepository billDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public UserOrderModel order(List<ProductModel> productList, int idUser, double total, boolean type, List<Integer> productsAmountList) {
        User user =userRepository.findUserById(idUser);
        UserOrder order= new UserOrder( LocalDate.now(),user,new OrderStatus(1));
        Bill bill = new Bill(total,LocalDate.now(),order,type);
        List<OrderDetail> orderDetailList= new ArrayList<>();
        List<BillDetail> billDetailList = new ArrayList<>();
        for(int i =0;i<productList.size();i++){
            ProductModel productModel = productList.get(i);
            Product product = productRepository.findProductById(productModel.getId());
            int amount = productsAmountList.get(i);
            double totalPayable =product.getPrice()*amount;
            OrderDetail orderDetail = new OrderDetail(amount,product,order);
            BillDetail billDetail = new BillDetail(bill,totalPayable,orderDetail);
            orderDetailList.add(orderDetail);
            billDetailList.add(billDetail);
        }
        order.setOrderDetailSet(new HashSet<>(orderDetailList));
        if(orderDetailList.size()>0){
            userOrderRepository.save(order);
            orderDetailRepository.saveAll(orderDetailList);
            billRepository.save(bill);
            billDetailRepository.saveAll(billDetailList);
            return new UserOrderModel(order,bill,orderDetailList);
        }
        return  null;
    }

    @Override
    public UserOrderModel handleOrder(long orderId,int statusId) {
        UserOrder userOrder = userOrderRepository.findById(orderId);
        if(userOrder!=null){
            userOrder.setOrderStatus(orderStatusRepository.findOrderStatusById(statusId));
            userOrderRepository.save(userOrder);
            return new UserOrderModel(userOrder);
        }

        return null;
    }

    @Override
    public List<UserOrderModel> getOrdersByUserId(long userId) {
        List<UserOrderModel> rs = userOrderRepository.getUserOrdersByUserId(userId)
                .stream().map(userOrder -> new UserOrderModel(userOrder)).collect(Collectors.toList());
        return rs;
    }
}
