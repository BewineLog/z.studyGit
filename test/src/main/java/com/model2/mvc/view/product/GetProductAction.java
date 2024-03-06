package com.model2.mvc.view.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.SpringUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class GetProductAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
//		Product product = new Product();
//		
//		ProductServiceImpl impl = new ProductServiceImpl();
		Product product = new Product();
		ProductService impl = (ProductService)SpringUtil.getProductService();
		
		List<Integer> history = null;
		
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("GetProductAction prodNo::" + prodNo);
		
		product = impl.getProduct(prodNo);
		
		
		//
		//���߿� session ���� ���� �ʿ� 
		//
		/*
		if((history = (List<Integer>)getServletContext().getAttribute("history")) == null) {
			history = new ArrayList<Integer>();
		}
		
		history.add(prodNo);
		getServletContext().setAttribute("history", history);	
		*/
		
		
		
		if(product != null) {
			request.setAttribute("product", product);
			System.out.println(product.toString());
		}
		
		System.out.println("getP action menu ::" + request.getParameter("menu"));
		
		if (request.getParameter("menu").equals("search") ) {
			Cookie[] cookie = request.getCookies();

			boolean isCookie = false;
			for (int i = 0; i < cookie.length; i++) {
				if (cookie[i].getName().equals("history")) {
					System.out.println("Action for running");
					isCookie = true;
					System.out.println("Action Cookie before::" + cookie[i].getValue());
					cookie[i].setValue(cookie[i].getValue() + "and" + Integer.toString(prodNo));
					System.out.println("Action Cookie::" + cookie[i].getValue());
					response.addCookie(cookie[i]);
				}
			}
			
			System.out.println("isCookie::"+isCookie);

			if (!isCookie) {
				System.out.println("Action for cookie setting");
				Cookie c = new Cookie("history", Integer.toString(prodNo));
				response.addCookie(c);
			}
		}
		//response.addCookie(new Cookie("history", Integer.toString(prodNo)));
		
		
		System.out.println("getProductAction::" + request.getParameter("menu"));
		if(request.getParameter("menu").equals("manage")) {
			return "forward:/product/updateProductView.jsp?menu=" + request.getParameter("menu");
			
		}else {
			return "forward:/product/getProduct.jsp?menu=" + request.getParameter("menu");
		}
	}

}
