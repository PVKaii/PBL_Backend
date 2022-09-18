package com.example.pbl_api.repository;

import com.example.pbl_api.entity.BillDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillDetailRepository extends CrudRepository<BillDetail, Long> {
    BillDetail findBillDetailsByBillId(long id);
}
