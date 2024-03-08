package com.tanthuan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tanthuan.dao.CustomerDAO;
import com.tanthuan.entity.Customer;
import com.tanthuan.service.SessionService;

@Controller
public class AccountController {
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	SessionService sessionService;
	
	// XỬ LÝ ĐĂNG KÝ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	
	//Hàm hiển thị view đăng ký
	@GetMapping("/dangKy")
	public String dangky(Model model) {
		Customer cs = new Customer();
		model.addAttribute("cs",cs);
		model.addAttribute("id",createID());
		return "Account/dangKy";
	}
	
	//Hàm xử lí chức năng đăng kí
	@PostMapping("/dangKy")
	public String regrist(Customer cus, Model model, RedirectAttributes param) {
		
		cus.setCustomerID(createID());
		dao.save(cus);
		
		model.addAttribute("cs",cus);
		model.addAttribute("id",createID());
		param.addAttribute("messageUP", "Đăng ký thành công! Mời bạn đăng nhập tài khoản mới!");
		
		return "redirect:/login";
	}
	
	//Hàm tạo mới set customerID
	public String createID() {
		String id = null;
		
		List<Customer> cus = dao.findAll();
		
		String first = cus.get(cus.size()-1).getCustomerID();
		
		if(first.length() == 4) {
			String a = first.substring(2, 3);
			String b = first.substring(3);
			if(a.equals("0") && !b.equals("9")) {
				int num = Integer.parseInt(first.substring(2)) + 1;
				id = "CS0"+ num;
			}else {
				int num = Integer.parseInt(first.substring(2)) + 1;
				id = "CS"+ num;
			}
			
		}
		
		return id;
	}
	
	
	// XỬ LÝ CẬP NHẬT THÔNG TIN- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	
	//Hiển thị view cập nhật thông tin
	@GetMapping("/capNhat")
	public String update(Model model, RedirectAttributes param) {
		String link = null;
		if(getIdUser(model)) {
			link = "Account/capNhat";
			setCustomerData(model);
		}else {
			param.addAttribute("messageUP", "Vui lòng đăng nhập trước khi thực hiện!");
			link = "Account/dangNhap";
		}
		return link;
	}
	
	//Hàm xử lí nghiệp vụ cập nhật thông tin
	@PostMapping("/capNhat")
	public String capNhat(Model model, Customer cs, @RequestParam("confirm") String conf) {
		
		String cusID = sessionService.get("id").toString();
		Customer cus = dao.findById(cusID).get();
		String pass = cus.getPassword();
		
		if(conf.equals(pass)) {
			
			cs.setCustomerID(cusID);
			cs.setPassword(pass);
			dao.save(cs);
			
			sessionService.set("username",cs.getCustomerName());
			model.addAttribute("cs",cus);
			model.addAttribute("mess", "Cập nhật thông tin thành công!");
			
		}
		return "Account/capNhat";
	}
	
	//Hàm set dữ liệu customer từ tài khoản hiện hành lên view cập nhật
	public void setCustomerData(Model model) {
		String cusID = sessionService.get("id").toString();
		Customer cs = dao.findById(cusID).get();
		model.addAttribute("cs",cs);				
	}
	
	//Hàm lấy dữ liệu id tài khoản hiện hành từ session
	public boolean getIdUser(Model model) {
		boolean check = false;
		if(sessionService.get("id") != null) {
			check = true;
		}else {
			check = false;
		}
		return check;
	}
	
	
	// XỬ LÝ ĐỔI MẬT KHẨU- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	
		//Hiển thị view đổi mật khẩu
		@GetMapping("/doiMatKhau")
		public String changPass(Model model, RedirectAttributes param) {
			
			String link = null;
			
			if(getIdUser(model)) {
				String cusName = sessionService.get("username").toString();
				link = "Account/doiMatKhau";
				model.addAttribute("name",cusName);
				
			}else {
				param.addAttribute("messageUP", "Vui lòng đăng nhập trước khi thực hiện!");
				link = "Account/dangNhap";
			}
			return link;
		}
		
		//Hàm xử lí nghiệp vụ chức năng đổi mật khẩu
		@PostMapping("/doiMatKhau")
		public String changePass(Customer cus, Model model, RedirectAttributes param,
				@RequestParam("current") String current,
				@RequestParam("new") String newP,
				@RequestParam("confirm") String confirm
				) {
			
			String cusID = sessionService.get("id").toString();
			String pass = dao.findPassword(cusID);
			String mess = null;
			
			if(current.equals(pass)) {
				
				if(newP.equals(confirm)) {
					dao.updatePassword(newP, cusID);
					mess = "Thay đổi thành công!";
				}else {
					mess = "Mật khẩu mới và mật khẩu xác nhận không khớp";
				}
				
			}else {
				mess = "Mật khẩu hiện tại không đúng!";
			}
			param.addAttribute("mess",mess);
			
			return "redirectL:/doiMatKhau";
		}
	
}
