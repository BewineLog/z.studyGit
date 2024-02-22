package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class UpdateProductViewAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ProductVO productVO = new ProductVO();
		
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		productVO.setFileName(request.getParameter("fileName"));
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		
		ProductServiceImpl impl = new ProductServiceImpl();
		impl.updateProduct(productVO);
		
		request.setAttribute("productVO", impl.getProduct(productVO.getProdNo()));
		
		return "forward:/product/updateProduct.jsp";
	}

}
