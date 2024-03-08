package com.tanthuan.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "bill")
public class Bill implements Serializable{
	
	@Id
	String billID;
	
//	@ManyToOne 
//	@JoinColumn(name = "customerID")
	String customerID;
	
	String customerName;
	float price;
	
//	@ManyToOne 
//	@JoinColumn(name = "staffID")
	String staffID;
	
//	@ManyToOne 
//	@JoinColumn(name = "statusID")
//	String statusID;
	
	String email;
	String phone;
	String diaChi;
	String ghiChu;
	LocalDate ngay;
	LocalTime gio;  
	String statusName;
}
