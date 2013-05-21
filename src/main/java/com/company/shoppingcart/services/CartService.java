package com.company.shoppingcart.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.shoppingcart.core.CartInterface;

//@Service
public class CartService {
	
	@Autowired
	private com.company.shoppingcart.core.CartInterface cart;

	public CartInterface getCart() {
		return this.cart;
	}

}
