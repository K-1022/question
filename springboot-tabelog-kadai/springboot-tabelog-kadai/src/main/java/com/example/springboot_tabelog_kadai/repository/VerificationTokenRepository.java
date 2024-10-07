package com.example.springboot_tabelog_kadai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_tabelog_kadai.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository< VerificationToken, Integer>{
	public VerificationToken findByToken(String token);

}
