package com.example.pbl_api.config;

import com.example.pbl_api.contants.RandomPassword;
import com.example.pbl_api.model.OAuth2UserModel;
import com.example.pbl_api.model.UserAccountModel;
import com.example.pbl_api.model.UserModel;
import com.example.pbl_api.service.impl.JwtService;
import com.example.pbl_api.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserService userService;

    @Autowired
    RandomPassword randomPassword;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        for (Cookie cookie:
             request.getCookies()) {
            System.out.println(cookie.getValue());
        }

        OAuth2UserModel user = (OAuth2UserModel) authentication.getPrincipal();
        String name = user.getName();
        String email = user.getEmail();
        handleProvider(email,name);
        response.sendRedirect("http://localhost:3000/login/oauth2?email="+email);
    }

    void handleProvider(String email,String name){
        if(userService.loadUserDetailByAccoutName(email)!=null) return ;
        String username=email;
        String password=new BCryptPasswordEncoder(10).encode(randomPassword.Random());
        List<String> roles= new ArrayList<>();
        roles.add("2");
        UserModel newUserDetail=new UserModel(name);
        UserAccountModel newUserAccout = new UserAccountModel(username,password,roles,email);
        userService.saveNewUser(newUserDetail,newUserAccout);
    }

}
