package com.example.springboot_tabelog_kadai.form;

import com.example.springboot_tabelog_kadai.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterForm {
	

	private String name;
	
	private String furigana;
	
	private String postalCode;
	
	private String address;
    
    private String phoneNumber;
    
    private String email;
        
    private String password;    
 
    private Role role;   
    
    private Boolean enabled;
    
}