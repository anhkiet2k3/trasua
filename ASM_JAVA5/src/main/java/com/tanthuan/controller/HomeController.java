
package com.tanthuan.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tanthuan.dao.BillDAO;
import com.tanthuan.dao.BillDetailDAO;
import com.tanthuan.dao.CategoryDAO;
import com.tanthuan.dao.ProductDAO;
import com.tanthuan.dao.ShoppingCartService;
import com.tanthuan.entity.BillDetail;
import com.tanthuan.entity.Category;
import com.tanthuan.entity.Product;
import com.tanthuan.service.SessionService;

@Controller
public class HomeController {
	
	@Autowired
	ProductDAO daoPr;
	
	@Autowired
	BillDAO daoB;
	
	@Autowired
	CategoryDAO daoCa;
	
	@Autowired
	ShoppingCartService cart;
	
	@Autowired
	SessionService sessionService;
	
	//Khởi chạy view trang chủ
	@RequestMapping("/trangChu")
	public String trangChu(Model model) {
	
		getTenUser(model);
		getTheLoai(model);
		demCXN();
		//Load dữ liệu sản phẩm
		getSanPham(model);
		
		sessionService.set("page","home");
		
		model.addAttribute("page","page/home.jsp");
		return "index";
	}
	
	
	//Hàm  đếm số lượng đơn chưa xác nhận
	public Integer demCXN() {
		Integer kq = 0;
		kq = daoB.demCXN("Chưa Xác Nhận");
		sessionService.set("CXN", kq);
		return kq;
	}
	
	//Khởi chạy view giới thiệu
	@RequestMapping("/gioiThieu")
	public String gioiThieu(Model model) {
		getTenUser(model);
		getTheLoai(model);
		model.addAttribute("page","page/about.jsp");
		return "index";
	}
	
	//Khởi chạy view liên hệ
		@RequestMapping("/lienHe")
		public String lienHe(Model model) {
			getTenUser(model);
			getTheLoai(model);
			model.addAttribute("page","page/contact.jsp");
			return "index";
		}
		
	//Tạo biến chứa category id dùng để lọc menu
	String idCate = "all";
	
	//Khởi chạy view menu thức uống
	@RequestMapping("/menuThucUong")
	public String menuThucUong(Model model) {
		
		getTenUser(model);
		model.addAttribute("page","page/menu.jsp");
		
		
		//Load dữ liệu thể loại
		getTheLoai(model);
		
		//lọc dữ liệu hiển thị lên menu
		if(idCate.equals("all")) {	
			
			//Load dữ liệu sản phẩm
			getSanPham(model);
			
		}else {
			
			//Load dữ liệu sản phẩm
			Product item = new Product();
			model.addAttribute("item", item);
			List<Product> items = daoPr.findByCategory(idCate);
			model.addAttribute("items", items);
			
		}
		sessionService.set("page","menu");	
		return "index";
	}
	
	//Hàm nhận giá trị category id sẽ lọc từ view
	@RequestMapping("/category/{id}")
	public String theLoai(@PathVariable("id") String id, Model model) {
		idCate = id;
		return "redirect:/menuThucUong";
	}
	
	//Hàm lấy dữ tên người dùng từ session
	public void getTenUser(Model model) {
		if(sessionService.get("username") != null) {
			model.addAttribute("nameCus",sessionService.get("username").toString());
			model.addAttribute("mess","Đang chuyển đến trang làm việc!");
		}else {
			model.addAttribute("mess","Vui lòng đăng nhập trước!");
			model.addAttribute("nameCus","Đăng nhập");
		}
	}
	
	//Hàm load dữ liệu thể loại lên view
	public void getTheLoai(Model model) {
		//Load dữ liệu thể loại
		Category cate = new Category();
		model.addAttribute("cate", cate);
		List<Category> cates = daoCa.findAll();
		model.addAttribute("cates", cates);
	}
	
	//Hàm load dữ liệu sản phẩm lên view
	public void getSanPham(Model model) {
		//Load dữ liệu sản phẩm
		Product item = new Product();
		model.addAttribute("item", item);
		List<Product> items = daoPr.findAll();
		model.addAttribute("items", items);
	}
	
	

}
