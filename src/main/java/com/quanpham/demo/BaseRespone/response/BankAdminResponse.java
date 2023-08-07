package com.quanpham.demo.BaseRespone.response;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.quanpham.demo.models.Roles;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BankAdminResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String status;
    private int rate;
    private List<Roles> role;
}
