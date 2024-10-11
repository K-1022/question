package com.example.nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer> {

}
