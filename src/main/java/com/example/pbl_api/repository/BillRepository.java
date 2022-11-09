package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Bill;
import com.example.pbl_api.project_interface.IReports;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BillRepository extends CrudRepository<Bill,Long> {
    List<Bill> findAllByOrderByIdDesc();

    @Query(value = "select bill.id,bill.day,bill.total,bill.type,bill.order_id from bill\n" +
            "join user_order on bill.order_id=user_order.id\n" +
            "where user_order.user_id=?1",nativeQuery = true)
    List<Bill> findBillsByUserId(long userId);
}
