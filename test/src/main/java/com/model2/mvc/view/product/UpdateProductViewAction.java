package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class UpdateProductViewAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Product Product = new Product();
		
		Product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		Product.setFileName(request.getParameter("fileName"));
		Product.setProdName(request.getParameter("prodName"));
		Product.setProdDetail(request.getParameter("prodDetail"));
		Product.setManuDate(request.getParameter("manuDate"));
		Product.setPrice(Integer.parseInt(request.getParameter("price")));
		
		ProductServiceImpl impl = new ProductServiceImpl();
		impl.updateProduct(Product);
		
		request.setAttribute("Product", impl.getProduct(Product.getProdNo()));
		
		return "forward:/product/updateProduct.jsp";
	}

}
