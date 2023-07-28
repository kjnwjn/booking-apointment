package com.quanpham.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quanpham.demo.models.BankAdmin;

public interface IBankAdminData extends JpaRepository<BankAdmin, Long> {
    Optional<BankAdmin> findByUsername(String username);

}
