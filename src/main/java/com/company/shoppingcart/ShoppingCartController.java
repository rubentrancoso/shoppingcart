package com.company.shoppingcart;

import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.shoppingcart.services.CartService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ShoppingCartController {

	@Autowired
	private CartService cartService;  

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/cart/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {


		//model.addAttribute("cart", this.cartService.getCart() );
		model.addAttribute("body", "cart" );
		
		return "template";
	}
	
}
