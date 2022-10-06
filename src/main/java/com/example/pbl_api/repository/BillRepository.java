package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface BillRepository extends CrudRepository<Bill,Long> {
    Bill findBillsByUserId(long id);
}
