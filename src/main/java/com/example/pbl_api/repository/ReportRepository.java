package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Category;
import com.example.pbl_api.project_interface.IReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReportRepository extends CrudRepository<Category,Integer> {

    @Query(value = "select id,name,count(bill_result.product_id) as count\n" +
            "from product \n" +
            "left join (select bill_detail.product_id \n" +
            "from bill_detail\n" +
            "join bill on bill_detail.bill_id=bill.id\n" +
            "where bill.day between ?1 and ?2 and bill.type=true) as bill_result\n" +
            "on product.id=bill_result.product_id\n" +
            "group by product.id;",nativeQuery = true)
    List<IReport> findSoldCategoryReportByDay(String startDay,String endDay);
}
