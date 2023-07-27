package com.quanpham.demo.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String categoryName;
    @Column(name = "description")
    private String desc;
    @OneToMany(mappedBy = "idCategory", cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Product> products = new HashSet<Product>(0);
    @OneToMany(mappedBy = "categoryId", cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<BankAdmin> bankAdmin = new HashSet<BankAdmin>(0);

    public Category(Long id, String categoryName, String desc) {
        this.id = id;
        this.categoryName = categoryName;
        this.desc = desc;
    }

}
