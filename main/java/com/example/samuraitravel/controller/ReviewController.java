package com.example.samuraitravel.controller;

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
		
		model.addAttribute("reviewPage", reviewPage);
		
		return "review/index";
	}
	
	@GetMapping("review/contribution")
	public String contribution(Model model) {
		model.addAttribute("reviewForm", new ReviewForm());
		return "review/contribution";
	}
	
    @PostMapping("/create")
    public String create(@ModelAttribute @Validated ReviewForm reviewForm, BindingResult bindingResult,  RedirectAttributes redirectAttributes) {
    	if(bindingResult.hasErrors()) {
    		return "review";
    	}
    	
    	reviewService.create(reviewForm);
    	redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
        
    	return "redirect:/review";
    }
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {
		Review review = reviewRepository.getReferenceById(id);
		ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getStar(), review.getComments());
		
		model.addAttribute("reviewEditForm", reviewEditForm);
		
		return "review/edit";
	}

}



