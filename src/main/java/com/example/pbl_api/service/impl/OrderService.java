package com.example.pbl_api.service.impl;

import com.example.pbl_api.entity.*;
import com.example.pbl_api.model.CartModel;
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

    @Autowired
    CartRepository cartRepository;



    @Override
    public UserOrderModel order(List<Long> cartList, int idUser, double total, boolean type,String payment) {
        User user =userRepository.findUserById(idUser);
        UserOrder order= new UserOrder( LocalDate.now(),user,orderStatusRepository.findOrderStatusByName("Chưa xác nhận"),payment);
        Bill bill = new Bill(total,LocalDate.now(),order,type);
        List<OrderDetail> orderDetailList= new ArrayList<>();
        List<BillDetail> billDetailList = new ArrayList<>();
        for(int i =0;i<cartList.size();i++){
            CartModel cart = new CartModel(cartRepository.findCartById(cartList.get(i)));
            ProductModel productModel = cart.getProduct();
            Product product = productRepository.findProductById(productModel.getId());
            int amount =cart.getAmount();
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
            return new UserOrderModel(order,bill,orderDetailList,payment);
        }
        return  null;
    }

    @Override
    public UserOrderModel handleOrder(long orderId,String status) {
        UserOrder userOrder = userOrderRepository.findById(orderId);
        OrderStatus orderStatus=null;
        if(status=="OK"){
            orderStatus=orderStatusRepository.findOrderStatusByName("Đã xác nhận");
        }
        else if(status=="Deny"){
            orderStatus=orderStatusRepository.findOrderStatusByName("Đã từ chối");
        }
        if(userOrder!=null&&orderStatus!=null){
            userOrder.setOrderStatus(orderStatus);
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

    @Override
    public UserOrderModel findOrderByPayment(String payment) {
        UserOrder result = userOrderRepository.findUserOrderByPayment(payment);
        return  new UserOrderModel(result);
    }
}
