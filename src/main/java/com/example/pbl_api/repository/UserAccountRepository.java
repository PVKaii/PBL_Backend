package com.example.pbl_api.repository;

import com.example.pbl_api.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount,Long> {
    UserAccount findUserAccountByUsername(String username);
}
