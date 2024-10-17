package com.cream.controller;

import java.io.File;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cream.dto.SalesDTO;
import com.cream.service.SalesService;
import com.cream.service.SalesServiceImpl;
import com.cream.util.AWSService;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@MultipartConfig( //어노테이션을 통해  서블릿이 파일 업로드 기능을 할 수 있도록 웹 컨테이너에 지시
        maxFileSize = 1024 * 1024 * 5, //5M - 한 번에 업로드 할 수 있는 파일 크기 제한
       maxRequestSize = 1024 * 1024 * 50 //50M -전체 요청의 크기 제한. 기본값은 무제한 
)
public class SalesAjaxController implements RestController {
	SalesService service = new SalesServiceImpl();
	AWSService awsService = AWSService.getInstance();
	

	
	public Object selectAll(HttpServletRequest request, HttpServletResponse response) throws SQLException {
    	int shoesNo = Integer.parseInt(request.getParameter("shoesNo"));
    	int productNo = Integer.parseInt(request.getParameter("productNo"));
    	
    	List<SalesDTO> sales = service.selectAll(shoesNo,productNo);
        System.out.println("사진 등록 됐냐?????");
        return sales;
            
       
    }
	
	public Object insertSalesImg(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Part part;
		int result=0;
		try {
			part = request.getPart("file");
			String fileName = this.getFilename(part);
	    	int salesNo = Integer.parseInt(request.getParameter("salesNo"));
	    	long fileSize = part.getSize();
	    	String saveDir = request.getServletContext().getRealPath("/save"); 

	    	if (fileName!=null) {
	    	            part.write( saveDir + "/"+ fileName);
	    	}
	    	File file = new File(saveDir, fileName); 

	    	awsService.upload(file, fileName);
	    	String url = fileName;
	    	if(url.equals("")) {
	    		url  = "https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/no-shoes.webp";
	    		System.out.println("파일 업로드 안했을때 url :"+url);
		    	result = service.insertSalesImg(salesNo,url,fileSize);
	    	}else {
	    		saveDir = "https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/"+URLEncoder.encode(fileName,"UTF-8"); 
	    		System.out.println("파일 업로드 했을떄 fimeName :"+fileName);
	    		result = service.insertSalesImg(salesNo,saveDir,fileSize);
	    	}
	    	
		} catch (Exception e) {
			
		}
	
           
        return result;
            
       
    }
	
	private String getFilename(Part part) {
        String headerContent = part.getHeader("content-disposition");
        //contentDisp의 결과 form-data; name="fileName"; filename="추가한 파일 이름"
        System.out.println(headerContent);
        String[] split = headerContent.split(";");
        for (int i = 0; i < split.length; i++) {
            String temp = split[i];
            if (temp.trim().startsWith("filename")) {
                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
            }
        }
        return null;
    }
}
