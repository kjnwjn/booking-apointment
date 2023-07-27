package com.quanpham.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quanpham.demo.models.Ticket;

public interface ITicketData extends JpaRepository<Ticket, Long> {
    @Query(value = "SELECT * FROM ticket t WHERE t.id_user = :idUser", nativeQuery = true)
    List<Ticket> findByIdUser(String idUser);

    @Query(value = "SELECT * FROM ticket t WHERE t.id_product = :idProduct", nativeQuery = true)
    List<Ticket> findByIdProduct(Long idProduct);

}