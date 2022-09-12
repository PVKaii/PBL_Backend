package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {
    Category findCategoryByName(String name);

    Category findCategoryById(int id);
}
