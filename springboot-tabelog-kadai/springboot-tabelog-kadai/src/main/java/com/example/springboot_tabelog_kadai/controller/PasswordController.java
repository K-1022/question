package com.example.springboot_tabelog_kadai.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springboot_tabelog_kadai.form.ChangeForm;
import com.example.springboot_tabelog_kadai.service.PasswordResetService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PasswordController {
	private final PasswordResetService passwordResetService;

	public PasswordController(PasswordResetService passwordResetService) {
		this.passwordResetService = passwordResetService;
	}

	@GetMapping("/change")
	public String change(Model model) {
		
		model.addAttribute("changeForm", new ChangeForm());
		
		return "auth/change";
	}

	@PostMapping("/sendResetLink")
	public String sendEmail(@RequestParam("email") String email, Model model, HttpServletRequest request) {
		try {
			String url = request.getRequestURL().toString();
			String baseUrl = url.replace(request.getServletPath(), "");
			passwordResetService.sendResetEmail(email, baseUrl);
			model.addAttribute("message", "パスワードリセットリンクをメールで送信しました。");

		} catch (MessagingException | UnsupportedEncodingException e) {
			model.addAttribute("error", "メールの送信中にエラーが発生しました。もう一度お試しください。");
		}
		return "/auth/emailSend";
	}
	
	@GetMapping("/updatePassword")
	public String updatePassword(@RequestParam("token") String token, Model model) {
		if (passwordResetService.isValidToken(token)) {
			model.addAttribute("token", token);
			return "auth/updatePassword";
		} else {
			model.addAttribute("error", "無効なトークンです。もう一度お試しください。");
			return "/auth/emailSend";
		}
	}

	@PostMapping("/resetPassword")
	public String resetPassword(@RequestParam("token") String token, @RequestParam("password") String password,
			Model model) {
		if (passwordResetService.updatePassword(token, password)) {
			model.addAttribute("message", "パスワードが正常にリセットされました。");
			return "redirect:/login";
		} else {
			model.addAttribute("error", "パスワードリセット中にエラーが発生しました。");
			return "/auth/updatePassword";
		}
	}

}
