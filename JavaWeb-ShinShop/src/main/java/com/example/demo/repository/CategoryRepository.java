package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.po.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Optional<Category> findByName(String name);
}
