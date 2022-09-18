package com.example.pbl_api.model;

import com.example.pbl_api.entity.User;

public class UserModel {
    long id;

    public UserModel(User user){
        this.id=1;
    }

    public UserModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
