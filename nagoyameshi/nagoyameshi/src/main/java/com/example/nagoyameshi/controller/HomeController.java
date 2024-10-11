package com.example.nagoyameshi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.nagoyameshi.entity.Genre;
import com.example.nagoyameshi.entity.Store;
import com.example.nagoyameshi.repository.GenreRepository;
import com.example.nagoyameshi.repository.StoreRepository;

@Controller
public class HomeController {
	private final StoreRepository storeRepository; 
	private final GenreRepository genreRepository;

    
    public HomeController(StoreRepository storeRepository, GenreRepository genreRepository) {
        this.storeRepository = storeRepository; 
        this.genreRepository = genreRepository;
       
    } 
    
	@GetMapping("/")
	 public String index(Model model) {
	     List<Store> newStores = storeRepository.findTop10ByOrderById();
	     List<Genre> newGenre = genreRepository.findByOrderById();
	     
	     model.addAttribute("newStores", newStores); 
	     model.addAttribute("newGenre", newGenre);
	     
	     
		return "index";
	}

}
