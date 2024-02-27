package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Purchase;

public class UpdatePurchaseViewAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//tranNo
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
//		HttpSession session = request.getSession();
//		Purchase Purchase = (Purchase)session.getAttribute("Purchase");
		PurchaseServiceImpl impl = new PurchaseServiceImpl();
		Purchase purchase = impl.getPurchase(prodNo);
		
		if(purchase != null) {
		
			request.setAttribute("purchase", purchase);
			System.out.println("updatePurchaseView :::" + purchase.toString());
		}
		return "forward:/purchase/updatePurchaseView.jsp";
		
		
	}

}
