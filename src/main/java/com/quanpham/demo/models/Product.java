package com.quanpham.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
