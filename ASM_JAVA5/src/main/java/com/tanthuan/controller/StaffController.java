package com.tanthuan.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.Parameter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tanthuan.dao.BillDAO;
import com.tanthuan.dao.BillDetailDAO;
import com.tanthuan.dao.BillToppingDAO;
import com.tanthuan.dao.CategoryDAO;
import com.tanthuan.dao.CustomerDAO;
import com.tanthuan.dao.ProductDAO;
import com.tanthuan.dao.ShoppingCartService;
import com.tanthuan.dao.ShoppingCartServiceImpl;
import com.tanthuan.entity.Bill;
import com.tanthuan.entity.BillDetail;
import com.tanthuan.entity.BillTopping;
import com.tanthuan.entity.Category;
import com.tanthuan.entity.Customer;
import com.tanthuan.entity.Item;
import com.tanthuan.entity.Product;
import com.tanthuan.service.ParamService;
import com.tanthuan.service.SessionService;

@Controller
public class StaffController {
	
	@Autowired
	ProductDAO dao;
	
	@Autowired
	CategoryDAO daoC;
	
	@Autowired
	BillDAO daoB;
	
	@Autowired
	BillDetailDAO daoBD;
	
	@Autowired
	BillToppingDAO daoBT;

	@Autowired
	CustomerDAO daoCus;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ParamService paramService;
	
	//Hàm  đếm số lượng đơn chưa xác nhận
	public Integer demCXN() {
		Integer kq = 0;
		kq = daoB.demCXN("Chưa Xác Nhận");
		sessionService.set("CXN", kq);
		return kq;
	}
	
	//Hàm view trang đơn hàng
	@RequestMapping("/bill_list")
	public String buyHistory(Model model, @RequestParam("p") Optional<Integer> p) {

		getTenUser(model);

		if(getIdUser(model)) {
			
			Pageable pageable;
			
			//Xu li khi prev qua gioi han
			if(p.orElse(0).intValue() >= 0) {
				pageable = PageRequest.of(p.orElse(0), 5);
			}else {
				pageable = PageRequest.of(0, 5);
			}
			
			//Xu li khi next qua gioi han
			if(!daoB.findAll(pageable).isEmpty()) {
				model.addAttribute("bill",daoB.findAllDesc(pageable));
			}else {
				pageable = PageRequest.of(0, 5);
				Page<Bill> list = daoB.findAllDesc(pageable);
				model.addAttribute("bill",list);
			}
			
			//Set va show du lieu billDetail neu co ma id
			if(idDT != null) {
				Bill bill = daoB.findById(idDT).get();
				model.addAttribute("thongTin",bill);
				
				List<BillDetail> listDT = daoBD.findByBillID(idDT);
				
				List<BillTopping> listTP = daoBT.findByBillID(idDT);
				model.addAttribute("listTP",listTP);
				
				model.addAttribute ("check",true);
				model.addAttribute ("listDT",listDT);
				idDT = null;
			}else {
				Bill bill = new Bill();
				model.addAttribute ("thongTin",bill);
				model.addAttribute ("check",false);
			}
			model.addAttribute("auth",true);	
		}else {
			model.addAttribute("auth",false);
		}
		
		
		demCXN();
		model.addAttribute("page","Staff/bill.jsp");
		return"index";
	}
	
	
	String idDT = null;
	
	//Chi tiết đơn hàng
	@RequestMapping("/staff_bill_detail/{id}")
	public String detail(Model model, @PathVariable("id") String id) {
		idDT = id;
		model.addAttribute("page","Staff/bill.jsp");
		return "redirect:/bill_list";

	}
	
	//Hàm hủy đơn hàng
	@RequestMapping("/staff_cancel/{id}")
	public String cancel(Model model, RedirectAttributes param, @PathVariable("id") String id) {
		daoB.updateStatus("Đã Hủy", id);
		String liDo = paramService.getString("liDo","");
		daoB.updateNote(liDo, id);
		daoB.updateStaff(sessionService.get("id").toString(), id);
		param.addAttribute("mess","Hủy Đơn Hàng Thành Công!");
		return "redirect:/bill_list";

	}
	
	//Hàm xác nhận đơn hàng
	@RequestMapping("/staff_confirm/{id}")
	public String confirm(Model model, RedirectAttributes param, @PathVariable("id") String id) {
		daoB.updateStatus("Đang Thực Hiện", id);
		daoB.updateStaff(sessionService.get("id").toString(), id);
		param.addAttribute("mess","Xác Nhận Đơn Hàng Thành Công!");
		return "redirect:/bill_list";

	}
	
	//Hàm hoàn tất đơn hàng
	@RequestMapping("/staff_success/{id}")
	public String success(Model model, RedirectAttributes param, @PathVariable("id") String id) {
		daoB.updateStatus("Hoàn Tất", id);
		param.addAttribute("mess","Đã Hoàn Tất Đơn Hàng!");
		return "redirect:/bill_list";

	}

	//Hàm lấy dữ liệu id tài khoản hiện hành từ session
	public boolean getIdUser(Model model) {
		boolean check = false;
		if(sessionService.get("id") != null) {
			if(sessionService.get("role").equals(true)) {
				check = true;
			}
		}else {
			check = false;
		}
		return check;
	}
	
	//Hàm lấy dữ tên người dùng từ session
	public void getTenUser(Model model) {
		if(sessionService.get("username") != null) {
			model.addAttribute("nameCus",sessionService.get("username").toString());
		}else {
			model.addAttribute("nameCus","Đăng nhập");
		}
	}
		
}
