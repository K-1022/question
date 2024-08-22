package com.example.samuraitravel.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
     //public Page<Review> findByHouseId(Integer houseId, Pageable pageable);
     public List<Review> findByHouseId(Integer id, Pageable pageable);
     public List<Review> findTop6ByOrderByCreatedAtDesc(Pageable pageable);
	public List<Review> findTop6ByHouseIdOrderByCreatedAtDesc(Integer id);
	public Review getHouseId();



}

