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

import com.example.springboot_tabelog_kadai.entity.Store;
import com.example.springboot_tabelog_kadai.form.StoreEditForm;
import com.example.springboot_tabelog_kadai.form.StoreRegisterForm;
import com.example.springboot_tabelog_kadai.repository.StoreRepository;
import com.example.springboot_tabelog_kadai.service.StoreService;

@Controller
@RequestMapping("/admin/stores")
public class AdminStoreController {
	private final StoreRepository storeRepository;
	private final StoreService storeService;
	
	public AdminStoreController(StoreRepository storeRepository, StoreService storeService) {
		this.storeRepository = storeRepository;
		this.storeService = storeService;
	}

//	店舗一覧表示
	@GetMapping
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {
		Page<Store> storePage;
		
		if(keyword != null && !keyword.isEmpty()) {
			storePage = storeRepository.findByNameLike("%" + keyword + "%", pageable);
		} else {
			storePage = storeRepository.findAll(pageable);
		}
		
		model.addAttribute("storePage", storePage);
		model.addAttribute("keyword", keyword);
		
		return "admin/stores/index";
	}
	
//	店舗詳細
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		Store store = storeRepository.getReferenceById(id);
		
		model.addAttribute("store", store);
		
		return "admin/stores/show";
	}
	
//	店舗登録
	@GetMapping("/register")
	public String register(Model model) {
		
		model.addAttribute("storeRegisterForm", new StoreRegisterForm());
		
		return "admin/stores/register";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute @Validated StoreRegisterForm storeRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
            return "admin/stores/register";
        }
		
		storeService.create(storeRegisterForm);
        redirectAttributes.addFlashAttribute("successMessage", "店舗を登録しました。");    
		
		System.out.println("create");
		
		return "redirect:/admin/stores";
	}
	
//	店舗編集
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {
		Store store = storeRepository.getReferenceById(id);
		String imageName = store.getImageName();
		StoreEditForm storeEditForm = new StoreEditForm(store.getId(), store.getName(), store.getGenre(), null, store.getDescription(), store.getPrice(), store.getPostalCode(), store.getAddress(), store.getPhoneNumber(), store.getHoliday(), store.getOpenTime(), store.getCloseTime());
		
		model.addAttribute("imageName", imageName);
		model.addAttribute("storeEditForm", storeEditForm);
		
		return "admin/stores/edit";
	}
	
	@PostMapping("/{id}/update")
	public String update(@ModelAttribute @Validated StoreEditForm storeEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return "admin/stores/edit";
		}
		
		storeService.update(storeEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "店舗情報を編集しました。" );
		
		return "redirect:/admin/stores";
	}
	
//	店舗削除
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		storeRepository.deleteById(id);
		
		redirectAttributes.addFlashAttribute("successMessage", "店舗を削除しました。");
        
		return "redirect:/admin/stores";
	}
}
