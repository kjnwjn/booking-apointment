package com.quanpham.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quanpham.demo.models.BankAdmin;

public interface IBankAdminData extends JpaRepository<BankAdmin, Long> {

}
