package com.tanthuan.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.tanthuan.entity.Item_Topping;
import com.tanthuan.entity.Topping;
import com.tanthuan.service.ParamService;


@SessionScope
@Service
public class ToppingCartServiceImpl implements ToppingCartService{
	public Map<String, Item_Topping> map = new HashMap<>();
	
	@Autowired
	ParamService paramService;
	
	@Override
	public Item_Topping add(Item_Topping Item_Topping) {
		map.put(Item_Topping.getItemToppingID(), Item_Topping);
		return Item_Topping;
	}
	
	@Override
	public void remove(String id) {
		map.remove(id);
	}
	
	public void removeByID(String id) {
		Iterator<Map.Entry<String, Item_Topping>> it = map.entrySet().iterator();

		while (it.hasNext()) {
		  Map.Entry<String, Item_Topping> entry = it.next();
		  if(entry.getValue().getItemID().equals(id)){
		    it.remove();
		  }
		}
	}
	
	@Override
	public Item_Topping update(String id, int qty) {
		Item_Topping Item_Topping = map.get(id);
		Item_Topping.setSoLuong(qty);
		return Item_Topping;
	}
	
	@Override
	public void clear() {
		map.clear();
	}
	
	@Override
	public Collection<Item_Topping> getItem_Toppings() {
		return map.values();
	}
	
	@Override
	public int getCount() {
			return map.values().stream()
					.mapToInt(Item_Topping -> Item_Topping.getSoLuong())
					.sum();

	}
	
	@Override
	public double getAmount() {
			return map.values().stream()
					.mapToDouble(Item_Topping -> Item_Topping.getPrice())
					.sum();
	}

}
