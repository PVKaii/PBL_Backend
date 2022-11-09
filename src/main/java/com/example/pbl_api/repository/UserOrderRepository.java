package com.example.pbl_api.repository;

import com.example.pbl_api.entity.UserOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderRepository extends CrudRepository<UserOrder,Long> {
    List<UserOrder> getUserOrdersByUserId(long id);

    UserOrder findById(long id);
}
