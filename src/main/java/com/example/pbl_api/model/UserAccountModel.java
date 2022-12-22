package com.example.pbl_api.model;

import com.example.pbl_api.entity.Bill;
import com.example.pbl_api.entity.Role;
import com.example.pbl_api.entity.User;
import com.example.pbl_api.entity.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserAccountModel implements UserDetails {
    private Long id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> roles;



    public UserAccountModel(Long id, String username, String password, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }



    public static UserAccountModel build(UserAccount userAccount){
//        System.out.println(userAccount.getRoles());
        List<GrantedAuthority> authorities = userAccount.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return new UserAccountModel(userAccount.getId(),userAccount.getUsername(),userAccount.getPassword(),authorities);
    }

    public Long getId() {
        return id;
    }

    public UserAccountModel() {
    }

    public UserAccountModel(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {

        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {

        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
