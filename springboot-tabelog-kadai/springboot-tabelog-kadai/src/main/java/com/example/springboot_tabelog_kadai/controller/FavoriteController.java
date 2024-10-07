package com.example.springboot_tabelog_kadai.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springboot_tabelog_kadai.entity.Favorite;
import com.example.springboot_tabelog_kadai.entity.Store;
import com.example.springboot_tabelog_kadai.entity.User;
import com.example.springboot_tabelog_kadai.repository.FavoriteRepository;
import com.example.springboot_tabelog_kadai.repository.StoreRepository;
import com.example.springboot_tabelog_kadai.repository.UserRepository;
import com.example.springboot_tabelog_kadai.security.UserDetailsImpl;
import com.example.springboot_tabelog_kadai.service.FavoriteService;


@Controller
public class FavoriteController {
	private final FavoriteRepository favoriteRepository;
	private final StoreRepository storeRepository;
	private final UserRepository userRepository;
	private final FavoriteService favoriteService;
	
	public FavoriteController(FavoriteRepository favoriteRepository, StoreRepository storeRepository, UserRepository userRepository, FavoriteService favoriteService) {
		this.favoriteRepository = favoriteRepository;
		this.storeRepository = storeRepository;
		this.userRepository = userRepository;
		this.favoriteService = favoriteService;
	}
	
	@GetMapping("/favorite")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		Page<Favorite> favoritePage = favoriteRepository.findByUserOrderByCreatedAtDesc(user, pageable);
		model.addAttribute("favoritePage", favoritePage);
		
		return "favorite/index";
	}
	
	@PostMapping("/stores/{storeId}/favorite/create")
    public String create(@PathVariable(name = "storeId") Integer storeId,
    		             @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
    		             RedirectAttributes redirectAttributes,
    		             Model model) 
    {
    	
    	Store store = storeRepository.getReferenceById(storeId);
    	User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
    	
    	favoriteService.create(user, store);
    	redirectAttributes.addFlashAttribute("successMessage", "お気に入りに追加しました。");
        
    	return "redirect:/stores/{storeId}";
    }
	
	@PostMapping("/stores/{storeId}/favorite/{favoriteId}/delete")
    public String delete(@PathVariable(name = "storeId") Integer storeId,
    		             @PathVariable(name = "favoriteId") Integer favoriteId,
    		              RedirectAttributes redirectAttributes) {        
        favoriteRepository.deleteById(favoriteId);
       
        redirectAttributes.addFlashAttribute("successMessage", "お気に入りを解除しました。");
        
        return "redirect:/stores/{storeId}";
    } 
	
}

