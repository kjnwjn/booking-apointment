package com.quanpham.demo.models;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.quanpham.demo.enums.RoleUserEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name")
    private RoleUserEnum.RoleUser name;
    @Column(name = "description")
    private String desc;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // @ManyToMany(mappedBy = "roles")
    // private Set<BankAdmin> bankAdmin;

}
