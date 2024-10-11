package com.cream.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *  사용자의 모든 요청을 처리할 진입점 Controller이다(FrontController의 역할한다)
 */
@WebServlet(urlPatterns = "/ajax" , loadOnStartup = 1)
public class AjaxDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
     Map<String, RestController> map;
     Map<String, Class<?>> clzMap;
 	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext application = config.getServletContext();
		Object obj = application.getAttribute("ajaxMap");
		map = (Map<String, RestController>)obj;
		
		clzMap = (Map<String, Class<?>>)config.getServletContext().getAttribute("ajaxClzMap");
		
	}
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key"); //customer
		String methodName = request.getParameter("methodName"); //idCheck , insert , selectAll
		
		if(key ==null || key.equals("")) {
			key="customer";
			methodName="test";
		}
		
		System.out.println("key = " + key+", methodName = " + methodName);
		try {
			Class<?> clz = clzMap.get(key);
			Method method = clz.getMethod(methodName, HttpServletRequest.class , HttpServletResponse.class);
			
			RestController controller = map.get(key);
			method.invoke(controller, request , response);
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}//service 메소드 끝 

}








