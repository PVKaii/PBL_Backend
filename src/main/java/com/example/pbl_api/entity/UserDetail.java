package com.example.pbl_api.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_detail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dateofbirth")
    private Date dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "gender")
    private Boolean gender;

    @OneToOne(mappedBy = "userDetail")
    private User user;
}
