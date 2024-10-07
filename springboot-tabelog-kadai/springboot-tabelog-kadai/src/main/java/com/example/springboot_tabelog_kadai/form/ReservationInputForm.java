package com.example.springboot_tabelog_kadai.form;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
    @NotBlank(message = "来店日を選択してください。")
    private String fromDayDate;
    
    @NotBlank(message = "来店時間を選択してください。")
    private String fromTimeDate;
    
    @NotNull(message = "来店人数を入力してください。")
    @Min(value = 1, message = "来店人数は1人以上に設定してください。")
    private Integer numberOfPeople;

    public LocalDate getReservationDate() {
		String DayDate = getFromDayDate();
		return LocalDate.parse(DayDate);
	}
    public LocalTime getTimeDate() {
        String TimeDate = getFromTimeDate();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(TimeDate, timeFormatter);
    }
}
