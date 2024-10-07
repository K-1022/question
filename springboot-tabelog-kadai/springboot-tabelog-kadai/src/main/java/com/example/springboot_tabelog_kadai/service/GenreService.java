package com.example.springboot_tabelog_kadai.service;

import org.springframework.stereotype.Service;

import com.example.springboot_tabelog_kadai.entity.Genre;
import com.example.springboot_tabelog_kadai.form.GenreRegisterForm;
import com.example.springboot_tabelog_kadai.repository.GenreRepository;

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


