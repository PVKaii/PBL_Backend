package com.example.pbl_api.entity;


import com.example.pbl_api.model.UserModel;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "email",unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<UserOrder> userOrders;

    @OneToMany(mappedBy = "user")
    private Set<Cart> carts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_account_id",referencedColumnName = "id")
    private UserAccount userAccount;


    public User(String name, LocalDate dateOfBirth, String address, String phoneNumber, Boolean gender,String email, UserAccount userAccount) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.userAccount = userAccount;
        this.email=email;
    }

    public User(String email, UserAccount userAccount) {
        this.email = email;
        this.userAccount = userAccount;
    }

    public User() {

    }

    public User(long id) {
        this.id = id;
    }

    public User(UserModel userModel) {
        this.name = userModel.getName();
        this.dateOfBirth = userModel.getDateOfBirth();
        this.address = userModel.getAddress();
        this.phoneNumber = userModel.getPhoneNumber();
        this.gender = userModel.getGender();
        this.email=userModel.getEmail();
        this.userAccount = new UserAccount(
                userModel.getUserAccount().getUsername(),
                userModel.getUserAccount().getPassword(),false,false,
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserOrder> getOrders() {
        return userOrders;
    }

    public void setOrders(Set<UserOrder> userOrders) {
        this.userOrders = userOrders;
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

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }
}
