package com.example.springboot_tabelog_kadai.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_tabelog_kadai.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{
	public Page<Genre> findByGenreLike(String keyword, Pageable pageable);
	
	public List<Genre> findByOrderById();
	}



