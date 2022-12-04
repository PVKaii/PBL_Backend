package com.example.pbl_api.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private String token;

    private String type="Bearer";

    private UserModel userModel;

    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(String token, UserModel userModel, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.roles = roles;
//        if(userModel.getGender()==null||userModel.getAddress()==null||userModel.getDateOfBirth()==null||
//                userModel.getPhoneNumber()==null
//        ){
//            this.userModel=null;
//        }
//        else{
//            this.userModel = userModel;
//        }
        this.userModel=userModel;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
