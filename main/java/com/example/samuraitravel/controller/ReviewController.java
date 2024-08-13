package com.example.samuraitravel.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewForm;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
private final ReviewRepository reviewRepository;
private final ReviewService reviewService;
	
	public ReviewController(ReviewRepository reviewRepository, ReviewService reviewService) {
		this.reviewRepository = reviewRepository;
		this.reviewService = reviewService;
	}
	
	@GetMapping
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Review> reviewPage = reviewRepository.findAll(pageable);
		List<Review> newReview = reviewRepository.findTop6ByOrderByCreatedAtDesc();
		
		model.addAttribute("reviewPage", reviewPage);
		model.addAttribute("newReview", newReview);
		
		return "review/index";
	}
	
	@GetMapping("/contribution")
	public String contribution(Model model) {
		model.addAttribute("reviewForm", new ReviewForm());
		return "review/contribution";
	}
	
    @PostMapping("/create")
    public String create(@ModelAttribute @Validated ReviewForm reviewForm, BindingResult bindingResult,  RedirectAttributes redirectAttributes) {
    	if(bindingResult.hasErrors()) {
    		return "review/contribution";
    	}
    	
    	reviewService.create(reviewForm);
    	redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
        
    	return "redirect:/houses/show/{id}";
    }
	
	@GetMapping("/{id}/edit")
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



