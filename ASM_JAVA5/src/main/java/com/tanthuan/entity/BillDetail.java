package com.tanthuan.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "billdetail")
public class BillDetail implements Serializable{
	@Id
	String billDetailID;
	String billID;
	String productID;
	String productName;
	String ghiChu;
	Integer soLuong;
	Float price;
	
}
