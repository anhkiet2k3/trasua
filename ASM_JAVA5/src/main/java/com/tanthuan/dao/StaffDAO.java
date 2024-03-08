package com.tanthuan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tanthuan.entity.Staff;



public interface StaffDAO extends JpaRepository<Staff, String>{
	@Transactional
	@Modifying
	@Query("UPDATE Staff o SET o.password = ?1 where o.staffID = ?2")
	public void updatePassword(String newPassword, String staffID);
	
	@Query("SELECT password from Staff where staffID = ?1")
	public String findPassword(String staffID);
	
	@Query("SELECT admin from Staff where staffID = ?1")
	public Boolean findAdmin(String staffID);
}