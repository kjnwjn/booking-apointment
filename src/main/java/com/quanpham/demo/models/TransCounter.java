package com.quanpham.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trans_counter")
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TransCounter extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long numOfTicket;
    private String status;
    @OneToOne()
    @JoinColumn(name = "id_user")
    private BankAdmin user;

    @Override
    public void prePersist() {
        // TODO Auto-generated method stub
        super.prePersist();
    }

    @Override
    public void preUpdate() {
        System.out.println("123");
        // TODO Auto-generated method stub
        super.preUpdate();
    }
}
