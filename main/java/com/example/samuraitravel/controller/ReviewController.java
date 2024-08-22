package com.example.samuraitravel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.security.UserDetailsImpl;
import com.example.samuraitravel.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
private final ReviewRepository reviewRepository;
private final ReviewService reviewService;
private final HouseRepository houseRepository;
	
	public ReviewController(ReviewRepository reviewRepository, HouseRepository houseRepository,ReviewService reviewService ) {
		
		this.reviewRepository = reviewRepository;
		this.houseRepository = houseRepository;
		this.reviewService = reviewService;
		
	}
	
	@GetMapping
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Review> reviewPage = reviewRepository.findAll(pageable);
		List<Review> newReview = reviewRepository.findTop6ByOrderByCreatedAtDesc(pageable);
		
		model.addAttribute("reviewPage", reviewPage);
		model.addAttribute("newReview", newReview);
		
		return "review/index";
	}
	
	@GetMapping("/houses/{id}/review/input") 
	public String input(@PathVariable(name = "id") Integer id,
            @ModelAttribute @Validated ReviewForm reviewForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model)
     { 
		 House house = houseRepository.getReferenceById(id);
		 String star = reviewForm.getStar();
		 String comments = reviewForm.getComments();
		 
		 if (bindingResult.hasErrors()) {            
             model.addAttribute("house", house);            
             model.addAttribute("errorMessage", "レビューの内容に不備があります。"); 
             model.addAttribute("star", star);            
             model.addAttribute("errorMessage", "レビューの内容に不備があります。"); 
             return "review/index";
         }
		 
		 redirectAttributes.addFlashAttribute("reviewForm", reviewForm);           
		 return "redirect:/houses/{id}/review/index";
     }
	
	@GetMapping("/houses/{id}/review/contribution")
	public String confirm(@PathVariable(name = "id") Integer id,
            @ModelAttribute ReviewForm reviewForm,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,                          
            Model model) 
{ 
		House house = houseRepository.getReferenceById(id);
        User user = userDetailsImpl.getUser(); 
        
        String name = reviewForm.getName();
        String star = reviewForm.getStar();
		String comments = reviewForm.getComments();
		
		ReviewRegisterForm reviewRegisterForm = new ReviewRegisterForm(house.getId(), user.getId(), reviewForm.getName(),reviewForm.getComments(), reviewForm.getStar());
		
		model.addAttribute("house", house);
		model.addAttribute("reviewRegisterForm", reviewRegisterForm);
		
		return "review/index";
}
	
	
    @PostMapping("house/{houseId}/review/create")
    public String create(@ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm, BindingResult bindingResult,  RedirectAttributes redirectAttributes) {
    	if(bindingResult.hasErrors()) {
    		return "review/contribution";
    	}
    	
    	reviewService.create(reviewRegisterForm);
    	redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
        
    	return "redirect:/review?reviewed";
    }
	
	@GetMapping("/edit")
	public String edit(@PathVariable(name = "id") Integer Id, Model model, RedirectAttributes redirectAttributes) {
		Optional<Review> optionalReview = reviewRepository.findById(Id);
	    
	    if (optionalReview.isPresent()) {
		Review review = optionalReview.get();
		ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getStar(), review.getComments());
		
		model.addAttribute("reviewEditForm", reviewEditForm);
		
		return "review/edit";
	} else {
		 redirectAttributes.addFlashAttribute("errorMessage", "指定されたレビューが見つかりません。");
	        return "redirect:/review";
	}

	}
}

//@Controller
//public class ReviewController {
//	private final ReviewRepository reviewRepository;
//	
//	public ReviewController(ReviewRepository reviewRepository) {
//		this.reviewRepository = reviewRepository;
//	}
//	
//	@GetMapping
//	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
//		Page<Review> reviewPage = reviewRepository.findAll(pageable);
//		Page<Review> newReview = reviewRepository.findAll(pageable);
//		
//		model.addAttribute("reviewPage", reviewPage);
//		model.addAttribute("newReview", newReview);
//		
//		return "review/index";
//	}
//}