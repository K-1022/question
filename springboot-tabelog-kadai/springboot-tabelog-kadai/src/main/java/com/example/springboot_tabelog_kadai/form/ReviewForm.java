package com.example.springboot_tabelog_kadai.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewForm {
	@NotBlank(message = "評価を選択してください。")
	private String star;
	
	@NotBlank(message = "コメントを入力してください")
	private String comments;

}
