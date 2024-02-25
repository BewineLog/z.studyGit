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
		Purchase Purchase = new Purchase();
		
		Purchase = (Purchase)(impl.getPurchase(prodNo));
		
		if(Purchase != null) {
			System.out.println("UpdateTranCodeByProdAction:: " + Purchase.toString() );
		}
		Purchase.setTranCode(tranCode);
		
		impl.updatePurchase(Purchase);
		
		return "forward:/listProduct.do?menu=manage";
	}

}
