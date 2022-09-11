package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudRepository<Brand,Integer> {
    Brand findBrandByName(String name);
}
