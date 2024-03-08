package com.tanthuan.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable{
	@Id
	String productID;
	String productName;
	String description;
	Boolean size;
	String categoryID;
	Float price;
}
