package com.cream.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/front",loadOnStartup = 1)
@MultipartConfig( //어노테이션을 통해  서블릿이 파일 업로드 기능을 할 수 있도록 웹 컨테이너에 지시
        maxFileSize = 1024 * 1024 * 5, //5M - 한 번에 업로드 할 수 있는 파일 크기 제한
       maxRequestSize = 1024 * 1024 * 50 //50M -전체 요청의 크기 제한. 기본값은 무제한 
)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String,Class<?>> classMap = new HashMap<String, Class<?>>();
	private Map<String,String> sqlMap = new HashMap<String, String>();
	Map<String,Controller> map = new HashMap<String, Controller>();

	
	@Override
	public void init(ServletConfig config) throws ServletException{
			ServletContext application = config.getServletContext();
			classMap =(Map<String, Class<?>>) application.getAttribute("classMap");
			sqlMap = (Map<String, String>) application.getAttribute("sqlMap");
			map  = (Map<String, Controller>) application.getAttribute("map");
			
			
	}
	@Override
	public void service(HttpServletRequest request,HttpServletResponse response) {
			System.out.println("servlet service 왔는가..??");
		
			String key = request.getParameter("key");
			String methodName = request.getParameter("methodName");
			System.out.println("key : " + key + ", methodName : " + methodName);
			
			try {
				Controller con = map.get(key);
				Class<?> className = classMap.get(key);
				Method method = className.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
				ModelAndView mv= (ModelAndView) method.invoke(con,request,response);
				
				if(mv.isRedirect()) {
					response.sendRedirect(mv.getUrl());
				}else {
					request.getRequestDispatcher(mv.getUrl()).forward(request, response);

				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
	
		
	}
	
	
}
