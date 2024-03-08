package com.tanthuan.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "topping")
public class Topping implements Serializable{
	@Id
	String toppingID;
	String toppingName;
	Float price;
	
}
