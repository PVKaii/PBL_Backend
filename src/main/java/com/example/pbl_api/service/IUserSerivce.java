package com.example.pbl_api.service;

import com.example.pbl_api.model.UserAccountModel;
import com.example.pbl_api.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

public interface IUserSerivce extends UserDetailsService {
    UserModel loadUserDetailByAccoutName(String name);

    Collection<? extends GrantedAuthority> loadRolesByAccoutName(String name);

    UserModel saveNewUser(UserModel newUser,UserAccountModel newAccount);

    UserModel editUser(long id,UserModel user);

    UserModel findUserById(long id);


}
