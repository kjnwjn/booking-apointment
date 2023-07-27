package com.quanpham.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quanpham.demo.models.Category;

public interface ICategoryData extends JpaRepository<Category, Long> {

}
