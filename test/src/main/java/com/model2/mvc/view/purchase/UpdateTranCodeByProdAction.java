package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Purchase;

public class UpdateTranCodeByProdAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String tranCode = request.getParameter("tranCode").toString();
		
		System.out.println("UpdateTranCodeByProdAction :: " + prodNo + " " + tranCode);
		
		System.out.println("UpdateTranCodeByProdAction:: " + tranCode +"  "+ prodNo);
		
		PurchaseServiceImpl impl = new PurchaseServiceImpl();
		Purchase purchase = new Purchase();
		
		purchase = (Purchase)(impl.getPurchase(prodNo));
		
		if(purchase != null) {
			System.out.println("UpdateTranCodeByProdAction:: " + purchase.toString() );
		}
		purchase.setTranCode(tranCode);
		
		impl.updatePurchase(purchase);
		
		return "forward:/listProduct.do?menu=manage";
	}

}
