package com.tanthuan.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tanthuan.dao.CustomerDAO;
import com.tanthuan.dao.StaffDAO;
import com.tanthuan.entity.Customer;
import com.tanthuan.entity.Staff;
import com.tanthuan.service.CookieService;
import com.tanthuan.service.ParamService;
import com.tanthuan.service.SessionService;


@Controller
public class LoginController {
	
	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	CustomerDAO cus_dao;
	@Autowired
	StaffDAO stf_dao;
	
	//Ham hien thi view login
	@GetMapping("/login")
	public String dangnhap(Model model) {
		System.out.println(cus_dao.findAll());
		//Set dữ liệu đăng nhập lên form nếu đã lưu từ trước
		if(getCookieValue("phone") == null || getCookieValue("pass") == null) {
			return "Account/dangNhap";
		}else {	
			model.addAttribute("phone",getCookieValue("phone"));
			model.addAttribute("pass",getCookieValue("pass"));
		}
		return "Account/dangNhap";
	}
	
	//Hàm xử lí nghiệp vụ đăng xuất account
	@RequestMapping("/dangXuat")
	public String dangXuat(RedirectAttributes param) {
		//Xóa dữ liệu tài khoản hiện hành trong session
		sessionService.set("thongTin","");
		sessionService.set("username",null);
		sessionService.set("id",null);
		sessionService.set("role",null);
		sessionService.set("menu",null);
		param.addAttribute("messageDH","Đăng xuất thành công!");
		return "redirect:/trangChu";
	}
	
	//Ham kiem tra vai tro khi dang nhap
	public String checkRole(boolean role) {
		String link = null;
		if(role == true) {
			link = "redirect:/bill_list";
		}else {
			if(sessionService.get("page") != null) {
				String page = sessionService.get("page").toString();
				if(page.equals("menu")) {
					link = "redirect:/menuThucUong";
				}else if(page.equals("buy")) {
					link = "redirect:/buy_view";
				}else {
					link = "redirect:/trangChu";
				}
			}else {
				link = "redirect:/trangChu";
			}
		}
		return link;
	}
	
	//Ham xu li chuc nang nghiep vu login
	@PostMapping("/login")
	public String login2(Model model) {
		
		//Lay cac value can thiet tu view login.jsp
		String phone = paramService.getString("phone", "");
		String pw = paramService.getString("password", "");
		boolean rm = paramService.getBoolean("remember", false);
		boolean role = paramService.getBoolean("role", false);

		//Khai bao bien can thiet
		boolean check = false;
		String un = null;
		String id = null;
		
		//Kiem tra vai tro cua account dang nhap
		if(role) {
			
			//So sánh dữ liệu của bảng Staff nếu đăng nhập với vai trò là Staff
			List<Staff> ds = stf_dao.findAll();
			for (int i = 0; i < ds.size(); i++) {
				if(
					//Nếu khớp số điện thoại và mật khẩu
					(phone.equals(ds.get(i).getPhone()) && pw.equals(ds.get(i).getPassword())) ||
					//Nếu khớp tên đăng nhập và mật khẩu
					(phone.equals(ds.get(i).getUsername()) && pw.equals(ds.get(i).getPassword())) ){
						check = true;
						un = ds.get(i).getFullname();
						id = ds.get(i).getStaffID();
						sessionService.set("menu","true");
						break;
				}
				
			}
		}else {
			
			//So sánh dữ liệu của bảng Customer nếu đăng nhập với vai trò là Customer
			List<Customer> ds = cus_dao.findAll();
			for (int i = 0; i < ds.size(); i++) {
				if(
					//Nếu khớp số điện thoại và mật khẩu
					(phone.equals(ds.get(i).getPhone()) && pw.equals(ds.get(i).getPassword())) ||
					//Nếu khớp tên đăng nhập và mật khẩu
					(phone.equals(ds.get(i).getUsername()) && pw.equals(ds.get(i).getPassword())) ){
						check = true;
						un = ds.get(i).getCustomerName();
						id = ds.get(i).getCustomerID();
						sessionService.set("menu","false");
						break;
				}
				
			}
		}
				
		//Kiểm tra đăng nhập
		if(check) {
			
			//Lưu name, role và id vào session nếu thành công
			sessionService.set("username",un);
			sessionService.set("id",id);
			sessionService.set("role",role);
			
			//Kiểm tra checkbox Remember Me
			if(rm==true) {
				
				//Nếu remember me được check thì lưu dữ liệu phone và pass vào cookie
				cookieService.add("phone", phone, 10);
				cookieService.add("pass", pw, 10);
				cookieService.add("role", role+"", 10);
				return checkRole(role);	
				
			}else if(rm==false) {
				
				//Nếu remember me không được check thì xóa dữ liệu phone và pass trong cookie
				cookieService.remove("phone");
				cookieService.remove("pass");
				return checkRole(role);	
				
			}
			
		}else {
			   //Thông báo lỗi nếu thất bại
			   model.addAttribute("mess", "Sai mật khẩu hoặc tên đăng nhập!");
			   return "Account/dangNhap";
		}

		return "Account/dangNhap";
	}
	
	
	//Ham lay gia tri cookie theo ten
	public String getCookieValue(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					   return cookie.getValue();
				}
			}
		}
		return null;
	}
}
