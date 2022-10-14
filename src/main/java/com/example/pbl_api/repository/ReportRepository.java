package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Category;
import com.example.pbl_api.project_interface.IDayReport;
import com.example.pbl_api.project_interface.IMonthReport;
import com.example.pbl_api.project_interface.IProductReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface ReportRepository extends CrudRepository<Category,Integer> {

    @Query(value = "select id,name,count(bill_result.product_id) as count\n" +
            "from product \n" +
            "left join (select bill_detail.product_id \n" +
            "from bill_detail\n" +
            "join bill on bill_detail.bill_id=bill.id\n" +
            "where bill.day between ?1 and ?2 and bill.type=true) as bill_result\n" +
            "on product.id=bill_result.product_id\n" +
            "group by product.id;",nativeQuery = true)
    List<IProductReport> findSoldCategoryReportByDay(String startDay, String endDay);


    @Query(value = "select day,sum(total) as total from bill where bill.day between '2022-10-11' and '2022-10-14' and bill.type=true group by day",nativeQuery = true)
    List<IDayReport> getDayReport(String startDay, String endDay);

    @Query(value = "select date_format(day,'%M') as month ,sum(total) as total from bill where bill.day and bill.type=true group by date_format(day,'%M')",nativeQuery = true)
    List<IMonthReport> getMonthReport();
}
