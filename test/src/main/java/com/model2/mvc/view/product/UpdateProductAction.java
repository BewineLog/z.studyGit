package com.model2.mvc.view.product;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class UpdateProductAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		

		ProductVO productVO = new ProductVO();
		
		ProductServiceImpl impl = new ProductServiceImpl();
		
		List<Integer> history = null;
		
		
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		productVO = impl.getProduct(prodNo);
		
		
		
		//
		//나중에 session 으로 변경 필요 
		//
		/*
		if((history = (List<Integer>)getServletContext().getAttribute("history")) == null) {
			history = new ArrayList<Integer>();
		}
		
		history.add(prodNo);
		getServletContext().setAttribute("history", history);	
		*/
		
		
		
		if(productVO != null) {
			request.setAttribute("productVO", productVO);
			System.out.println(productVO.toString());
		}
		
		System.out.println("getP action menu ::" + request.getParameter("menu"));
		
		return "forward:/listProduct.do";
	}

}

