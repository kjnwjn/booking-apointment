package com.quanpham.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quanpham.demo.models.Roles;

public interface IRolesData extends JpaRepository<Roles, Long> {

}
