package com.cream.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.cream.controller.Controller;
import com.cream.controller.RestController;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HandlerMappingListener implements ServletContextListener {

	
	@Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext application = event.getServletContext();

        // 일반 Controller 맵핑
        Map<String, Class<?>> classMap = new HashMap<>();
        Map<String, Controller> controllerMap = new HashMap<>();
        
        // RestController 맵핑
        Map<String, RestController> ajaxControllerMap = new HashMap<>();
        Map<String, Class<?>> ajaxClassMap = new HashMap<>();

        // SQL 쿼리 맵핑
        Map<String, String> sqlMap = new HashMap<>();

        try {
            // 일반 Controller와 관련된 설정 파일 로딩
            ResourceBundle actionMappingBundle = ResourceBundle.getBundle("actionMapping");
            for (String key : actionMappingBundle.keySet()) {
                String className = actionMappingBundle.getString(key);
                Class<?> clazz = Class.forName(className);
                Controller controllerInstance = (Controller) clazz.getDeclaredConstructor().newInstance();
                classMap.put(key, clazz);
                controllerMap.put(key, controllerInstance);
            }

            // RestController와 관련된 설정 파일 로딩
            ResourceBundle ajaxMappingBundle = ResourceBundle.getBundle("ajaxMapping");
            for (String key : ajaxMappingBundle.keySet()) {
                String className = ajaxMappingBundle.getString(key);
                Class<?> clazz = Class.forName(className);
                RestController ajaxControllerInstance = (RestController) clazz.getDeclaredConstructor().newInstance();
                ajaxClassMap.put(key, clazz);
                ajaxControllerMap.put(key, ajaxControllerInstance);
            }

            // SQL 쿼리 설정 파일 로딩
            ResourceBundle dbQueryBundle = ResourceBundle.getBundle("dbQuery");
            for (String key : dbQueryBundle.keySet()) {
                String query = dbQueryBundle.getString(key);
                sqlMap.put(key, query);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 모든 맵을 서블릿 컨텍스트에 저장
        application.setAttribute("classMap", classMap);
        application.setAttribute("map", controllerMap);
        application.setAttribute("ajaxMap", ajaxControllerMap);
        application.setAttribute("ajaxClzMap", ajaxClassMap);
        application.setAttribute("sqlMap", sqlMap);
        application.setAttribute("path", application.getContextPath());
    }
		
		

	
	
}
