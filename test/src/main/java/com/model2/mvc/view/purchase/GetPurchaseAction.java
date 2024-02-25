package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Purchase;

public class GetPurchaseAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		PurchaseServiceImpl impl = new PurchaseServiceImpl();
		
		Purchase Purchase = (Purchase)(impl.getPurchase(tranNo));
		
		if(Purchase != null) {
			System.out.println("GetPurchaseAction Purchase not null");
			request.setAttribute("Purchase", Purchase);
			request.setAttribute("productVO", Purchase.getPurchaseProd());
			request.setAttribute("userVO", Purchase.getBuyer());
		}
		
		
		return "forward:/purchase/addPurchase.jsp?isGetPurchase=true";
	}
}
