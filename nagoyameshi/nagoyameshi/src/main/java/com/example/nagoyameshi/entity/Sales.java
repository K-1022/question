package com.example.nagoyameshi.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sales")
@Data
public class Sales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;
	
	@Column(name = "order_date")
	private LocalDate orderData;
	
	@Column(name = "paid")
	private Integer paid;
	
	

}
