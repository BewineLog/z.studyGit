package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

import java.util.Calendar;
import java.sql.Date;
import java.time.LocalDate;

public class AddPurchaseAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//구매정보 get
		PurchaseServiceImpl puImpl = new PurchaseServiceImpl();
		ProductServiceImpl pImpl = new ProductServiceImpl();
		UserServiceImpl uImpl = new UserServiceImpl();
		
		
		PurchaseVO purchaseVO = new PurchaseVO();
		ProductVO productVO = new ProductVO();
		UserVO userVO = new UserVO();
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String userId =request.getParameter("buyerId");
		
		
		
//		HttpSession session = request.getSession();
//		session.getAttribute("productVO");
//		
		
		productVO = (ProductVO)pImpl.getProduct(prodNo);
		userVO = (UserVO)uImpl.getUser(userId);
		
		purchaseVO.setBuyer(userVO);
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setOrderDate(Date.valueOf(LocalDate.now()));
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone").replaceAll("-", ""));
		purchaseVO.setTranCode("1");
		purchaseVO.setTranNo(0);
		
		System.out.println(request.getParameter("receiverAddr"));
		System.out.println(request.getParameter("receiverRequest"));
		System.out.println(request.getParameter("receiverDate"));
		System.out.println("AddPurchaseAction user:::" + purchaseVO.getBuyer().getUserId());
		System.out.println("AddPurchaseAction user:::" + purchaseVO.toString());
		puImpl.addPurchase(purchaseVO);
		
		
		//tranNo를 알 수 없음. DB 자동 세팅이라. 따라서 다시 get을 한번 해줘야
		//CURRVAL  의 경우 같은 세션에서 사용 가능 하기에 INSERT 후 그 증가된 값을 추출 할 수 있다.
		//출처: https://choija.tistory.com/61 [수캥이의 삶 :티스토리]
		
		// 이거 DB 한번 초기화 해줘야함. 안그럼 이전 데이터가 먼저 끌려나옴 
		
		System.out.println("addPurchaseAction tran_no::" + purchaseVO.getTranNo());
//		purchaseVO = puImpl.getPurchase(purchaseVO.getBuyer().getUserId(), purchaseVO.getPurchaseProd().getProdNo());
		
		request.setAttribute("tranNo", purchaseVO.getTranNo());
		request.setAttribute("purchaseVO", purchaseVO);
		request.setAttribute("productVO", purchaseVO.getPurchaseProd());
		request.setAttribute("userVO", purchaseVO.getBuyer());
		
//		HttpSession session = request.getSession();
//		session.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/addPurchase.jsp";
		
	}
}
