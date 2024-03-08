package com.tanthuan.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "billtopping")
public class BillTopping implements Serializable{
	@Id
	String billToppingID;
	String billDetailID;
	String billID;
	String toppingID;
	String toppingName;
	Integer soLuong;
	Float price;
}
