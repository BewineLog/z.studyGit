package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class AddProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//request
		Product productVO = new Product();
		
		productVO.setFileName(request.getParameter("fileName"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setProdName(request.getParameter("prodName"));
		
		ProductServiceImpl impl = new ProductServiceImpl();
		impl.addProduct(productVO);
		
		request.setAttribute("productVO", productVO);
		
		return "forward:/product/addProduct.jsp?menu=manage";
	}
}
