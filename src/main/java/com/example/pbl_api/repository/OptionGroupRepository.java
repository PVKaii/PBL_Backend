package com.example.pbl_api.repository;

import com.example.pbl_api.entity.OptionGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionGroupRepository extends CrudRepository<OptionGroup,Integer> {
    OptionGroup findOptionGroupByNameAndCategoryName(String name1,String name2);
}
