package com.quanpham.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quanpham.demo.models.Product;

public interface IProductData extends JpaRepository<Product, Long> {

    // List<Product> selectAllProducts();
    // Product selectProductById(String id);

    // List<Product> selectProductsByCondition(String condition, String value);

    // List<Product> selectProductByIdTicket(String id);

}
