
package com.tanthuan.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.Parameter;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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
import com.tanthuan.dao.ToppingCartServiceImpl;
import com.tanthuan.dao.ToppingDAO;
import com.tanthuan.entity.Bill;
import com.tanthuan.entity.BillDetail;
import com.tanthuan.entity.BillTopping;
import com.tanthuan.entity.Category;
import com.tanthuan.entity.Customer;
import com.tanthuan.entity.Item;
import com.tanthuan.entity.Item_Topping;
import com.tanthuan.entity.Product;
import com.tanthuan.entity.Topping;
import com.tanthuan.service.ParamService;
import com.tanthuan.service.SessionService;

@Controller
public class CartController {
	@Autowired
	ShoppingCartServiceImpl cart;
	
	@Autowired
	ToppingCartServiceImpl cartTopping;
	
	@Autowired
	ProductDAO dao;
	
	@Autowired
	BillDAO daoB;
	
	@Autowired
	BillToppingDAO daoBT;
	
	@Autowired
	BillDetailDAO daoBD;
	
	@Autowired
	CategoryDAO daoC;

	@Autowired
	CustomerDAO daoCus;
	
	@Autowired
	ToppingDAO daoTP;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ParamService paramService;
	
	
	//---------- LỊCH SỬ ĐƠN HÀNG ----------
	
	
	//Hàm view trang lịch sử
	@RequestMapping("/buy_history")
	public String buyHistory(Model model, @RequestParam("p") Optional<Integer> p) {

		getTenUser(model);
		getTheLoai(model);

		if(getIdUser(model)) {
			
			Pageable pageable;
			
			//Xu li khi prev qua gioi han
			if(p.orElse(0).intValue() >= 0) {
				pageable = PageRequest.of(p.orElse(0), 5);
			}else {
				pageable = PageRequest.of(0, 5);
			}
			
			//Xu li khi next qua gioi han
			if(!daoB.findByCus(sessionService.get("id").toString(), pageable).isEmpty()) {
				model.addAttribute("bill",daoB.findByCus(sessionService.get("id").toString(), pageable));
			}else {
				pageable = PageRequest.of(0, 5);
				Page<Bill> list = daoB.findByCus(sessionService.get("id").toString(), pageable);
				model.addAttribute("bill",list);
			}
			
			//Set va show du lieu billDetail neu co ma id
			if(idDT != null) {
				Bill bill = daoB.findById(idDT).get();
				model.addAttribute ("thongTin",bill);
				
				List<BillDetail> listDT = daoBD.findByBillID(idDT);
				model.addAttribute ("check",true);
				model.addAttribute ("listDT",listDT);
				
				List<BillTopping> listTP = daoBT.findByBillID(idDT);
				model.addAttribute("listTP",listTP);
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
		
		model.addAttribute("page","page/bill.jsp");
		return"index";
	}
	
	//Hàm hủy đơn hàng
	@RequestMapping("/cancel/{id}")
	public String cancel(Model model, @PathVariable("id") String id, RedirectAttributes param) {
		daoB.updateStatus("Đã Hủy", id);
		daoB.updateNote("Khách Hàng Hủy", id);
		param.addAttribute("mess","Hủy Đơn Hàng Thành Công!");
		return "redirect:/buy_history";

	}
	
	String idDT = null;
	
	//Chi tiết đơn hàng
	@RequestMapping("/billDetail/{id}")
	public String detail(Model model, @PathVariable("id") String id) {
		idDT = id;
		model.addAttribute("page","page/bill.jsp");
		return "redirect:/buy_history";

	}
	
	
	//---------- ĐẶT HÀNG ----------
	
	
	//Hàm view trang đặt hàng
	@RequestMapping("/buy_view")
	public String datHang(Model model) {
		
		getTenUser(model);
		getTheLoai(model);
		
		if(sessionService.get("id") != null) {
			Customer cus = daoCus.findById(sessionService.get("id").toString()).get();
			Bill bill = new Bill();
			BeanUtils.copyProperties(cus,bill);
			bill.setDiaChi(cus.getAddress());
			model.addAttribute("info",bill);
		}else {
			Bill bill = new Bill();
			bill.setCustomerID(createCusID());
			model.addAttribute("info",bill);
		}
		
		model.addAttribute("page","page/buy.jsp");
		
		return "index";
	}
	
	//Hàm hoàn tất đặt hàng
	@RequestMapping("/buy_confirm")
	public String buyConfirm(Model model, Bill info, RedirectAttributes param) {
		if(info.getCustomerName() != null || info.getDiaChi() != null || info.getEmail() != null || info.getPhone() != null) {
			
			info.setBillID(createBillID());
			info.setStatusName("Chưa Xác Nhận");
			info.setNgay(LocalDate.now());
			info.setGio(LocalTime.now());
			daoB.save(info);
			
			saveItem(info.getBillID());
			
			sessionService.set("page","buy");
			trangThai = "oke";
			return "redirect:/cart/clear";
		}else {
			param.addAttribute("mess","Vui lòng nhập đầy đủ thông tin đặt hàng!");
			return "redirect:/buy_view";
		}
	}
	
	String trangThai = null;
	
	//Lưu dữ liệu từ giỏ hàng session vào billDetail
	public void saveItem(String billID) {	
		Collection<Item> list = cart.getItems();
		list.forEach(k -> {
			BillDetail bill = new BillDetail();
			BeanUtils.copyProperties(k,bill);
			bill.setBillDetailID(createBillDetailID());
			bill.setBillID(billID);
			bill.setPrice(bill.getSoLuong() * bill.getPrice());
			daoBD.save(bill);
			saveItemTopping(bill.getBillDetailID(), billID, k.getItemID());
		});
			
	}
	
	//Lưu dữ liệu từ giỏ hàng topping session vào billTopping
	public void saveItemTopping(String billDetailID, String billID, String itemID) {
		Collection<Item_Topping> list = cartTopping.getItem_Toppings();
		list.forEach(k -> {
			if(k.getItemID().equals(itemID)) {
				BillTopping bill = new BillTopping();
				BeanUtils.copyProperties(k,bill);
				bill.setBillToppingID(createBillToppingID());
				bill.setBillDetailID(billDetailID);
				bill.setBillID(billID);
				bill.setPrice(bill.getSoLuong() * bill.getPrice());
				daoBT.save(bill);
			}		
		});
			
	}
	
	
	//----------HÀM LẤY DỮ LIỆU NGƯỜI DÙNG VÀ KIỂM TRA ----------
	
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
		
	//---------- DETAIL PAGE ----------
	
	//Trang thong tin chi tiet cua san pham 
	@RequestMapping("/detail")
	public String detail(Model model) {
		
		//Load dữ liệu topping
		List<Topping> listTP = daoTP.findAll();
		model.addAttribute("listTP",listTP);
		
		//Load dữ liệu sản phẩm chi tiết
		Product pr = dao.findById(idPro).get();
		model.addAttribute("item",pr);
		
		List<Product> lst = dao.findByCategory(pr.getCategoryID());
		model.addAttribute("items",lst);
		
		model.addAttribute("theLoai", daoC.findById(pr.getCategoryID()).get().getCategoryName());
		getTenUser(model);
		getTheLoai(model);
		sessionService.set("page", "menu");
		model.addAttribute("page","page/details.jsp");
		return "index";
	}
	
	//Tạo biến chứa product id dùng để lấy sản phẩm
	String idPro = "TSSS";
	
	//Hàm nhận giá trị product id để set lên form chi tiết
	@RequestMapping("/detail/{id}")
	public String theLoai(@PathVariable("id") String id, Model model) {
		idPro = id;
		return "redirect:/detail";
	}
	

	//Hàm load dữ liệu thể loại lên view
	public void getTheLoai(Model model) {
		//Load dữ liệu thể loại
		Category cate = new Category();
		model.addAttribute("cate", cate);
		List<Category> cates = daoC.findAll();
		model.addAttribute("cates", cates);
	}
	
	
	//---------- GIỎ HÀNG ----------
	
	
	//Xoa toan bo gio hang
	@RequestMapping("/cart/clear")
	public String clear(RedirectAttributes param) {
		cart.clear();
		cartTopping.clear();
		setSession();
		String page = sessionService.get("page").toString();
		String link = null;	
		if(page.equals("home")) {
			link = "redirect:/trangChu";
		}else {
			link = "redirect:/menuThucUong";
		}
		if(trangThai == "oke") {
			param.addAttribute("mess","Đặt hàng thành công!");
		}else {
			param.addAttribute("mess","Đã xóa giỏ hàng!");
		}
		
		return link;
	}
	
	//Xoa 1 san pham ra khoi gio hang bang id
	@RequestMapping("/cart/delete/{id}")
	public String delete(@PathVariable("id") String id, RedirectAttributes param) {
		cart.remove(id);
		cartTopping.removeByID(id);
		setSession();
		String page = sessionService.get("page").toString();
		String link = null;	
		
		if(page.equals("home")) {
			link = "redirect:/trangChu";
		}else {
			link = "redirect:/menuThucUong";
		}
		param.addAttribute("mess","Đã xóa khỏi giỏ hàng!");
		return link;
	}

	
	//Them 1 san pham vao gio hang
	@RequestMapping("/cart/add/{id}")
	public String add(@PathVariable("id") String id, Model model, RedirectAttributes param) {
		
		//Get dữ liệu sản phẩm theo id
		Product pr = dao.findById(id).get();
		Item item = new Item();
		
		//Copy dữ liệu từ pr vào item để thêm vào cart session
		BeanUtils.copyProperties(pr,item);
		
		String crID = cart.createItemID();
		
		item.setItemID(crID);
		cart.add(item);
		
		//Gọi hàm thêm topping đã chọn vào session
		String[] topping = paramService.getList("topping");
		
		addItemTopping(topping, crID);
		
		String page = sessionService.get("page").toString();
		String link = null;	
		
		if(page.equals("home")) {
			link = "redirect:/trangChu";
		}else {
			link = "redirect:/menuThucUong";
		}
		
		param.addAttribute("messageGH","Đã thêm vào giỏ hàng!");
		setSession();
		return link;
	}
	
		
	//---------- TOPPING ----------
	
	
	//Xoa 1 san pham ra khoi gio hang bang id
	@RequestMapping("/cart_topping/delete/{id}")
	public String deleteTP(@PathVariable("id") String id, RedirectAttributes param) {
		cartTopping.remove(id);
		setSession();
		String page = sessionService.get("page").toString();
		String link = null;	
		
		if(page.equals("home")) {
			link = "redirect:/trangChu";
		}else {
			link = "redirect:/menuThucUong";
		}
		param.addAttribute("mess","Đã xóa khỏi giỏ hàng!");
		return link;
	}
	
	//Hàm thêm dữ liệu topping vào session
	public void addItemTopping(String[] topping, String itemID) {
		if(topping != null) {
			for (int i = 0; i < topping.length; i++) {
				Topping top = daoTP.findById(topping[i]).get();
				Item_Topping topping1 = new Item_Topping();
				BeanUtils.copyProperties(top,topping1);
				String crID = cart.createToppingID();
				topping1.setItemToppingID(crID);
				topping1.setItemID(itemID);
				cartTopping.add(topping1);
			}
		}
		
	} 
	
	
	//---------- HÀM TẠO ID ----------
	
	
	//Hàm tạo mới cusID
	public String createCusID() {
		String id = null;
		
		List<Bill> cus = daoB.findAll();
		
		int first = cus.size();
		
		if(first <= 9) {
			id = "GU0"+ first;
		}else {
			id = "GU"+ first;
		}
		
		return id;
	}
	
	//Hàm tạo mới billDetailID
	public String createBillDetailID() {
		String id = null;
		
		List<BillDetail> bill = daoBD.findAll();
		
		String first = bill.get(bill.size()-1).getBillDetailID();
		
		if(first.length() <= 4) {
			String a = first.substring(2, 3);
			String b = first.substring(3);
			if(a.equals("0") && !b.equals("9")) {
				int num = Integer.parseInt(first.substring(2)) + 1;
				id = "BD0"+ num;
			}else {
				int num = Integer.parseInt(first.substring(2)) + 1;
				id = "BD"+ num;
			}
			
		}else {
			int num = Integer.parseInt(first.substring(2)) + 1;
			id = "BI"+ num;
		}
		
		return id;
		}
	
	//Hàm tạo mới billToppingID
	public String createBillToppingID() {
		String id = null;
		
		List<BillTopping> bill = daoBT.findAll();
		
		String first = bill.get(bill.size()-1).getBillToppingID();
		
		if(first.length() <= 4) {
			String a = first.substring(2, 3);
			String b = first.substring(3);
			if(a.equals("0") && !b.equals("9")) {
				int num = Integer.parseInt(first.substring(2)) + 1;
				id = "BT0"+ num;
			}else {
				int num = Integer.parseInt(first.substring(2)) + 1;
				id = "BT"+ num;
			}
			
		}else {
			int num = Integer.parseInt(first.substring(2)) + 1;
			id = "BT"+ num;
		}
		
		return id;
		}
	
	//Hàm tạo mới billID
	public String createBillID() {
		String id = null;
		
		List<Bill> bill = daoB.findAll();
		
		String first = bill.get(bill.size()-1).getBillID();
		
		if(first.length() <= 4) {
			String a = first.substring(2, 3);
			String b = first.substring(3);
			if(a.equals("0") && !b.equals("9")) {
				int num = Integer.parseInt(first.substring(2)) + 1;
				id = "BI0"+ num;
			}else {
				int num = Integer.parseInt(first.substring(2)) + 1;
				id = "BI"+ num;
			}
			
		}else {
			int num = Integer.parseInt(first.substring(2)) + 1;
			id = "BI"+ num;
		}
		
		return id;
	}
	
	//---------- HÀM SET DỮ LIỆU LÊN SESSION----------
	public void setSession() {
		sessionService.set("count", cart.getCount());
		sessionService.set("count_topping", cartTopping.getCount());
		sessionService.set("amount", cart.getAmount());
		sessionService.set("amount_topping", cartTopping.getAmount());
		sessionService.set("cart", cart.getItems());
		sessionService.set("cart_topping", cartTopping.getItem_Toppings());
		
	}
}
