package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class GetPurchaseAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		PurchaseServiceImpl impl = new PurchaseServiceImpl();
		
		PurchaseVO purchaseVO = (PurchaseVO)(impl.getPurchase(tranNo));
		
		if(purchaseVO != null) {
			System.out.println("GetPurchaseAction purchaseVO not null");
			request.setAttribute("purchaseVO", purchaseVO);
			request.setAttribute("productVO", purchaseVO.getPurchaseProd());
			request.setAttribute("userVO", purchaseVO.getBuyer());
		}
		
		
		return "forward:/purchase/addPurchase.jsp?isGetPurchase=true";
	}
}
