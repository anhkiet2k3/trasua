package com.tanthuan.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer implements Serializable{
	@Id
	String customerID;
	String customerName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date birthday;
	String phone;
	String address;
	String email;
	String username;
	String password;
}
