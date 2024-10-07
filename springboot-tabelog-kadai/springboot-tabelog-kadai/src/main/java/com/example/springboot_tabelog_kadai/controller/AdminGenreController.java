package com.example.springboot_tabelog_kadai.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springboot_tabelog_kadai.entity.Genre;
import com.example.springboot_tabelog_kadai.form.GenreRegisterForm;
import com.example.springboot_tabelog_kadai.repository.GenreRepository;
import com.example.springboot_tabelog_kadai.service.GenreService;

@Controller
@RequestMapping("/admin/genre")
public class AdminGenreController {
	private final GenreRepository genreRepository;
	private final GenreService genreService; 
	
	public AdminGenreController(GenreRepository genreRepository, GenreService genreService) {
		this.genreRepository = genreRepository;
		this.genreService = genreService;
		
	}
	
	@GetMapping
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {
		Page<Genre> genrePage;
		
		if (keyword != null && !keyword.isEmpty()) {
            genrePage = genreRepository.findByGenreLike("%" + keyword + "%", pageable);                
        } else {
            genrePage = genreRepository.findAll(pageable);
        }  
		
		model.addAttribute("genrePage", genrePage);
		model.addAttribute("keyword", keyword);
		
		return "admin/genre/index";
	}
	
	 @GetMapping("/register")
	    public String register(Model model) {
	        model.addAttribute("genreRegisterForm", new GenreRegisterForm());
	        return "admin/genre/register";
	    } 
	 
	 @PostMapping("/create")
     public String create(@ModelAttribute @Validated GenreRegisterForm houseRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {        
         if (bindingResult.hasErrors()) {
             return "admin/genre/register";
         }
         
         genreService.create(houseRegisterForm);
         redirectAttributes.addFlashAttribute("successMessage", "ジャンルを追加しました。");    
         
         return "redirect:/admin/genre";
     }   
	 
	 @PostMapping("/{id}/delete")
     public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {        
         genreRepository.deleteById(id);
                 
         redirectAttributes.addFlashAttribute("successMessage", "ジャンルを削除しました。");
         
         return "redirect:/admin/genre";
     }    

}
