package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.po.Item;
import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByCategoryId(Integer categoryId);
}
