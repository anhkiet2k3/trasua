package com.tanthuan.controller.manage;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tanthuan.controller.StaffController;
import com.tanthuan.dao.BillDAO;
import com.tanthuan.dao.CategoryDAO;
import com.tanthuan.dao.ProductDAO;
import com.tanthuan.entity.Category;
import com.tanthuan.entity.Product;
import com.tanthuan.service.SessionService;

@Controller
public class ProductManageController {
	@Autowired
	ProductDAO daoPr;
	
	@Autowired
	CategoryDAO daoC;
	
	@Autowired
	BillDAO daoB;
	
	@Autowired
	SessionService sessionService;
	
	//Hàm tìm kiếm sản phẩm
	@RequestMapping("/product_find")
	public String find_product(Model model, @RequestParam("search") String searchS, @RequestParam("p") Optional<Integer> p) {
		getTenUser(model);

		if(getIdUser(model)) {
			Pageable pageable;
			
			Page<Product> list;
			
			//Xu li khi prev qua gioi han
			if(p.orElse(0).intValue() >= 0) {
				pageable = PageRequest.of(p.orElse(0), 9);
			}else {
				pageable = PageRequest.of(0, 9);
			}
			
			//Xu li khi next qua gioi han
			if(daoPr.findAll(pageable).isEmpty()) {
				pageable = PageRequest.of(0, 9);
			}
			
			//Kiểm tra kiểu dữ liệu nội dung nhập vào
			try {
				Float search = Float.parseFloat(searchS);
				list = daoPr.findProductByPrice(search, pageable);
			}catch(Exception e) {
				
					String kq = "Sai gòi!";
					if(searchS.equalsIgnoreCase("960CC")) {
						kq = "true";
					}else if(searchS.equalsIgnoreCase("720CC")) {
						kq = "false";
					}else if(searchS.equalsIgnoreCase("lớn")) {
						kq = "true";
					}else if(searchS.equalsIgnoreCase("nhỏ")) {
						kq = "false";
					}else if(searchS.equalsIgnoreCase("true")) {
						kq = "true";
					}else if(searchS.equalsIgnoreCase("false")) {
						kq = "false";
					}
					
					if(kq == "true" || kq == "false") {
						Boolean search = Boolean.parseBoolean(kq);
						list = daoPr.findProductBySize(search, pageable);
					}else {
						list = daoPr.findProductByString(searchS, pageable);
					}
				}
			
			if(editPr != null) {
				model.addAttribute("product",editPr);
			}else {
				Product pr = new Product();
				model.addAttribute("product",pr);
			}
			model.addAttribute("list", list);
			List<Category> theLoai = daoC.findAll();
			model.addAttribute("theLoai",theLoai);
			model.addAttribute("auth","true");
			model.addAttribute("page","Staff/sanPham.jsp");
		}else {
			model.addAttribute("auth","false");
		}
		
		demCXN();
		return "index";
	}
		
	//Hàm thêm 1 sản phẩm mới
	@RequestMapping("/product/add")
	public String add_Product(Product pr, RedirectAttributes param) {
		daoPr.save(pr);
		param.addAttribute("mess","Tạo Mới Thành Công!");
		return "redirect:/product/reset";
	}
	
	//Hàm cập nhật 1 sản phẩm
	@RequestMapping("/product/update")
	public String update_Product(Product pr, RedirectAttributes param) {
		daoPr.save(pr);
		param.addAttribute("mess","Cập Nhật Thành Công!");
		return "redirect:/product/reset";
	}
	
	//Hàm xóa 1 sản phẩm
	@RequestMapping("/product/delete/{id}")
	public String delete_Product(RedirectAttributes param, @PathVariable("id") String id) {
		daoPr.deleteById(id);
		param.addAttribute("mess","Xóa Thành Công!");
		return "redirect:/product/reset";
	}
	
	//Hàm reset trang sanPham
	@RequestMapping("/product/reset")
	public String reset_Product() {
		editPr = null;
		return "redirect:/sanPham";
	}
	
	//Hàm load dữ liệu 1 sản phẩm đã chọn từ table lên form
	@RequestMapping("/product/edit/{id}")
	public String edit_Product(RedirectAttributes param, @PathVariable ("id") String id) {
		editPr = daoPr.findById(id).get();
		return "redirect:/sanPham";
	}
	
	Product editPr = null; 
	
	//Hàm view trang sản phẩm
	@RequestMapping("/sanPham")
	public String detail(Model model, @RequestParam("p") Optional<Integer> p) {
		getTenUser(model);

		if(getIdUser(model)) {
			Pageable pageable;
			
			//Xu li khi prev qua gioi han
			if(p.orElse(0).intValue() >= 0) {
				pageable = PageRequest.of(p.orElse(0), 9);
			}else {
				pageable = PageRequest.of(0, 9);
			}
			
			//Xu li khi next qua gioi han
			if(!daoPr.findAll(pageable).isEmpty()) {
				model.addAttribute("list",daoPr.findAllAsc(pageable));
			}else {
				pageable = PageRequest.of(0, 9);
				model.addAttribute("list",daoPr.findAllAsc(pageable));
			}
			
			if(editPr != null) {
				model.addAttribute("product",editPr);
			}else {
				Product pr = new Product();
				model.addAttribute("product",pr);
			}
			List<Category> theLoai = daoC.findAll();
			model.addAttribute("theLoai",theLoai);
			model.addAttribute("auth",true);
			model.addAttribute("page","Staff/sanPham.jsp");
		}else {
			model.addAttribute("auth",false);
		}
		
		demCXN();
		return "index";
	}
	
	//Hàm  đếm số lượng đơn chưa xác nhận
	public Integer demCXN() {
		Integer kq = 0;
		kq = daoB.demCXN("Chưa Xác Nhận");
		sessionService.set("CXN", kq);
		return kq;
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
