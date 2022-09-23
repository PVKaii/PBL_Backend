package com.example.pbl_api.service;

import com.example.pbl_api.model.UserAccountModel;
import com.example.pbl_api.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface IUserSerivce extends UserDetailsService {
    UserModel loadUserDetailByAccoutName(String name);

    UserModel saveNewUser(UserModel newUser,UserAccountModel newAccount);

    UserModel editUser(long id,UserModel user);

    UserModel findUserById(long id);

}
