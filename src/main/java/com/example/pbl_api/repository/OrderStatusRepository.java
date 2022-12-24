package com.example.pbl_api.repository;

import com.example.pbl_api.entity.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus,Integer> {
    OrderStatus findOrderStatusById(int id);

    OrderStatus findOrderStatusByName(String name);

}
