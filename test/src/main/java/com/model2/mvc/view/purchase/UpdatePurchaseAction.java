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
		Purchase Purchase = (Purchase)session.getAttribute("Purchase");
//		ProductVO productVO = new ProductVO();
		
		Purchase.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		Purchase.setDivyAddr(request.getParameter("receiverAddr"));
		Purchase.setDivyDate(request.getParameter("receiverDate"));
		Purchase.setDivyRequest(request.getParameter("receiverRequest"));
		Purchase.setPaymentOption(request.getParameter("paymentOption"));
		Purchase.setReceiverName(request.getParameter("receiverName"));
		Purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		Purchase.setTranCode("1");
		
		
		request.setAttribute("Purchase", Purchase);
		request.setAttribute("tranNo", Purchase.getTranNo());
		System.out.println("UpdatePurchaseAction :: " + Purchase.toString());
		
		return "forward:/listProduct.do";
		
		
	}
}
