package com.example.pbl_api.repository;

import com.example.pbl_api.model.Attributes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributesRepository extends CrudRepository<Attributes,Integer> {
}
