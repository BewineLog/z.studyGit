package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.model2.mvc.common.util.SpringUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

public class AddProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//request
		Product productVO = new Product();
		
		productVO.setFileName(request.getParameter("fileName"));
		productVO.setManuDate(request.getParameter("manuDate").replaceAll("-",""));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setProdName(request.getParameter("prodName"));
		
//		ProductServiceImpl impl = new ProductServiceImpl();
		
		ProductService impl = (ProductService)SpringUtil.getProductService();
		impl.addProduct(productVO);
		
		request.setAttribute("productVO", productVO);
		
		return "forward:/product/addProduct.jsp?menu=manage";
	}
}
