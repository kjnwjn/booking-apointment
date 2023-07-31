package com.quanpham.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quanpham.demo.models.Roles;

public interface IRolesData extends JpaRepository<Roles, Long> {
    @Query(value = " SELECT * FROM roles r " +
            " WHERE r.id IN (SELECT ur.role_id FROM user_roles ur WHERE ur.user_id = :userId) ", nativeQuery = true)
    List<Roles> findByUserId(@Param("userId") Long userId);
}
