package com.quanpham.demo.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.relational.core.mapping.MappedCollection;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
// @NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idProduct;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String desc;
    @Column(name = "id_category")
    private Long idCategory;

    public Product() {
    }

    public Product(String productName, String desc, Long idCategory) {
        this.productName = productName;
        this.desc = desc;
        this.idCategory = idCategory;
    }

}
