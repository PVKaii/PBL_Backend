package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Category;
import com.example.pbl_api.project_interface.IReports;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface ReportsRepository extends CrudRepository<Category,Integer> {

    @Query(value = "select sum(bill.total) as data , yearweek(bill.day) as label from bill where bill.type=1 group by label\n",nativeQuery = true)
    List<IReports> getWeeksSalesReports();


    @Query(value = "select sum(case when bill.type = 1 then bill.total else -bill.total end) as data , yearweek(bill.day) as label from bill  group by label\n",nativeQuery = true)
    List<IReports> getWeeksRevenueReports();

    @Query(value = "select website_visitor.counter as data ,website_visitor.week as label from website_visitor ;",nativeQuery = true)
    List<IReports> getWeeksVisitorsReports();

    @Query(value = "select category.name as label, count(category.name) as data \n" +
            "from product \n" +
            "join category on product.category = category.id\n" +
            "join order_detail on order_detail.product_id = product.id\n" +
            "join user_order on  order_detail.order_id = user_order.id\n" +
            "join bill on user_order.id=bill.order_id  \n" +
            "where bill.type=1\n" +
            "group by(category.name)\n",nativeQuery = true)
    List<IReports> getWeeksProductsReports();


    @Query(value = "select sum(bill.total) as data , \"Total sales\" as label from bill\n" +
            "  where bill.type=1\n" +
            "  && yearweek(bill.day)=yearweek(now())\n" +
            "  group by label\n" +
            "union\n" +
            "select sum(case when bill.type = 1 then bill.total else -bill.total end) as data , \"Total revenue\" as label\n" +
            " from bill\n" +
            "where yearweek(bill.day)=yearweek(now())\n" +
            " group by label\n" +
            "union\n" +
            "select count(order_detail.product_id) as data , \"Amount products sales\" as label\n" +
            "from order_detail\n" +
            "join bill_detail on bill_detail.detail_id=order_detail.id\n" +
            "join bill on bill_detail.bill_id=bill.id \n" +
            "where yearweek(bill.day) = yearweek(now()) && bill.type=1\n" +
            "union\n" +
            "select sum(bill.total) as data , \"Total sales last month\" as label from bill\n" +
            "  where bill.type=1\n" +
            "  && DATE_FORMAT(bill.day , '%Y%m')=DATE_FORMAT(now() - interval 1 month , '%Y%m')\n" +
            "  group by label\n" +
            "union\n" +
            "select sum(case when bill.type = 1 then bill.total else -bill.total end) as data , \"Total revenue last month\" as label\n" +
            " from bill\n" +
            "where DATE_FORMAT(bill.day , '%Y%m')=DATE_FORMAT(now() - interval 1 month , '%Y%m')\n" +
            " group by label\n" +
            "union\n" +
            "select count(order_detail.product_id) as data , \"Amount products sales last month\" as label\n" +
            "from order_detail\n" +
            "join bill_detail on bill_detail.detail_id=order_detail.id\n" +
            "join bill on bill_detail.bill_id=bill.id \n" +
            "where DATE_FORMAT(bill.day , '%Y%m')=DATE_FORMAT(now() - interval 1 month , '%Y%m') && bill.type=1\n" +
            "union\n" +
            "select website_visitor.counter as data ,\"Visitor\" as label from website_visitor where website_visitor.week = yearweek(now());",nativeQuery = true)
    List<IReports> getTotalReports();
}
