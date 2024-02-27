package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Purchase;

public class UpdateTranCodeAction extends Action {

	public UpdateTranCodeAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		String tranCode = request.getParameter("tranCode").toString();
		
		System.out.println("UpdateTranCodeAction :: " + tranNo + " " + tranCode);
		
		
		PurchaseServiceImpl impl = new PurchaseServiceImpl();
		Purchase purchase = new Purchase();
		
		purchase = (Purchase)(impl.getPurchaseByTranNo(tranNo));
		
		if(purchase != null) {
			System.out.println("UpdateTranCodeAction:: " + purchase.toString() );
		}
		purchase.setTranCode(tranCode);
		
		impl.updatePurchase(purchase);
		
		return "forward:/listPurchase.do";
	}
}
