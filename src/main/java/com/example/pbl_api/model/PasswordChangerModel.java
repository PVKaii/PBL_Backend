package com.example.pbl_api.model;

public class PasswordChangerModel {
    private String recentPassword;
    private String newPassword;

    public PasswordChangerModel(String recentPassword, String newPassword) {
        this.recentPassword = recentPassword;
        this.newPassword = newPassword;
    }

    public String getRecentPassword() {
        return recentPassword;
    }

    public void setRecentPassword(String recentPassword) {
        this.recentPassword = recentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
