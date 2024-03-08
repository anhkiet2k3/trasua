package com.tanthuan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tanthuan.entity.Bill;
import com.tanthuan.entity.BillDetail;

public interface BillDetailDAO extends JpaRepository<BillDetail, String>{
	@Query("SELECT o from BillDetail o where o.billID = ?1")
	public List<BillDetail> findByBillID(String billID);

}
