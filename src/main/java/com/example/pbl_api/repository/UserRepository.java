package com.example.pbl_api.repository;

import com.example.pbl_api.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User,Long> {
    User findUserById(long id);

    @Query(value = "select \n" +
            "user.id,address,dateofbirth,gender,name,phonenumber,email,user_account_id\n" +
            " from\n" +
            "user join user_account on user.id=user_account.id\n" +
            "where user_account.username=?1",nativeQuery = true)
    User findUserByAccountName(String name);

    User findUserByEmail(String email);

    User findUserByUserAccountVerifyCode(String code);
}
