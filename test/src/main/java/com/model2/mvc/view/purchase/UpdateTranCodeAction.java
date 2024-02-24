package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

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
		PurchaseVO purchaseVO = new PurchaseVO();
		
		purchaseVO = (PurchaseVO)(impl.getPurchaseByTranNo(tranNo));
		
		if(purchaseVO != null) {
			System.out.println("UpdateTranCodeAction:: " + purchaseVO.toString() );
		}
		purchaseVO.setTranCode(tranCode);
		
		impl.updatePurchase(purchaseVO);
		
		return "forward:/listPurchase.do";
	}
}
