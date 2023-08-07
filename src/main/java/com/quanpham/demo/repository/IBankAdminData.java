package com.quanpham.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quanpham.demo.models.BankAdmin;

public interface IBankAdminData extends JpaRepository<BankAdmin, Long> {

    Optional<BankAdmin> findByEmail(String email);

    @Query(value = "select * from users u where u.id in(select up.id_user  from users_product up where up.id_product = :idProduct", nativeQuery = true)

    List<BankAdmin> findByIdProduct(@Param("idProduct") Long idProduct);

}
