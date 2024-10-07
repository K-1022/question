package com.example.springboot_tabelog_kadai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_tabelog_kadai.entity.PasswordResetToken;
import com.example.springboot_tabelog_kadai.entity.User;

public interface PasswordResetRepository extends JpaRepository< PasswordResetToken, Integer> {

	public PasswordResetToken findByToken(String token);

	public PasswordResetToken findByUser(User user);
}


