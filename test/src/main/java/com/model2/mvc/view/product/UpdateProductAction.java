package com.model2.mvc.view.product;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.SpringUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class UpdateProductAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		

		Product Product = new Product();
		
//		ProductServiceImpl impl = new ProductServiceImpl();
		ProductService impl = (ProductService)SpringUtil.getProductService();
		
		List<Integer> history = null;
		
		
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		Product = impl.getProduct(prodNo);
		
		
		
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
		
		
		
		if(Product != null) {
			request.setAttribute("Product", Product);
			System.out.println(Product.toString());
		}
		
		System.out.println("getP action menu ::" + request.getParameter("menu"));
		
		return "forward:/listProduct.do";
	}

}

