package com.example.nagoyameshi.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GenreRegisterForm {
	@NotBlank(message = "ジャンル名を入力してください。")
    private String genre;


}
