package com.company.shoppingcart.core;


import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.company.shoppingcart.database.Inventory;

@Scope("session")
public class Cart implements CartInterface, Serializable {

	private static final long serialVersionUID = 4770136681194802425L;
	private ArrayList<ArrayList<Object>> cart;
	@Autowired
	private Inventory inventory;
	
	public static final int  ID = 0;
	public static final int  QUANTITY = 1;
	public static final int PRODUCT = 2;
	
	public Cart() {
		cart = new ArrayList<ArrayList<Object>>();
	}
	
//	public void add(ArrayList<Object> product) {
//		int id = (Integer)product.get(0);
//		int count = itemCount(id);
//		
//		//inventory.decrementById(id);
//		ArrayList<Object> item = new ArrayList<Object>();
//		item.add(new Integer(id));
//		item.add(product);
//		cart.add(product);
//	}
	
	public void add(ArrayList<Object> product) {
		int id = (Integer)product.get(Cart.ID);
		int count = itemCount(id);
		if(count == 0) {
			ArrayList<Object> cartItem = new ArrayList<Object>();
			
			cartItem.add(Cart.ID, new Integer(id));
			cartItem.add(Cart.QUANTITY,  1);
			cartItem.add(Cart.PRODUCT, product);
			
			cart.add(cartItem);
		} else {
			ArrayList<Object> item = getItem(id);
			Integer quantity = (Integer)item.get(Cart.QUANTITY);
			item.set(Cart.QUANTITY, ++quantity);
		}
		inventory.decrementById(id);
	}
	
	private ArrayList<Object> getItem(int id) {
		for(ArrayList<Object> item : cart) {
			ArrayList<Object> currentItem = item;
			int currentItemId = (Integer)currentItem.get(Cart.ID);
			if(id == currentItemId) {
				return item; 
			}
		}
		return null;
	}
	
	public int itemCount(int id) {
		int count = 0;
		for(ArrayList<Object> item : cart) {
			ArrayList<Object> currentItem = item;
			int currentItemId = (Integer)currentItem.get(Cart.ID);
			if(id == currentItemId) {
				count = (Integer)currentItem.get(Cart.QUANTITY);
				return count; 
			}
		}
		return count;
	}
	
	public void add(ArrayList<Object> product, int quantity) {
		for(int i = 0; i<quantity; i++)
			this.add(product);
	}

	public void remove(ArrayList<Object> product) {
		int id = (Integer)product.get(Cart.ID);
		for(ArrayList<Object> item : cart) {
			int currentItemId = (Integer)item.get(Cart.ID);
			if(currentItemId == id) {
				int count = itemCount(id);
				if(count > 1) {
					item.set(Cart.QUANTITY, --count );
					inventory.incrementById(id);
				} else if(count == 1) {
						cart.remove(item);
						inventory.incrementById(id);
				}
				break;
			}
		}
	}
	
	public void remove(ArrayList<Object> product, int quantity) {
		for(int i = 0; i<quantity; i++)
			this.remove(product);
	}

	public void empty() {
		for(ArrayList<Object> item : cart) {
			@SuppressWarnings("unchecked")
			ArrayList<Object> product = (ArrayList<Object>)item.get(Cart.PRODUCT);
			int quantity = (Integer)item.get(Cart.QUANTITY);
			for(int i=0; i<quantity; i++) {
				inventory.returnProduct(product);
			}
		}
		cart.clear();
	}
	
	public ArrayList<ArrayList<Object>> getList() {
		return cart;
	}
	
}
