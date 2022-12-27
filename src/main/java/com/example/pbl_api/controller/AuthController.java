package com.example.pbl_api.controller;


import com.example.pbl_api.contants.RandomPassword;
import com.example.pbl_api.model.JwtResponse;
import com.example.pbl_api.model.PasswordChangerModel;
import com.example.pbl_api.model.UserAccountModel;
import com.example.pbl_api.model.UserModel;
import com.example.pbl_api.service.impl.JwtService;
import com.example.pbl_api.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import javax.servlet.UnavailableException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
public class AuthController {


    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserAccountModel userAccount){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        JwtResponse response= userService.login(userAccount,authenticationManager);
        return new ResponseEntity<>(response,httpHeaders, HttpStatus.OK);
    }

    @PutMapping("password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangerModel passwordChangerModel,
    @RequestHeader("Authorization") String token
    ) throws UnavailableException {
        if (token != null && token.startsWith("Bearer ")) {
            token=token.replace("Bearer ","");
            String username= jwtService.getUsernameFromJwtToken(token);
            userService.changePassword(username,passwordChangerModel,authenticationManager);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("invalid  token", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("password")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException, UnavailableException {
            userService.resetPassword(email);
            return new ResponseEntity<>("Kiem tra email", HttpStatus.OK);
    }

//    @PostMapping("provider")
//    public ResponseEntity<?> loginByGoogle(@RequestBody UserAccountModel account){
//        String jwt=jwtService.generateOauth2TokenLogin(account.getUsername());
//        Collection<? extends GrantedAuthority> userRoles = userService.loadRolesByAccoutName(account.getUsername());
//        UserModel user = userService.loadUserDetailByAccoutName(account.getUsername());
//        return new ResponseEntity<>(new JwtResponse(jwt,user,userRoles), HttpStatus.OK);
//    }

    @GetMapping("verify")
    public ResponseEntity<?> verify(@RequestParam("code") String code)  {
        userService.enableAccount(code);
        return new ResponseEntity<>("Xac thuc thanh cong", HttpStatus.OK);
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
        UserAccountModel newUserAccout = new UserAccountModel(username,password,roles);
        String verifyCode= userService.saveNewUser(newUserDetail,newUserAccout);
        userService.emailVerify(newUserDetail.getEmail(),verifyCode);
        return new ResponseEntity<>(verifyCode,HttpStatus.OK);

    }



    @PutMapping("user/edit")
    public ResponseEntity<?> edit(@RequestBody UserModel userDetail,@RequestHeader("Authorization") String token) throws Exception {
        if (token != null && token.startsWith("Bearer ")) {
            token=token.replace("Bearer ","");
            String username= jwtService.getUsernameFromJwtToken(token);
            userService.editUser(username,userDetail);
        }

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.findAndRegisterModules();
//        UserModel user = userService.findUserById(id);
//        if(user== null) throw new Exception();
//        userService.editUser(id,userDetail);
        return new ResponseEntity<>(userDetail,HttpStatus.OK);

    }


}
