package com.example.springboot_tabelog_kadai.controller;

import java.util.List;

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

import com.example.springboot_tabelog_kadai.entity.Favorite;
import com.example.springboot_tabelog_kadai.entity.Review;
import com.example.springboot_tabelog_kadai.entity.Store;
import com.example.springboot_tabelog_kadai.form.ReservationInputForm;
import com.example.springboot_tabelog_kadai.repository.FavoriteRepository;
import com.example.springboot_tabelog_kadai.repository.ReviewRepository;
import com.example.springboot_tabelog_kadai.repository.StoreRepository;

@Controller
@RequestMapping("/stores")
public class StoreController {
	private final StoreRepository storeRepository;  
	private final ReviewRepository reviewRepository;
	private final FavoriteRepository favoriteRepository;
    
    public StoreController(StoreRepository storeRepository, ReviewRepository reviewrepository, FavoriteRepository favoriteRepository) {
        this.storeRepository = storeRepository;       
        this.reviewRepository = reviewrepository;
        this.favoriteRepository = favoriteRepository;
    }     
  
    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String keyword,
                        @RequestParam(name = "area", required = false) String area,
                        @RequestParam(name = "price", required = false) Integer price, 
                        @RequestParam(name = "genre", required = false) String genre,
                        @RequestParam(name = "order", required = false) String order,
                        @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
                        Model model) 
    {
        Page<Store> storePage;
                
        if (keyword != null && !keyword.isEmpty()) {
        	if (order != null && order.equals("priceAsc")) {
                storePage = storeRepository.findByNameLikeOrAddressLikeOrderByPriceAsc("%" + keyword + "%", "%" + keyword + "%", pageable);
            } else {
                storePage = storeRepository.findByNameLikeOrAddressLikeOrderByCreatedAtDesc("%" + keyword + "%", "%" + keyword + "%", pageable);
            }   
        } else if (area != null && !area.isEmpty()) {
        	 if (order != null && order.equals("priceAsc")) {
                 storePage = storeRepository.findByAddressLikeOrderByPriceAsc("%" + area + "%", pageable);
             } else {
                 storePage = storeRepository.findByAddressLikeOrderByCreatedAtDesc("%" + area + "%", pageable);
             }  
        } else if (price != null) {
        	if (order != null && order.equals("priceAsc")) {
                storePage = storeRepository.findByPriceLessThanEqualOrderByPriceAsc(price, pageable);
            } else {
                storePage = storeRepository.findByPriceLessThanEqualOrderByCreatedAtDesc(price, pageable);
            }   
        } else if (genre != null && !genre.isEmpty()) {
            if (order != null && order.equals("priceAsc")) {
                storePage = storeRepository.findByGenreLikeOrderByPriceAsc("%" + genre + "%", pageable);
            } else {
                storePage = storeRepository.findByGenreLikeOrderByCreatedAtDesc("%" + genre + "%", pageable);
            }  
        } else {
        	if (order != null && order.equals("priceAsc")) {
                storePage = storeRepository.findAllByOrderByPriceAsc(pageable);
            } else {
                storePage = storeRepository.findAllByOrderByCreatedAtDesc(pageable);   
            } 
        }                
        
        model.addAttribute("storePage", storePage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("area", area);
        model.addAttribute("price", price); 
        model.addAttribute("genre", genre);
        model.addAttribute("order", order);
        
        return "stores/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model) {
        Store store = storeRepository.getReferenceById(id);
        
        List<Review>reviewPage = reviewRepository.findTop6ByStoreIdOrderByCreatedAtDesc(id); 
        
        Favorite favorite = favoriteRepository.findByStoreId(id);
        
        model.addAttribute("store", store); 
        model.addAttribute("reservationInputForm", new ReservationInputForm());
        model.addAttribute("reviewId", id);
        model.addAttribute("reviewPage", reviewPage);
        model.addAttribute("favorite", favorite);
        
        return "stores/show";
    }    

}
