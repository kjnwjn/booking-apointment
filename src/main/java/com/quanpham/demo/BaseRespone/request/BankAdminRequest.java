package com.quanpham.demo.BaseRespone.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BankAdminRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username")

    private String username;
    @Column(name = "password")

    private String password;
    @Column(name = "fullname")

    private String fullname;
    @Column(name = "phonenumber")

    private String phone;
    private String status;

    private String email;
    private String role;
    @Column(name = "category_id")
    private Long categoryId;
    private int rate;
}
