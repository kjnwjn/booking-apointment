package com.quanpham.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quanpham.demo.models.TransCounter;

public interface ITransCounterData extends JpaRepository<TransCounter, Long> {
    @Query(value = "select * from trans_counter tc where tc.id_user in" +
            "(select up.id_user  from users_product up where up.id_product = :idProduct) " +
            "Order by num_of_ticket ASC;", nativeQuery = true)
    List<TransCounter> findByIdProduct(@Param("idProduct") Long idProduct);

    @Modifying
    @Query(value = "UPDATE trans_counter SET num_of_ticket = :numOfTicket WHERE trans_counter.id = :id", nativeQuery = true)
    int updateByIdTicket(@Param("numOfTicket") Long numOfTicket, @Param("id") Long id);
}
