package com.company.shoppingcart.database;

import org.springframework.context.annotation.Scope;

@Scope("singleton")
public class Menu extends Database {
	@Override
	public void init() {
		System.out.println("Menu init");
		/*   name           url         id  */
		add( "home",		"" );
		add( "about",		"about" );
		add( "browse",		"browse" );
		add( "cart",		"cart" );
		add( "empty cart",	"cart/#", 	"empty_cart" );
	}
	
}
