package com.example.springboot_tabelog_kadai.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_tabelog_kadai.entity.Review;
import com.example.springboot_tabelog_kadai.entity.Store;


public interface ReviewRepository extends JpaRepository<Review, Integer>{
	 public Page<Review> findByStoreId(Integer storeId, Pageable pageable);
	    public Page<Review> findTop10ByOrderByCreatedAtDesc(Store storeId, Pageable pageable);
		public List<Review> findTop6ByStoreIdOrderByCreatedAtDesc(Integer storeId);
	

}
