package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdatePurchaseViewAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//tranNo
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
//		HttpSession session = request.getSession();
//		PurchaseVO purchaseVO = (PurchaseVO)session.getAttribute("purchaseVO");
		PurchaseServiceImpl impl = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = impl.getPurchase(prodNo);
		
		if(purchaseVO != null) {
		
			request.setAttribute("purchaseVO", purchaseVO);
			System.out.println("updatePurchaseView :::" + purchaseVO.toString());
		}
		return "forward:/purchase/updatePurchaseView.jsp";
		
		
	}

}
