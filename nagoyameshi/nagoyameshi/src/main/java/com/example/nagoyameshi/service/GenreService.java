package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Genre;
import com.example.nagoyameshi.form.GenreRegisterForm;
import com.example.nagoyameshi.repository.GenreRepository;

import jakarta.transaction.Transactional;

@Service
public class GenreService {
	private final GenreRepository genreRepository;    
    
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;        
    }    
    
    @Transactional
    public void create(GenreRegisterForm genreRegisterForm) {
        Genre genre = new Genre();        
        
        genre.setGenre(genreRegisterForm.getGenre());                
                    
        genreRepository.save(genre);
    }  
    
         
    } 


