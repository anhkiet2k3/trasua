package com.tanthuan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item_Topping {
	String itemID;
	String itemToppingID;
	String toppingID;
	String toppingName;
	Integer soLuong = 1;
	Float price;
}
