package com.quanpham.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quanpham.demo.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = " select * from roles where id in " +
            " (select ur.role_id from user_roles ur where ur.user_id  = 1) ", nativeQuery = true)
    List<Role> findByUserId(long id);
}
