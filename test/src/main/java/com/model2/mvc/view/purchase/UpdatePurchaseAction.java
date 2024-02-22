package com.model2.mvc.view.purchase;

import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdatePurchaseAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PurchaseServiceImpl impl = new PurchaseServiceImpl();
		
		HttpSession session = request.getSession();
		PurchaseVO purchaseVO = (PurchaseVO)session.getAttribute("purchaseVO");
//		ProductVO productVO = new ProductVO();
		
		purchaseVO.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setTranCode("1");
		
		
		request.setAttribute("purchaseVO", purchaseVO);
		request.setAttribute("tranNo", purchaseVO.getTranNo());
		System.out.println("UpdatePurchaseAction :: " + purchaseVO.toString());
		
		return "forward:/listProduct.do";
		
		
	}
}
