package com.example.pbl_api.model;

import com.example.pbl_api.entity.UserAccount;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class RoleModel {

    private int id;

    private String name;

    private List<UserAccountModel> userAccounts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserAccountModel> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccountModel> userAccounts) {
        this.userAccounts = userAccounts;
    }
}
