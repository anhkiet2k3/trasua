package com.tanthuan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "staff")
public class Staff implements Serializable{
	@Id
	String staffID;
	String fullname;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date birthday;
	boolean gender;
	String address;
	String email;
	String phone;
	String username;
	String password;
	boolean admin;
}
