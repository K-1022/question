package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.Store;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReviewEditForm;
import com.example.nagoyameshi.form.ReviewForm;
import com.example.nagoyameshi.repository.ReviewRepository;

@Service

public class ReviewService {
private final ReviewRepository reviewRepository; 
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
		 
	}
	
	@Transactional
	public void create(ReviewForm reviewForm, User user, Store storeId) {
		Review review = new Review();
		
		review.setStar(reviewForm.getStar());
		review.setComments(reviewForm.getComments());
		review.setUser(user);
		review.setStore(storeId);
		review.setName(user.getName());
		
		reviewRepository.save(review);
	}
	
	@Transactional
	public void update(ReviewEditForm reviewEditForm, User user, Store storeId) {
		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
		
		review.setStar(reviewEditForm.getStar());
		review.setComments(reviewEditForm.getComments());
		review.setUser(user);
		review.setStore(storeId);
		review.setName(user.getName());
		
		reviewRepository.save(review);
	}

}
