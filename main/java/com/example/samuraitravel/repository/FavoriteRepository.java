package com.example.samuraitravel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;


//public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{
//	public Page<Favorite> findByUserId(User user, Pageable pageable);
//	//public Page<Review> findTop10ByOrderByCreatedAtDesc(User UserId, Pageable pageable);
//
//}

public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{
	public Page<Favorite> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
	public List<Favorite> findByHouseIdAndUserId(House house, User user);
	public List<Favorite> findByHouseIdOrderByCreatedAtDesc(Integer houseId);

}
