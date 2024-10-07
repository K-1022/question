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

import com.example.springboot_tabelog_kadai.entity.Reservation;
import com.example.springboot_tabelog_kadai.entity.Store;
import com.example.springboot_tabelog_kadai.entity.User;
import com.example.springboot_tabelog_kadai.form.ReservationInputForm;
import com.example.springboot_tabelog_kadai.form.ReservationRegisterForm;
import com.example.springboot_tabelog_kadai.repository.ReservationRepository;
import com.example.springboot_tabelog_kadai.repository.StoreRepository;
import com.example.springboot_tabelog_kadai.security.UserDetailsImpl;
import com.example.springboot_tabelog_kadai.service.ReservationService;
@Controller
public class ReservationController {
 private final ReservationRepository reservationRepository;    
 private final StoreRepository storeRepository;
 private final ReservationService reservationService;
     
     public ReservationController(ReservationRepository reservationRepository, StoreRepository storeRepository, ReservationService reservationService) {        
         this.reservationRepository = reservationRepository;  
         this.storeRepository = storeRepository;
 		 this.reservationService = reservationService;
     }    
 
     @GetMapping("/reservations")
     public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
         User user = userDetailsImpl.getUser();
         Page<Reservation> reservationPage = reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);
         
         model.addAttribute("reservationPage", reservationPage);         
         
         return "reservations/index";
     }
     

 	@GetMapping("/stores/{id}/reservations/input")
 	public String input(@PathVariable(name = "id") Integer id,
 						 @ModelAttribute @Validated ReservationInputForm reservationInputForm,
 						 BindingResult bindingResult,
 						 @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
 						 RedirectAttributes redirectAttributes,
 						 Model model)
 	{
 		Store store = storeRepository.getReferenceById(id);
 		
 		if(bindingResult.hasErrors()) {
 			model.addAttribute("store", store);
 			model.addAttribute("errorMessage", "予約内容に不備があります。");
 			return "stores/show";
 		}
 		
 		redirectAttributes.addFlashAttribute("reservationInputForm", reservationInputForm);           
       	
 		return "redirect:/stores/{id}/reservations/confirm";
 	}
 	
 	@GetMapping("/stores/{id}/reservations/confirm")
 	public String confirm(@PathVariable(name = "id") Integer id,
 						   @ModelAttribute ReservationInputForm reservationInputForm,
 						   @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
 						   Model model) {
 		Store store = storeRepository.getReferenceById(id);
 		User user = userDetailsImpl.getUser();
 		
 		//予約日と予約時間を取得する
 		String fromDayDate = reservationInputForm.getFromDayDate();
 		String fromTimeDate = reservationInputForm.getFromTimeDate();
 		
 		ReservationRegisterForm  reservationRegisterForm = new ReservationRegisterForm(store.getId(),user.getId(),fromDayDate,fromTimeDate,reservationInputForm.getNumberOfPeople());
 		
 		model.addAttribute("store", store);
 		model.addAttribute("reservationRegisterForm", reservationRegisterForm);
 		
 		return "reservations/confirm";
 	}
 	
 	@PostMapping("/stores/{id}/reservations/create")
 	public String create(@ModelAttribute ReservationRegisterForm  reservationRegisterForm) {

 		reservationService.create(reservationRegisterForm);
 		return "redirect:/reservations?reserved";
 	}
 
 	@PostMapping("/reservations/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {        
        reservationRepository.deleteById(id);
                
        redirectAttributes.addFlashAttribute("successMessage", "予約を削除しました。");
        
        return "redirect:/reservations";
    }  

}
