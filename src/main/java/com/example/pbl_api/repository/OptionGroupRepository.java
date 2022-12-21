package com.example.pbl_api.repository;

import com.example.pbl_api.entity.OptionGroup;
import com.example.pbl_api.project_interface.IReports;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


public interface OptionGroupRepository extends CrudRepository<OptionGroup,Integer> {
    OptionGroup findOptionGroupByNameAndCategoryName(String name1,String name2);

    List<OptionGroup> findOptionGroupsByCategoryId(int id);

    @Query(value = "SELECT option_group.id ,option_group.name ,option_group.category_id FROM attributes join option_group on attributes.option_group_id= option_group.id\n" +
            "group by option_group.name\n" +
            "having count(option_group.name)<15\n " +
            "and count(option_group.name)>1\n" +
            "and option_group.category_id=?1",nativeQuery = true)
    Set<OptionGroup> getListOptionToShow(int categoryId);
}
