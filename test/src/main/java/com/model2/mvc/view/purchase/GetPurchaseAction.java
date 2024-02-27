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
		
		Purchase purchase = (Purchase)(impl.getPurchase(tranNo));
		
		if(purchase != null) {
			System.out.println("GetPurchaseAction Purchase not null");
			request.setAttribute("purchase", purchase);
			request.setAttribute("product", purchase.getPurchaseProd());
			request.setAttribute("user", purchase.getBuyer());
		}
		
		
		return "forward:/purchase/addPurchase.jsp?isGetPurchase=true";
	}
}
