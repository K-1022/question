package com.example.springboot_tabelog_kadai.service;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.example.springboot_tabelog_kadai.entity.Reservation;
import com.example.springboot_tabelog_kadai.entity.Store;
import com.example.springboot_tabelog_kadai.entity.User;
import com.example.springboot_tabelog_kadai.form.ReservationRegisterForm;
import com.example.springboot_tabelog_kadai.repository.ReservationRepository;
import com.example.springboot_tabelog_kadai.repository.StoreRepository;
import com.example.springboot_tabelog_kadai.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final StoreRepository storeRepository;
	private final UserRepository userRepository;
	
	public ReservationService(ReservationRepository reservationRepository, StoreRepository storeRepository, UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.storeRepository = storeRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void create(ReservationRegisterForm reservationRegisterForm) {
		Reservation reservation = new Reservation();
		Store store = storeRepository.getReferenceById(reservationRegisterForm.getStoreId());
		User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
		LocalDate DayDate = LocalDate.parse(reservationRegisterForm.getDayDate());
		LocalTime TimeDate  = LocalTime.parse(reservationRegisterForm.getTimeDate());
		
		if(isHoliday(DayDate, store.getHoliday())) {
			throw new IllegalArgumentException("予約日は店舗の定休日です");
		 }
		 if(!isWithinOpenTime(TimeDate, store.getOpenTime())) {
			 throw new IllegalArgumentException("予約時間は店舗の営業開始時間より前です。");
		 }
		 
		 if(!isWithinCloseTime(TimeDate, store.getCloseTime())) {
			 throw new IllegalArgumentException("予約時間は店舗の営業終了時間の後です。");
		 }
		 
		 reservation.setStore(store);
		 reservation.setUser(user);
		 reservation.setDayDate(DayDate);
		 reservation.setTimeDate(TimeDate);
		 reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());
		
		 reservationRepository.save(reservation);
	}
	
	public boolean isWithinOpenTime(LocalTime TimeDate, Time openTime) {
		return !TimeDate.isBefore(openTime.toLocalTime());
	}
	public boolean isWithinCloseTime(LocalTime TimeDate, Time closeTime) {
	    return !TimeDate.isAfter(closeTime.toLocalTime());
	}
	
	public boolean isHoliday(LocalDate DayDate, String holidays) {
        List<String> holidayList = Arrays.asList(holidays.split(","));
        DayOfWeek dayOfWeek = DayDate.getDayOfWeek();
        String dayOfWeekJapanese = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.JAPANESE);
        String dayOfWeekStr = dayOfWeek.toString().substring(0, 1).toUpperCase() + dayOfWeek.toString().substring(1).toLowerCase(); // "MONDAY" -> "Monday"
        return holidayList.contains(dayOfWeekJapanese) || holidayList.contains(dayOfWeekStr);
       
    }
	
	public void validateReservation(ReservationRegisterForm reservationRegisterForm) {
        Store store = storeRepository.getReferenceById(reservationRegisterForm.getStoreId());
        LocalDate DayDate = LocalDate.parse(reservationRegisterForm.getDayDate());
        LocalTime TimeDate = LocalTime.parse(reservationRegisterForm.getTimeDate(), DateTimeFormatter.ofPattern("HH:mm"));

        if (isHoliday(DayDate, store.getHoliday())) {
            throw new IllegalArgumentException("予約日は店舗の定休日です");
        }
        if (!isWithinOpenTime(TimeDate, store.getOpenTime())) {
            throw new IllegalArgumentException("予約時間は店舗の営業開始時間より前です。");
        }
        if (!isWithinCloseTime(TimeDate, store.getCloseTime())) {
            throw new IllegalArgumentException("予約時間は店舗の営業終了時間の後です。");
        }
    }


}
