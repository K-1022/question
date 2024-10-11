package com.example.nagoyameshi.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Sales;
import com.example.nagoyameshi.repository.SalesRepository;

@Service
public class SalesService {
    private final SalesRepository salesRepository;
    
  public SalesService (SalesRepository salesRepository) {
 	 this.salesRepository = salesRepository;
	 	 
  }
  
  @Transactional
  public void create(Map<String, String> paymentIntentObject) {
	  Sales sales = new Sales();
	  
	  LocalDate orderDate = LocalDate.parse(paymentIntentObject.get("orderDate"));
	  Integer paid = Integer.valueOf(paymentIntentObject.get("paid"));
	  
	  sales.setOrderData(orderDate);
	  sales.setPaid(paid);
	  
	  System.out.println(orderDate);
	  System.out.println(paid);
	  
	  
	  salesRepository.save(sales);
  }
 
  
 
}


