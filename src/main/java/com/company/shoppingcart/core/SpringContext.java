package com.company.shoppingcart.core;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContext implements ApplicationContextAware {
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext pcontext) throws BeansException {
		context = pcontext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return context;
	}
}