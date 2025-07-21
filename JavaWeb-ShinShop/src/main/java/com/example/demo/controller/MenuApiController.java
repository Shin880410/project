package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.po.Category;
import com.example.demo.model.po.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@RestController
@RequestMapping("/api")
public class MenuApiController {

    @Autowired private CategoryRepository categoryRepo;
    @Autowired private ItemRepository itemRepo;

    @GetMapping("/categories")
    public List<Category> categories() {
        return categoryRepo.findAll();
    }

    @GetMapping("/items")
    public List<Item> items(@RequestParam(name="categoryId", required=false) Integer categoryId) {
        return categoryId == null ? itemRepo.findAll() : itemRepo.findByCategoryId(categoryId);
    }
}

