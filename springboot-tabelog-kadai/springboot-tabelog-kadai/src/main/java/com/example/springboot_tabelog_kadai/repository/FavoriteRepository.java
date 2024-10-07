package com.example.springboot_tabelog_kadai.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_tabelog_kadai.entity.Favorite;
import com.example.springboot_tabelog_kadai.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{
	public Page<Favorite> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
	public List<Favorite> findByStoreIdOrderByCreatedAtDesc(Integer storeId);
	public List<Favorite> findByStoreIdAndUserId(Integer storeId, User userId);
	public Favorite findByStoreId(Integer storeId);

}
