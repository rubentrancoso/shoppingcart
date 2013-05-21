package com.company.shoppingcart;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.company.shoppingcart.core.CartInterface;
import com.company.shoppingcart.core.Functions;
import com.company.shoppingcart.database.Inventory;
import com.company.shoppingcart.services.CartService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/ajax/", method = RequestMethod.GET)
public class AjaxController {

	@Autowired
	private CartService cartService;
	@Autowired
	private Inventory inventory; 

	
	@RequestMapping(method = RequestMethod.GET)
	public String silent(Model model) {
		String result = "silence is gold";
	    model.addAttribute("result", result );
		return "ajaxtemplate";
	}

	@RequestMapping(value="add_to_cart/{code}", method = RequestMethod.GET)
	@ResponseBody
	protected String add_quantity(
			@PathVariable int code,
			HttpServletRequest request
			)  {
		CartInterface cart = this.cartService.getCart();
		
		int quantity = Integer.parseInt(request.getParameter("order_quantity"));
		int count = inventory.getCountById(code);

		String result = "";
		if(quantity > 0 && quantity <= count) {
			cart.add(inventory.getById(code), quantity);
			int taken = cart.itemCount(code);
			result = "{ \"quantity\":" + taken + ", \"available\":" + ( count - quantity ) + ", \"message\":\"" + quantity + " item(s) were added to cart\"}";
		} else {
			int taken = cart.itemCount(code);
			result = "{ \"quantity\":" + taken + ", \"available\":" + count + ", \"message\":\"invalid try. " + count + " item(s) are available\"}";
		}
		return result;
	}
	
	@RequestMapping(value="remove_from_cart/{code}", method = RequestMethod.GET)
	@ResponseBody
	protected String remove_quantity(
			@PathVariable int code,
			HttpServletRequest request
			)  {
		CartInterface cart = this.cartService.getCart();
		
		int quantity = Integer.parseInt(request.getParameter("order_quantity"));
		String result = "";
		
		ArrayList<Object> product = inventory.getById(code);
		cart.remove(product, quantity);
		int taken = cart.itemCount(code);
		result = "{ \"quantity\":" + taken + ", \"message\":\"" + quantity + " item(s) were removed from cart\"}";
		return result;
	}

	@RequestMapping(value="empty_cart/", method = RequestMethod.GET)
	@ResponseBody
	public String empty_cart(
			HttpServletRequest request
			)  {
		CartInterface cart = this.cartService.getCart();
		
		cart.empty();
		String result = "your cart is now empty";
		return result;
	}
	
	@RequestMapping(value="render_cart/", method = RequestMethod.GET)
	@ResponseBody
	public String render_cart(
			HttpServletRequest request
			)  {
		CartInterface cart = this.cartService.getCart();
		ArrayList<ArrayList<Object>> data = cart.getList();
		String result = Functions.build_json(data);
		return result;
	}
}
