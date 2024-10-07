package com.example.springboot_tabelog_kadai.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangeForm {
	
	@NotBlank(message = "メールアドレスを入力してください。")
	@Email(message = "メールアドレスは正しい形式で入力してください。")
    private String email;

}
