package com.example.nagoyameshi.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewRegisterForm {
    private Integer storeId;
	
	private Integer userId;
	
	private String name;
	
	private String star;
	
	private String comments;
	

}
