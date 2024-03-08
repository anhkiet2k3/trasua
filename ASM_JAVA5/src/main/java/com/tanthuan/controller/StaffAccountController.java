package com.tanthuan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tanthuan.dao.StaffDAO;
import com.tanthuan.entity.Customer;
import com.tanthuan.entity.Staff;
import com.tanthuan.service.SessionService;

@Controller
public class StaffAccountController {

	@Autowired
	StaffDAO dao;
	
	@Autowired
	SessionService sessionService;
	
	// XỬ LÝ ĐĂNG KÝ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	
		//Hàm hiển thị view đăng ký
		@GetMapping("/dangKy_Staff")
		public String dangky(Model model, RedirectAttributes param) {
			
			String staffID = sessionService.get("id").toString();
			String link;
			
			if(dao.findAdmin(staffID)) {
				Staff cs = new Staff();
				model.addAttribute("cs",cs);
				model.addAttribute("id",createID());
				link = "Account/dangKy_Staff";
			}else {
				param.addAttribute("messageUP", "Yêu Cầu Quyền Tài Khoản ADMIN!");
				link = "redirect:/login";
			}
			
			return link;
			
		}
		
		//Hàm xử lí chức năng đăng kí
		@PostMapping("/dangKy_Staff")
		public String regrist(Staff cus, Model model, RedirectAttributes param, @RequestParam("confirm") String confirm) {
			if(confirm.equals(cus.getPassword())) {
				cus.setStaffID(createID());
				cus.setAdmin(false);
				dao.save(cus);
				param.addAttribute("messageUP", "Đăng ký thành công! Mời bạn đăng nhập tài khoản mới!");
			}else {
				param.addAttribute("messageUP", "Mật khẩu và mật khẩu xác nhận không khớp!!");
			}
			return "redirect:/login";
		}
		
		//Hàm tạo mới set staffID
		public String createID() {
			String id = null;
			
			List<Staff> cus = dao.findAll();
			
			String first = cus.get(cus.size()-1).getStaffID();
			
			if(first.length() == 4) {
				String a = first.substring(2, 3);
				String b = first.substring(3);
				if(a.equals("0") && !b.equals("9")) {
					int num = Integer.parseInt(first.substring(2)) + 1;
					id = "ST0"+ num;
				}else {
					int num = Integer.parseInt(first.substring(2)) + 1;
					id = "ST"+ num;
				}
				
			}else {
				int num = Integer.parseInt(first.substring(2)) + 1;
				id = "ST"+ num;
			}
			
			return id;
		}
		
		
		// XỬ LÝ CẬP NHẬT THÔNG TIN- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
		
		//Hiển thị view cập nhật thông tin
		@GetMapping("/capNhat_Staff")
		public String update(Model model, RedirectAttributes param) {
			String link = null;
			if(getIdUser(model)) {
				link = "Account/capNhat_Staff";
				setStaffData(model);
			}else {
				param.addAttribute("messageUP", "Vui lòng đăng nhập trước khi thực hiện!");
				link = "redirect:/login";
			}
			return link;
		}
		
		//Hàm xử lí nghiệp vụ cập nhật thông tin
		@PostMapping("/capNhat_Staff")
		public String capNhat(Model model, RedirectAttributes param , Staff cs, @RequestParam("confirm") String conf) {
			
			String cusID = sessionService.get("id").toString();
			Staff cus = dao.findById(cusID).get();
			String pass = cus.getPassword();
			
			if(conf.equals(pass)) {
				
				cs.setStaffID(cusID);
				cs.setPassword(pass);
				dao.save(cs);
				
				sessionService.set("username",cs.getFullname());
				param.addAttribute("mess", "Cập nhật thông tin thành công!");
				
			}else {
				param.addAttribute("mess", "Mật khẩu xác nhận không chính xác!");
			}
			return "redirect:/capNhat_Staff";
		}
		
		//Hàm set dữ liệu staff từ tài khoản hiện hành lên view cập nhật
		public void setStaffData(Model model) {
			String cusID = sessionService.get("id").toString();
			Staff cs = dao.findById(cusID).get();
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
			@GetMapping("/doiMatKhau_Staff")
			public String changPass(Model model, RedirectAttributes param) {
				
				String link = null;
				
				if(getIdUser(model)) {
					String cusName = sessionService.get("username").toString();
					link = "Account/doiMatKhau_Staff";
					model.addAttribute("name",cusName);
					
				}else {
					param.addAttribute("messageUP", "Vui lòng đăng nhập trước khi thực hiện!");
					link = "redirect:/login";
				}
				return link;
			}
			
			//Hàm xử lí nghiệp vụ chức năng đổi mật khẩu
			@PostMapping("/doiMatKhau_Staff")
			public String changePass(Staff cus, Model model, RedirectAttributes param, 
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
						mess = "Mật khẩu mới và mật khẩu xác nhận không khớp!";
					}
					
				}else {
					mess = "Mật khẩu hiện tại không đúng!";
				}
				
				param.addAttribute("mess", mess);
				
				return "redirectL:/doiMatKhau_Staff";
			}
}
