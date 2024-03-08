package com.tanthuan.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.tanthuan.entity.Item;
import com.tanthuan.entity.Product;
import com.tanthuan.service.ParamService;
import com.tanthuan.service.SessionService;


@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	Map<String, Item> map = new HashMap<>();
	
	@Autowired
	ParamService paramService;
	
	@Autowired
	SessionService sessionService;
	
	@Override
	public Item add(Item item) {
		
		Item item1 = map.get(item.getItemID());
		if(item1 == null) {
			String sl = paramService.getString("quantity","1");
			String ghiChu = paramService.getString("ghiChu",null);
			try {
				int soLuong = Integer.parseInt(sl);
				item.setGhiChu(ghiChu);
				item.setSoLuong(soLuong);
				map.put(item.getItemID(),item);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			//item1.setSoLuong(item1.getSoLuong() + 1);
			map.put(item.getItemID()+"1", item1);
		}
		return item;
	}
	
	//Hàm tạo itemID cho item session product
	public String createItemID() {
		
		String id = sessionService.get("number");
		String kq = null;

		if(id == null){
			kq = "IT1";
		} else {
			int num = Integer.parseInt(id.substring(2)) + 1;
			kq = "IT"+ num;	
		}
		sessionService.set("number", kq);
		return kq;
	}
	
	//Hàm tạo itemToppingID cho item session topping
	public String createToppingID() {
		
		String id = sessionService.get("numberTP");
		String kq = null;
		if(id == null){
			kq = "TP1";
		} else {
			int num = Integer.parseInt(id.substring(2)) + 1;
			kq = "TP"+ num;	
		}
		sessionService.set("numberTP", kq);
		return kq;
	}
	
	@Override
	public void remove(String id) {
		Item item = map.get(id);
		if(item.getSoLuong() <= 0) {
			item.setSoLuong(0);
		}else {
			item.setSoLuong(item.getSoLuong()-1);
		}
		map.remove(id);
	}
	
	@Override
	public Item update(String id, int qty) {
		Item item = map.get(id);
		item.setSoLuong(qty);
		return item;
	}
	
	@Override
	public void clear() {
		map.clear();
	}
	
	@Override
	public Collection<Item> getItems() {
		return map.values();
	}
	
	@Override
	public int getCount() {
			return map.values().stream()
					.mapToInt(item -> item.getSoLuong())
					.sum();

	}
	
	@Override
	public double getAmount() {
			return map.values().stream()
					.mapToDouble(item -> item.getPrice()*item.getSoLuong())
					.sum();
	}

}
