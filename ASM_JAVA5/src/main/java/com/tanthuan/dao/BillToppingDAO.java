package com.tanthuan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tanthuan.entity.Bill;
import com.tanthuan.entity.BillDetail;
import com.tanthuan.entity.BillTopping;

public interface BillToppingDAO extends JpaRepository<BillTopping, String>{
	@Query("SELECT o from BillTopping o where o.billDetailID = ?1")
	public List<BillTopping> findByBillDetailID(String billDetailID);
	
	@Query("SELECT o from BillTopping o where o.billID = ?1")
	public List<BillTopping> findByBillID(String billID);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM BillTopping o where o.billDetailID = ?1")
	public void deleteByBIllDT(String billID);

}
