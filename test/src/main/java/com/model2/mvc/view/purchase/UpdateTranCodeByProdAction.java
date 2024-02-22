package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeByProdAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String tranCode = request.getParameter("tranCode").toString();
		
		System.out.println("UpdateTranCodeByProdAction:: " + tranCode +"  "+ prodNo);
		
		PurchaseServiceImpl impl = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = new PurchaseVO();
		
		purchaseVO = (PurchaseVO)(impl.getPurchase(prodNo,tranCode));
		
		if(purchaseVO != null) {
			System.out.println("UpdateTranCodeByProdAction:: " + purchaseVO.toString() );
		}
		purchaseVO.setTranCode("2");
		
		impl.updatePurchase(purchaseVO);
		
		return "forward:/listProduct.do?menu=manage";
	}

}
