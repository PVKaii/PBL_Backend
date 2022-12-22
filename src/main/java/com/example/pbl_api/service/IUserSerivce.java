package com.example.pbl_api.service;

import com.example.pbl_api.entity.UserAccount;
import com.example.pbl_api.model.JwtResponse;
import com.example.pbl_api.model.PasswordChangerModel;
import com.example.pbl_api.model.UserAccountModel;
import com.example.pbl_api.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

public interface IUserSerivce extends UserDetailsService {
    UserModel loadUserDetailByAccoutName(String name);

    Collection<? extends GrantedAuthority> loadRolesByAccoutName(String name);

    String saveNewUser(UserModel newUser,UserAccountModel newAccount);

    UserModel editUser(String username,UserModel user);

    UserModel findUserById(long id);

    void emailVerify(String email,String code) throws MessagingException, UnsupportedEncodingException;

    JwtResponse login(UserAccountModel user, AuthenticationManager authenticationManager);

    void enableAccount(String code);

    void changePassword(String username, PasswordChangerModel passwordChangerModel,AuthenticationManager authenticationManager);

    void resetPassword(String username) throws MessagingException, UnsupportedEncodingException;

}
