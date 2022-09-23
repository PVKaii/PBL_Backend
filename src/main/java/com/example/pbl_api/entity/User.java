package com.example.pbl_api.entity;


import com.example.pbl_api.model.UserModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "gender")
    private Boolean gender;

    @OneToMany(mappedBy = "user")
    private Set<Bill> bills;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_account_id",referencedColumnName = "id")
    private UserAccount userAccount;


    public User(String name, LocalDate dateOfBirth, String address, String phoneNumber, Boolean gender, UserAccount userAccount) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.userAccount = userAccount;
    }

    public User() {

    }

    public User(UserModel userModel) {
        this.name = userModel.getName();
        this.dateOfBirth = userModel.getDateOfBirth();
        this.address = userModel.getAddress();
        this.phoneNumber = userModel.getPhoneNumber();
        this.gender = userModel.getGender();
        this.userAccount = new UserAccount(
                userModel.getUserAccount().getUsername(),
                userModel.getUserAccount().getPassword(),
                userModel.getUserAccount().getAuthorities().stream().map(
                        grantedAuthority -> new Role(Integer.parseInt(grantedAuthority.getAuthority()))
                ).toList()
        );
    }

    public void editUser(UserModel userModel){
        this.name = userModel.getName();
        this.dateOfBirth = userModel.getDateOfBirth();
        this.address = userModel.getAddress();
        this.phoneNumber = userModel.getPhoneNumber();
        this.gender = userModel.getGender();
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public String getName() {
        return name;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getGender() {
        return gender;
    }
}
