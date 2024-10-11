package com.example.nagoyameshi.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterForm {
	
	private Integer id;

	private String name;
	
	private String postalCode;
	
	private String address;
    
    private String phoneNumber;
    
    private String email;
        
    private String furigana;
    
    private String password;
    
    private String consent;
  


    
}