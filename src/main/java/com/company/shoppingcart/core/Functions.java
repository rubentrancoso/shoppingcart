package com.company.shoppingcart.core;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import com.company.shoppingcart.database.Inventory;
import com.company.shoppingcart.database.Menu;

public class Functions {
	
	private static Functions self = null;
	static JspWriter jspOut;
	static HttpServletRequest jspReq;
	static ArrayList<String> styles;
	static ArrayList<String> scripts;
	static ArrayList<String> localScripts;

	protected Functions(HttpServletRequest request, JspWriter out) {
		jspReq = request;
		jspOut = out;
		reset();
	}
	
	private static void reset() {
		styles = new ArrayList<String>();
		scripts = new ArrayList<String>();
		localScripts = new ArrayList<String>();		
	}
	
	private void setReq(HttpServletRequest request) {
		jspReq = request;
	}
	
	private void setout(JspWriter out) {
		jspOut = out;
	}

	static public void init(HttpServletRequest request, JspWriter out) {
		if(self == null) {
			self = new Functions(request, out);
		} else {
			self.setout(out);
			self.setReq(request);
			reset();
		}
	}

	static public void the_context() throws IOException {
		jspOut.write(get_the_context());
	}

	static public String get_the_context() {
		String context = jspReq.getContextPath();
		context = (context != "" ? context + "/" : "/");
		return context;
	}
	
	static public void appendStyle(String uri) {
		styles.add(uri);
	}
	
	static public void appendScript(String script) {
		scripts.add(script);
	}
	
	static public void appendLocalScript(String script) {
		localScripts.add(script);
	}

	
	static public void the_header() throws IOException {
		jspOut.write("<head>");
		for(String script: scripts) {
			jspOut.write("<script src=\"" + script + "\"></script>");
		}
		for(String script: localScripts) {
			jspOut.write("<script src=\"" + get_the_context() + script + "\"></script>");
		}
		for(String style: styles) {
			jspOut.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + get_the_context() + style +  "\">");
		}
		jspOut.write("</head>");
	}
	
	static public void the_menu() throws IOException {
		Menu menu = (Menu)SpringContext.getApplicationContext().getBean("menu");
		ArrayList<ArrayList<Object>> amenu = menu.query();
		Functions.renderMenu(amenu, Functions.get_the_context(), jspOut);
	}
	
	static private Object getValue(ArrayList<Object> list, int index) {
		Object result = null;
		try {
			result = list.get(index);
		} catch (Exception e) {}
		return result;
	}
	
	static private void renderMenu(ArrayList<ArrayList<Object>> table, String context, JspWriter out) throws IOException {
		out.print("<div class=\"menu\">");
		for ( ArrayList<Object> row : table ) {
			out.print("<div class=\"item\">");
			Object name = Functions.getValue(row, 0);
			Object anchor = Functions.getValue(row, 1);
			Object id = "";
			id = Functions.getValue(row, 2);;
			
			anchor = anchor.equals("") ? "" : anchor + "/";
			
			String id_attribute =  (id != null ? "id=\"" + id + "\"" : "");
			
			out.print("<a " + id_attribute + " href=\"" + context + anchor + "\">"+ name + "</a>");
			out.print("</div>");
		}
		out.print("</div>");
	}
	
	public static ArrayList<ArrayList<Object>>  get_the_inventory() throws IOException {
		Inventory inventory = (Inventory)SpringContext.getApplicationContext().getBean("inventory");
		ArrayList<ArrayList<Object>> inventorydata = inventory.query();
		return inventorydata;
	}

	public static ArrayList<Object> get_inventory_item(int code) {
		Inventory inventory = (Inventory)SpringContext.getApplicationContext().getBean("inventory");
		return inventory.getById(code);
	}

	public static String build_json(ArrayList<ArrayList<Object>> data) {
		String result = "{\"cart\": [";
		boolean didfirst = false;
		for(ArrayList<Object> item : data) {
			if(didfirst)
				result += ",";
			result += "{";
			result += "\"id\": " + item.get(0) + ",";
			result += "\"quantity\": " + item.get(1) + ",";
			
			@SuppressWarnings("unchecked")
			ArrayList<Object>product = (ArrayList<Object>)item.get(2);
			result += "\"name\": \"" + ((String)product.get(1)).trim() + "\",";
			result += "\"price\": " + product.get(2) + ",";
			result += "\"available\": " + product.get(3) + ",";
			result += "\"image\": \"" + ((String)product.get(4)).trim() + "\"";
			result += "}";
			if(didfirst == false) didfirst = true;
		}
		result += "]}";
		return result;
	}
	
}
