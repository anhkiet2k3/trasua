package com.tanthuan.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tanthuan.entity.Bill;

public interface BillDAO extends JpaRepository<Bill, String>{

	@Transactional
	@Modifying
	@Query("UPDATE Bill o SET o.statusName = ?1 where o.billID = ?2")
	public void updateStatus(String newStatus, String billID);
	
	@Transactional
	@Modifying
	@Query("UPDATE Bill o SET o.ghiChu = ?1 where o.billID = ?2")
	public void updateNote(String newNote, String billID);
	
	@Transactional
	@Modifying
	@Query("UPDATE Bill o SET o.staffID = ?1 where o.billID = ?2")
	public void updateStaff(String newStaff, String billID);
	
	@Query("SELECT o from Bill o where o.customerID = ?1 ORDER BY ngay DESC , gio DESC")
	public Page<Bill> findByCus(String customerID, Pageable pageable);
	
	@Query("SELECT o from Bill o ORDER BY o.ngay DESC , o.gio DESC")
	public Page<Bill> findAllDesc(Pageable pageable);
	
	@Query("SELECT COUNT(o.billID) FROM Bill o WHERE o.statusName = ?1")
	public Integer demCXN(String stt);
	
	@Query("SELECT o FROM Bill o WHERE o.statusName = ?1")
	public List<Bill> listCXN(String stt);
}
