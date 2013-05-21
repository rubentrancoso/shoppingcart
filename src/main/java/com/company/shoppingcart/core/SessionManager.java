package com.company.shoppingcart.core;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.web.context.WebApplicationContext;

import com.company.shoppingcart.services.CartService;

public class SessionManager implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent ev) {
		System.out.println("Session Created");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent ev) {
		System.out.println("Session Destroyed");
		recoverInventory(ev);
	}
	
    private void recoverInventory(HttpSessionEvent sessionEvent){
    	ServletContext servletContext = sessionEvent.getSession().getServletContext();
    	WebApplicationContext ctx = (WebApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    	CartService cartService = (CartService) ctx.getBean("cartService");
        CartInterface cart = (CartInterface)cartService.getCart();
        cart.empty();
    }

}