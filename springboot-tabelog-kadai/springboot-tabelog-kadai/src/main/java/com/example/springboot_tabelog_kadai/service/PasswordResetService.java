package com.example.springboot_tabelog_kadai.service;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springboot_tabelog_kadai.entity.PasswordResetToken;
import com.example.springboot_tabelog_kadai.entity.User;
import com.example.springboot_tabelog_kadai.repository.PasswordResetRepository;
import com.example.springboot_tabelog_kadai.repository.UserRepository;

import jakarta.mail.MessagingException;
 
@Service
public class PasswordResetService {
	private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordResetRepository passwordResetRepository;
    private final PasswordEncoder passwordEncoder;

public PasswordResetService(UserRepository userRepository, EmailService emailService, PasswordResetRepository passwordResetRepository, PasswordEncoder passwordEncoder) {
	this.userRepository = userRepository;
	this.emailService = emailService;
	this.passwordResetRepository = passwordResetRepository;
	this.passwordEncoder = passwordEncoder;
}

public void sendResetEmail(String email, String baseUrl) throws MessagingException, UnsupportedEncodingException {
	User user = userRepository.findByEmail(email);
	PasswordResetToken resetToken = new PasswordResetToken();
	if (user != null) {
		// 既存のトークンを削除または更新
		PasswordResetToken existingToken = passwordResetRepository.findByUser(user);
		if (existingToken != null) {
			passwordResetRepository.delete(existingToken);
		}
		String token = UUID.randomUUID().toString();
		resetToken.setToken(token);
		resetToken.setUser(user);
		passwordResetRepository.save(resetToken);

		String resetLink = baseUrl + "/updatePassword?token=" + token;
		emailService.sendResetEmail(email, resetLink);
	}
}

public boolean isValidToken(String Token) {
	PasswordResetToken resetToken = passwordResetRepository.findByToken(Token);

	return resetToken != null;
}

// パスワード再設定処理
public boolean updatePassword(String token, String newPassword) {
	User user = passwordResetRepository.findByToken(token).getUser();
	if (user != null) {
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
		return true;
	}
	return false;
}


}
	