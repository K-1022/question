package com.example.samuraitravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.form.ReservationInputForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;

@Controller
@RequestMapping("/houses")
public class HouseController {
	private final HouseRepository houseRepository;
	private final ReviewRepository reviewRepository;
	
	public HouseController(HouseRepository houseRepository, ReviewRepository reviewrepository) {
		this.houseRepository = houseRepository;
		this.reviewRepository = reviewrepository;
	}
	
	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
			            @RequestParam(name = "area", required = false) String area,
			            @RequestParam(name = "price", required = false) Integer price,
			            @RequestParam(name = "order", required = false) String order,
			            @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			            Model model)
	{
		Page<House> housePage;
		
		if(keyword != null && !keyword.isEmpty()) {
			if(order != null && order.equals("priceAsc")) {
			   housePage = houseRepository.findByNameLikeOrAddressLikeOrderByPriceAsc("%" + keyword + "%", "%" + keyword + "%", pageable);
			} else {
				housePage = houseRepository.findByNameLikeOrAddressLikeOrderByCreatedAtDesc("%" + keyword + "%", "%" + keyword + "%", pageable);
			}
		} else if (area != null && !area.isEmpty()) {
			if(order != null && order.equals("priceAsc")) {
				housePage = houseRepository.findByAddressLikeOrderByPriceAsc("%" + area + "%", pageable);
			} else {
				housePage = houseRepository.findByAddressLikeOrderByCreatedAtDesc("%" + area + "%", pageable);
			}	
		} else if (price != null) {
			if(order != null && order.equals("priceAsc")) {
			   housePage = houseRepository.findByPriceLessThanEqualOrderByPriceAsc(price, pageable);
			} else {
				housePage = houseRepository.findByPriceLessThanEqualOrderByCreatedAtDesc(price, pageable);
			}
		}
		else {
			if (order != null && order.equals("priceAsc")) {
				housePage = houseRepository.findAllByOrderByPriceAsc(pageable);
			} else {
				housePage = houseRepository.findAllByOrderByCreatedAtDesc(pageable);
			}
			
		}
		
		model.addAttribute("housePage", housePage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("area", area);
		model.addAttribute("price", price);
		model.addAttribute("order", order);
		
		return "houses/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model, 
			           @PageableDefault(page = 0, size = 6, sort = "updatedAt", direction = Direction.ASC) Pageable pageable, 
			           @RequestParam(name = "houseId", required = false) String houseId ) {
		House house =houseRepository.getReferenceById(id);
		//Review review = reviewRepository.getReferenceById();

		Page<Review> reviewPage;
		
		if (houseId != null) {
            reviewPage = reviewRepository.findByHouseId(houseId, pageable);  

        } else {
        	String errorMessage = "まだレビューがありません";
        	model.addAttribute("errorMessage", errorMessage); 
        	reviewPage = reviewRepository.findAll(pageable);
        }  
		
		model.addAttribute("house", house);
		model.addAttribute("reservationInputForm", new ReservationInputForm());
		model.addAttribute("houseId", houseId);
		model.addAttribute("reviewPage", reviewPage);
		
		System.out.println(houseId);
		
		return "houses/show";
	}

}
































