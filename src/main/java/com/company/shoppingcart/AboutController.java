package com.company.shoppingcart;

import java.util.Locale;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AboutController {
	
	//private static final Logger logger = LoggerFactory.getLogger(AboutController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/about/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		model.addAttribute("body", "about" );

		
		return "template";
	}
	
}
