package com.example.springboot_tabelog_kadai.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StoreRegisterForm {
	@NotBlank(message = "店舗名を入力してください。")
	private String name;
	
	@NotBlank(message = "ジャンルを選択してください。")
	private String genre;
	
	private MultipartFile imageFile;
	
	@NotBlank(message = "説明を入力してください。")
	private String description;
	
	@NotNull(message = "予算を入力してください。")
	@Min(value = 1, message = "定員は1円以上に設定してください。")
	private Integer price;
	
	@NotBlank(message = "郵便番号を入力してください。")
	private String postalCode;
	
	@NotBlank(message = "住所を入力してください。")
	private String address;
	
	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNumber;
		
	@NotBlank(message = "定休日を入力してください。")
	private String holiday;
	
	@NotNull(message = "開始時間を入力してください。")
	private java.sql.Time openTime;
	
	@NotNull(message = "終了時間を入力してください。")
	private java.sql.Time closeTime;
	
}
