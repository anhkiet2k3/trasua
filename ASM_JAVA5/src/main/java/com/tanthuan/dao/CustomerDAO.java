package com.tanthuan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tanthuan.entity.Customer;
import com.tanthuan.entity.Product;



public interface CustomerDAO extends JpaRepository<Customer, String>{
	@Transactional
	@Modifying
	@Query("UPDATE Customer o SET o.password = ?1 where o.customerID = ?2")
	public void updatePassword(String newPassword, String customerID);
	
	@Query("SELECT password from Customer where customerID = ?1")
	public String findPassword(String customerID);
}