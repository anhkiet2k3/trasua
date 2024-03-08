package com.tanthuan.dao;

import java.util.Collection;

import com.tanthuan.entity.Item_Topping;

public interface ToppingCartService {
	
	
	/**
	 * Thêm mặt hàng vào giỏ hoặc tăng số lượng lên 1 nếu đã tồn tại
	 * @param Item_Topping là mặt hàng cần thêm
	 * @return mặt hàng đã được thêm vào hoặc cập nhật số lượng
	 */
	Item_Topping add(Item_Topping Item_Topping);
	
	
	/**
	 * Xóa mặt hàng khỏi giỏ
	 * @param id mã mặt hàng cần xóa
	 */
	void remove(String id);
	
	
	/**
	 * Thay đổi số lượng lên của mặt hàng trong giỏ
	 * @param id mã mặt hàng
	 * @param qty số lượng mới
	 * @return mặt hàng đã được thay đổi số lượng
	 */
	Item_Topping update(String id, int qty);
	
	
	/**
	 * Xóa sạch các mặt hàng trong giỏ
	 */
	void clear();
	
	
	/**
	 * Lấy tất cả các mặt hàng trong giỏ
	 */
	Collection<Item_Topping> getItem_Toppings();
	
	
	/**
	 * Lấy tổng số lượng các mặt hàng trong giỏ
	 */
	int getCount();
	
	
	/**
	 * Lấy tổng số tiền tất cả các mặt hàng trong giỏ
	 */
	double getAmount();
}
