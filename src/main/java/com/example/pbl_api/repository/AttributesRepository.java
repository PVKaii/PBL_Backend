package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Attributes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AttributesRepository extends CrudRepository<Attributes,Integer> {
    Attributes findAttributesByName(String name);

    List<Attributes> findAttributesByOptionGroupId(int id);

    @Query(value = "select attributes.id, attributes.name , option_group_id from attributes\n" +
            "join option_group on attributes.option_group_id = option_group.id\n" +
            "join category on option_group.category_id = category.id\n" +
            "where category.name = ?3 and option_group.name=?2 and attributes.name =?1",nativeQuery = true)
    Attributes findAttributesByNameAndOptionGroupNameAndCategoryName(String attribute , String optionGroup,String category);
}
