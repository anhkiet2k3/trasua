package com.tanthuan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	String itemID;
	String productID;
	String productName;
	String ghiChu;
	float price;
	int soLuong;
}
