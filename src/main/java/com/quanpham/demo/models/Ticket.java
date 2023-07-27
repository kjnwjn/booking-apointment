package com.quanpham.demo.models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Ticket extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String idUser;
    private String customerName;
    private String customerEmail;
    private String timeline;
    private int status;
    private int rate;
    @OneToOne()
    @JoinColumn(name = "id_product")
    private Product product;

    public Ticket(String idUser, String customerName, String customerEmail,
            String timeline,
            int status, int rate) {
        this.idUser = idUser;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.timeline = timeline;
        this.status = status;
        this.rate = rate;
    }

    @PrePersist
    @Override
    public void prePersist() {
        // super để gọi thuật toán từ cha
        super.prePersist();
    }

}
