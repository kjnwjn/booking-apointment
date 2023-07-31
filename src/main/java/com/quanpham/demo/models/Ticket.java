package com.quanpham.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.quanpham.demo.enums.StatusTicketEnum;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Ticket extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idTransCounter;
    private String customerName;
    private String customerEmail;
    private StatusTicketEnum.StatusTicket status;
    private int rate;
    @OneToOne()
    @JoinColumn(name = "id_product")
    private Product product;

    public Ticket(Long idTransCounter, Product product, String customerName, String customerEmail,
            StatusTicketEnum.StatusTicket status,
            int rate) {
        this.idTransCounter = idTransCounter;
        this.product = product;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.status = status;
        this.rate = rate;
    }

    @PrePersist
    @Override
    public void prePersist() {
        // super để gọi thuật toán từ cha
        super.prePersist();

    }

    @Override
    public void preUpdate() {
        // TODO Auto-generated method stub
        super.preUpdate();
    }

}
