package com.example.pbl_api.repository;

import com.example.pbl_api.entity.WebsiteVisitor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WebsiteVisitorRepository extends CrudRepository<WebsiteVisitor,Integer> {
    @Query(value = "select * from website_visitor where website_visitor.week = yearweek(now())\n",nativeQuery = true)
    WebsiteVisitor getWebsiteVisitorNow();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO website_visitor VALUES (yearweek(now()), 1);\n",nativeQuery = true)
    void insertNewWeek();
}
