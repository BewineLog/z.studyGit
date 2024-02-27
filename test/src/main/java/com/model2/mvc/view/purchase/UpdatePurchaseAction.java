package com.model2.mvc.view.purchase;

import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Purchase;

public class UpdatePurchaseAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PurchaseServiceImpl impl = new PurchaseServiceImpl();
		
		HttpSession session = request.getSession();
		Purchase purchase = (Purchase)session.getAttribute("purchase");
//		ProductVO productVO = new ProductVO();
		
		purchase.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setTranCode("1");
		
		
		request.setAttribute("purchase", purchase);
		request.setAttribute("tranNo", purchase.getTranNo());
		System.out.println("UpdatePurchaseAction :: " + purchase.toString());
		
		return "forward:/listProduct.do";
		
		
	}
}
