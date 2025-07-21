package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.po.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}