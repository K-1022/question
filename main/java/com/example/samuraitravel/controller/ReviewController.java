package com.example.samuraitravel.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.security.UserDetailsImpl;
import com.example.samuraitravel.service.ReviewService;

@Controller

public class ReviewController {
private final ReviewRepository reviewRepository;
private final ReviewService reviewService;
private final HouseRepository houseRepository;
	
	public ReviewController(ReviewRepository reviewRepository, HouseRepository houseRepository,ReviewService reviewService ) {
		
		this.reviewRepository = reviewRepository;
		this.houseRepository = houseRepository;
		this.reviewService = reviewService;
		
	}
	
	@GetMapping("/review")
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Review> reviewPage = reviewRepository.findAll(pageable);
	
		model.addAttribute("reviewPage", reviewPage);
		
		return "review/index";
	}   
	
	@GetMapping("houses/{houseId}/review/contribution")
	public String contribution(@PathVariable("houseId") Integer houseId, Model model) {
		House house = houseRepository.getReferenceById(houseId);
		model.addAttribute("reviewForm", new ReviewForm());
		model.addAttribute("house", house);
		
		return "review/contribution";
	}
	
    @PostMapping("/houses/{houseId}/review/create")
    public String create(@PathVariable(name = "houseId") Integer houseId,
    		             @ModelAttribute @Validated ReviewForm reviewForm, 
    		             BindingResult bindingResult,
    		             @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
    		             RedirectAttributes redirectAttributes,
    		             Model model) 
    {
    	
    	House house = houseRepository.getReferenceById(houseId);
    	
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("reviewForm", reviewForm);
    		
    		return "review/index";
    	}
    	
    	User user = userDetailsImpl.getUser();
    	
    	
    	reviewService.create(reviewForm, user, house);
    	redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
        
    	return "redirect:/review";
    }
	
	@GetMapping("houses/{houseId}/review/{reviewId}/edit")
	public String edit(@PathVariable(name = "houseId") Integer houseId, Model model) {
		House house = houseRepository.getReferenceById(houseId);
		Review review = reviewRepository.getReferenceById(houseId);
		
		ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getStar(), review.getComments());
		
		model.addAttribute("house", house);
		model.addAttribute("reviewEditForm", reviewEditForm);
		
		return "review/edit";
	}
	    
	@PostMapping("houses/{houseId}/review/{reviewId}/update")
	public String update(@ModelAttribute @Validated ReviewEditForm reviewEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		reviewService.update(reviewEditForm);
		redirectAttributes.addFlashAttribute("successMessage","レビューを編集しました。");
		
		return "redirect:/review";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		reviewRepository.deleteById(id);
		
		redirectAttributes.addFlashAttribute("successMessage", "民宿を削除しました。");
		
		return "redirect:/houses/show";
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