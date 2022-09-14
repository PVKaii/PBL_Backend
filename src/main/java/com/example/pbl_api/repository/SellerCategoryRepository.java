package com.example.pbl_api.repository;

import com.example.pbl_api.entity.SellerCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerCategoryRepository extends CrudRepository<SellerCategory,Integer> {
    SellerCategory findSellerCategoryByName(String name);

    SellerCategory findSellerCategoryById(int id);

}
