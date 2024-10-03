package com.cream.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.cream.controller.Controller;

@WebListener
public class HandlerMappingListener implements ServletContextListener {

	
	public void  contextInitialized(ServletContextEvent se) {
		Map<String,Class<?>> classMap = new HashMap<String, Class<?>>();
		Map<String,Controller> map = new HashMap<>();

		Map<String,String> sqlMap = new HashMap<String, String>();
		
		ResourceBundle rb = ResourceBundle.getBundle("actionMapping");
		ResourceBundle rbSql = ResourceBundle.getBundle("dbQuery");

		try {
			for(String key :rb.keySet()) {
				String value = rb.getString(key);
				Class<?> className = Class.forName(value);
				Controller con = (Controller) className.getDeclaredConstructor().newInstance();
				classMap.put(key, className);
				map.put(key, con);
			}
			
			for(String key :rbSql.keySet()) {
				String value = rbSql.getString(key);	

				sqlMap.put(key, value);
			}
			
			
		} catch (Exception e) {
					e.printStackTrace();
		}
			
		ServletContext application = se.getServletContext();	
		application.setAttribute("classMap", classMap);	
		application.setAttribute("sqlMap", sqlMap);	
		application.setAttribute("map", map);	
		application.setAttribute("path", application.getContextPath());
			

	}
		
		

	
	
}
