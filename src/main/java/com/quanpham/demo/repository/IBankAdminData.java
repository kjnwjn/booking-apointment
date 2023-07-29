package com.quanpham.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quanpham.demo.models.BankAdmin;
import com.quanpham.demo.models.Roles;

public interface IBankAdminData extends JpaRepository<BankAdmin, Long> {

    Optional<BankAdmin> findByEmail(String email);

    // List<Roles> findRoles_BankAdmin(BankAdmin bankAdmin);

}
