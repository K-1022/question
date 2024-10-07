package com.example.springboot_tabelog_kadai.controller;

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

import com.example.springboot_tabelog_kadai.entity.Review;
import com.example.springboot_tabelog_kadai.entity.Store;
import com.example.springboot_tabelog_kadai.entity.User;
import com.example.springboot_tabelog_kadai.form.ReviewEditForm;
import com.example.springboot_tabelog_kadai.form.ReviewForm;
import com.example.springboot_tabelog_kadai.repository.ReviewRepository;
import com.example.springboot_tabelog_kadai.repository.StoreRepository;
import com.example.springboot_tabelog_kadai.repository.UserRepository;
import com.example.springboot_tabelog_kadai.security.UserDetailsImpl;
import com.example.springboot_tabelog_kadai.service.ReviewService;

@Controller

public class ReviewController {
private final ReviewRepository reviewRepository;
private final ReviewService reviewService;
private final StoreRepository storeRepository;
private final UserRepository userRepository;
	
	public ReviewController(ReviewRepository reviewRepository, StoreRepository storeRepository,ReviewService reviewService, UserRepository userRepository) {
		
		this.reviewRepository = reviewRepository;
		this.storeRepository = storeRepository;
		this.reviewService = reviewService;
		this.userRepository = userRepository;
		
	}  
	
	@GetMapping("/templates/stores/{storeId}/review")
	public String index(@PathVariable(name = "storeId") Integer storeId,
			            @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			            Model model) 
	{
		Page<Review> reviewPage = reviewRepository.findByStoreId(storeId, pageable);
		Store store = storeRepository.getReferenceById(storeId);
		
		model.addAttribute("reviewPage", reviewPage);
		model.addAttribute("store", store);
		
		return "review/index";
	}   
	
	@GetMapping("stores/{storeId}/review/contribution")
	public String contribution(@PathVariable("storeId") Integer storeId, Model model) {
		Store store = storeRepository.getReferenceById(storeId);
		model.addAttribute("reviewForm", new ReviewForm());
		model.addAttribute("store", store);
		
		return "review/contribution";
	}
	
    @PostMapping("/stores/{storeId}/review/create")
    public String create(@PathVariable(name = "storeId") Integer storeId,
    		             @ModelAttribute @Validated ReviewForm reviewForm, 
    		             BindingResult bindingResult,
    		             @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
    		             RedirectAttributes redirectAttributes,
    		             Model model) 
    {
    	
    	Store store = storeRepository.getReferenceById(storeId);
    	User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());

    	
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("reviewForm", reviewForm);
    		System.out.println(bindingResult);
    		return "review/contribution";
    	}
    	
    	reviewService.create(reviewForm, user, store);
    	redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
        
    	return "redirect:/stores/{storeId}";
    }
	
	@GetMapping("/stores/{storeId}/review/{reviewId}/edit")
	public String edit(@PathVariable(name = "storeId") Integer storeId,
			           @PathVariable(name = "reviewId") Integer reviewId,
			           Model model) {
		Store store = storeRepository.getReferenceById(storeId);
		Review review = reviewRepository.getReferenceById(reviewId);
		
		ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getStar(), review.getComments());
		
		model.addAttribute("store", store);
		model.addAttribute("review", review);
		model.addAttribute("reviewEditForm", reviewEditForm);
		
		return "review/edit";
	}
	    
	@PostMapping("/stores/{storeId}/review/{reviewId}/update")
	public String update(@PathVariable(name = "storeId") Integer storeId,
			             @PathVariable(name = "reviewId") Integer reviewId,
			             @ModelAttribute @Validated ReviewEditForm reviewEditForm, 
			             BindingResult bindingResult, 
			             @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			             RedirectAttributes redirectAttributes)
	{
		Store store = storeRepository.getReferenceById(storeId);
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		
    	System.out.println(reviewEditForm);
		reviewService.update(reviewEditForm, user, store);
		redirectAttributes.addFlashAttribute("successMessage","レビューを編集しました。");
		
		return "redirect:/stores/{storeId}";
	}

	@PostMapping("stores/{storeId}/review/{reviewId}/delete")
	public String delete(@PathVariable(name = "storeId") Integer storeId,
			             @PathVariable(name = "reviewId") Integer id, 
			             RedirectAttributes redirectAttributes) {
		reviewRepository.deleteById(id);
		
		redirectAttributes.addFlashAttribute("successMessage", "民宿を削除しました。");
		
		return "redirect:/stores/{storeId}";
	}
}