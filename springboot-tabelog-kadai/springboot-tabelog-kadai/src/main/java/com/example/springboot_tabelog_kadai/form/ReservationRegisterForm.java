package com.example.springboot_tabelog_kadai.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
    private Integer storeId;
	
	private Integer userId;
	
	private String dayDate;
	
	private String timeDate;
	
	private Integer numberOfPeople;


}
