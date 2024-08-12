package com.example.samuraitravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReviewForm;
import com.example.samuraitravel.repository.ReviewRepository;

@Controller
@RequestMapping("/review")
public class ReviewController {
private final ReviewRepository reviewRepository;
	
	public ReviewController(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
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
	
    @PostMapping("/review/contribution")
    public String submitReviewForm(ReviewForm reviewForm, BindingResult bindingResult, Model model) {
//    	if(bindingResult.hasErrors()) {
//    		return "review";
//    	}
//    	
    	return "redirect:/thankyou";
    }
	
	
//	@GetMapping("/edit")
//	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
//		Review user = reviewRepository.getReferenceById(userDetailsImpl.getUser().getId());
//		UserEditForm userEditForm = new ReviewEditForm(user.getId(), user.getName());
//		
//		model.addAttribute("userEditForm", userEditForm);
//		
//		return "user/edit";
//	}

}



