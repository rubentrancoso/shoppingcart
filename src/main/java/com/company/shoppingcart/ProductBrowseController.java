package com.company.shoppingcart;

import java.io.IOException;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.shoppingcart.core.Functions;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ProductBrowseController {
	
	//private static final Logger logger = LoggerFactory.getLogger(ProductBrowseController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 */
	@RequestMapping(value = "/browse/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IOException {
		
		model.addAttribute("inventory", Functions.get_the_inventory() );
		model.addAttribute("body", "browse" );
		
		return "template";
	}
	
}
