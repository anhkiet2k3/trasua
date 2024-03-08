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
@Table(name = "category")
public class Category implements Serializable{
	@Id
	String categoryID;
	String categoryName;
	@OneToMany(mappedBy="categoryID")
	List<Product> product;
}
