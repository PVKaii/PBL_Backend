package com.example.pbl_api.config;

import com.example.pbl_api.contants.RandomPassword;
import com.example.pbl_api.entity.Role;
import com.example.pbl_api.entity.User;
import com.example.pbl_api.entity.UserAccount;
import com.example.pbl_api.model.OAuth2UserModel;
import com.example.pbl_api.model.UserAccountModel;
import com.example.pbl_api.model.UserModel;
import com.example.pbl_api.repository.UserRepository;
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
    UserRepository userRepository;

    @Autowired
    RandomPassword randomPassword;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        for (Cookie cookie:
             request.getCookies()) {
            System.out.println(cookie.getValue());
        }

        OAuth2UserModel authen = (OAuth2UserModel) authentication.getPrincipal();
        String name = authen.getName();
        String email = authen.getEmail();
        handleProvider(email,name);
        User user = userRepository.findUserByEmail(email);
        response.sendRedirect("http://localhost:3000/login/oauth2?code="+user.getUserAccount().getUsername()+"@"+email);
    }

    void handleProvider(String email,String name){
        if(userRepository.findUserByEmail(email)!=null) return ;
        String username=new BCryptPasswordEncoder(10).encode(email);
        String password=new BCryptPasswordEncoder(10).encode(email);
        List<Role> roles= new ArrayList<>();
        roles.add(new Role(2));
        UserAccount account = new UserAccount(username,password,true,true,roles);
        User user = new User(email,account);
        userRepository.save(user);
    }

}
