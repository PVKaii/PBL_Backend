package com.example.pbl_api.repository;

import com.example.pbl_api.model.Warranty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyRepository extends CrudRepository<Warranty,Integer> {
}
