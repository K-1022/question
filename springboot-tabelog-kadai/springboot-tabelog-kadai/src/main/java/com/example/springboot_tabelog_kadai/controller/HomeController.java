package com.example.springboot_tabelog_kadai.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springboot_tabelog_kadai.entity.Store;
import com.example.springboot_tabelog_kadai.repository.StoreRepository;

@Controller
public class HomeController {
	private final StoreRepository storeRepository; 

    
    public HomeController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;  
       
    } 
    
	@GetMapping("/")
	 public String index(Model model) {
	     List<Store> newStores = storeRepository.findTop10ByOrderById();
	    
	     
	     model.addAttribute("newStores", newStores); 
	     
	     
		return "index";
	}

}
