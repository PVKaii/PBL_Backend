package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Attributes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AttributesRepository extends CrudRepository<Attributes,Integer> {
    Attributes findAttributesByName(String name);

    List<Attributes> findAttributesByOptionGroupId(int id);
}
