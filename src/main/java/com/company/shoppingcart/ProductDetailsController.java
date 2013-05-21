package com.company.shoppingcart;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.shoppingcart.core.Functions;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ProductDetailsController {
		
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/details/", method = RequestMethod.GET)
	public String homeGet(HttpServletRequest request, Locale locale, Model model) {
		
		Integer code = Integer.valueOf(request.getParameter("code"));
		
		model.addAttribute("item", Functions.get_inventory_item(code) );
		model.addAttribute("code", code );
		model.addAttribute("body", "detail" );

		
		return "template";
	}
	
//	@RequestMapping(value = "/details/", method = RequestMethod.POST)
//	public String homePost(HttpServletRequest request, Locale locale, Model model) {
//		logger.info("Detail The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		Integer order = Integer.valueOf(request.getParameter("quantity"));
//		Integer code = Integer.valueOf(request.getParameter("code"));
//		
//		model.addAttribute("item", Functions.get_inventory_item(code) );
//		model.addAttribute("order", order );
//		model.addAttribute("body", "detail" );
//		model.addAttribute("serverTime", formattedDate );
//		
//		return "template";
//	}
	
}
