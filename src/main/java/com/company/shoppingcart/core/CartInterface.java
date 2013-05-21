package com.company.shoppingcart.core;


import java.util.ArrayList;

public interface CartInterface {
	public void add(ArrayList<Object> product);
	public void add(ArrayList<Object> product, int quantity);
	public void remove(ArrayList<Object> product);
	public void remove(ArrayList<Object> product, int quantity);
	public int itemCount(int id);
	public void empty();
	public ArrayList<ArrayList<Object>> getList(); 
	
}
