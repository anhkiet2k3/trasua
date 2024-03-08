package com.tanthuan.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "status")
public class Status implements Serializable{
	@Id
	String statusID;
	String statusName;
	String description;
}
