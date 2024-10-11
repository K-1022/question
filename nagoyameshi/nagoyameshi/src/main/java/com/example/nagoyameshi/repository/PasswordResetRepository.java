package com.example.nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.PasswordResetToken;
import com.example.nagoyameshi.entity.User;

public interface PasswordResetRepository extends JpaRepository< PasswordResetToken, Integer> {

	public PasswordResetToken findByToken(String token);

	public PasswordResetToken findByUser(User user);
}


