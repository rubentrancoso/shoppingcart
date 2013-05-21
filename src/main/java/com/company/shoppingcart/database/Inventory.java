package com.company.shoppingcart.database;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;

@Scope("singleton")
public class Inventory extends Database {
	@Override
	public void init() {
		System.out.println("Inventory init");
		add( 1, 	"sandal   ",		10.2,	1, 	"pic1.png" );
		add( 2, 	"boot     ",		35.7, 	8, 	"pic2.png" );
		add( 3, 	"skate    ",		25.3, 	4, 	"pic3.png" );
		add( 4, 	"shoe     ",		10.2,	1, 	"pic1.png" );
		add( 5, 	"running  ",		35.7, 	8, 	"pic2.png" );
		add( 6, 	"adventure",		25.3, 	4, 	"pic3.png" );
		add( 7, 	"modern   ",		10.2,	1, 	"pic1.png" );
		add( 8, 	"casual   ",		35.7, 	8, 	"pic2.png" );
		add( 9, 	"atletic  ",		25.3, 	4, 	"pic3.png" );
		add( 10, 	"kids     ",		10.2,	1, 	"pic1.png" );
		add( 11, 	"special  ",		35.7, 	8, 	"pic2.png" );
		add( 12, 	"basket   ",		25.3, 	4, 	"pic3.png" );
		add( 13, 	"beach    ",		10.2,	1, 	"pic1.png" );
		add( 14, 	"night    ",		35.7, 	8, 	"pic2.png" );
		add( 15, 	"confort  ",		25.3, 	4, 	"pic3.png" );
		add( 16, 	"marathon ",		10.2,	1, 	"pic1.png" );
		add( 17, 	"rugby    ",		35.7, 	8, 	"pic2.png" );

	}
	
	public int getCountById(int id) {
		int size = -1;
		for(ArrayList<Object> item : data) {
			if(item.get(0).equals(id)) {
				size = (Integer)item.get(3);
			}
		}
		return size;

	}

	public void returnProduct(ArrayList<Object> product) {
		int id = (Integer)product.get(0);
		for(ArrayList<Object> item : data) {
			if(item.get(0).equals(id)) {
				int count = (Integer)item.get(3);
				item.set(3, ++count);
				break;
			}
		}
	}

	public void decrementById(int id) {
		for(ArrayList<Object> item : data) {
			if(item.get(0).equals(id)) {
				int count = (Integer)item.get(3);
				item.set(3, count - 1);
			}
		}
	}
	
	public void incrementById(int id) {
		for(ArrayList<Object> item : data) {
			if(item.get(0).equals(id)) {
				int count = (Integer)item.get(3);
				item.set(3, count + 1);
			}
		}		
	}
	
}
