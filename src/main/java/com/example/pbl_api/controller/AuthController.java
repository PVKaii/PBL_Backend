package com.example.pbl_api.controller;


import com.example.pbl_api.contants.RandomPassword;
import com.example.pbl_api.model.JwtResponse;
import com.example.pbl_api.model.UserAccountModel;
import com.example.pbl_api.model.UserModel;
import com.example.pbl_api.service.impl.JwtService;
import com.example.pbl_api.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder encoder;




    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserAccountModel userAccount){
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAccount.getUsername(),
                        userAccount.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=jwtService.generateTokenLogin(authentication);
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        UserModel user = userService.loadUserDetailByAccoutName(userDetails.getUsername());
        System.out.println(user.getName());
        return new ResponseEntity<>(new JwtResponse(jwt,user,userDetails.getAuthorities()), HttpStatus.OK);
    }

    @PostMapping("provider")
    public ResponseEntity<?> loginByGoogle(@RequestBody UserAccountModel account){
        String jwt=jwtService.generateOauth2TokenLogin(account.getUsername());
        Collection<? extends GrantedAuthority> userRoles = userService.loadRolesByAccoutName(account.getUsername());
        UserModel user = userService.loadUserDetailByAccoutName(account.getUsername());
        return new ResponseEntity<>(new JwtResponse(jwt,user,userRoles), HttpStatus.OK);
    }

    @PostMapping("verify")
    public ResponseEntity<?> sendEmail(@RequestHeader (name="Authorization") String token) throws MessagingException, UnsupportedEncodingException {
        token=token.replace("Bearer","");
        String username= jwtService.getUsernameFromJwtToken(token);
        userService.emailVerify(username);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }



    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody ObjectNode json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        UserModel user = userService.loadUserDetailByAccoutName(json.get("username").asText());
        if(user!= null) throw new Exception();
        String username=json.get("username").asText();
        String password=encoder.encode(json.get("password").asText());
        List<String> roles= Arrays.asList(mapper.convertValue( json.get("roles"), String[].class));
        UserModel newUserDetail=mapper.convertValue(json.get("userDetail"),UserModel.class);
        UserAccountModel newUserAccout = new UserAccountModel(username,password,roles,null);
        userService.saveNewUser(newUserDetail,newUserAccout);
        return new ResponseEntity<>(newUserDetail,HttpStatus.OK);

    }



    @PutMapping("user/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody ObjectNode json, @PathVariable(name = "id") long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        UserModel user = userService.findUserById(id);
        if(user== null) throw new Exception();
        UserModel userDetail=mapper.convertValue(json.get("userDetail"),UserModel.class);
        userService.editUser(id,userDetail);
        return new ResponseEntity<>("successs",HttpStatus.OK);

    }


}
