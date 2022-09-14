package com.example.pbl_api.repository;

import com.example.pbl_api.entity.OptionGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionGroupRepository extends CrudRepository<OptionGroup,Integer> {
    OptionGroup findOptionGroupByNameAndCategoryName(String name1,String name2);

    List<OptionGroup> findOptionGroupsByCategoryId(int id);
}
