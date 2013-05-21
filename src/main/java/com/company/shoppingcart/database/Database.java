package com.company.shoppingcart.database;

import java.util.ArrayList;
 
public class Database {

	protected ArrayList<ArrayList<Object>> data;
	
	protected Database database;
	
	public Database() {
		data = new ArrayList<ArrayList<Object>>();
		init();
	}
	
	public ArrayList<ArrayList<Object>> query() {
		return data;
	}
	
	public void add(Object... fields ) {
		
		ArrayList<Object> row = new ArrayList<Object>();
		
		for ( Object field : fields ) {
			row.add(field);
		}
		data.add(row);
	}
	
	public void init() {
		System.out.println("Database init");
	}
	
	public ArrayList<Object> getById(int id) {
		//connect();
		for(ArrayList<Object> item : data) {
			if(item.get(0).equals(id)) {
				return item;
			}
		}
		return null;

	}
	
}
