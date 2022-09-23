package com.example.pbl_api.repository;

import com.example.pbl_api.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
    Role findRoleByName(String name);
}
