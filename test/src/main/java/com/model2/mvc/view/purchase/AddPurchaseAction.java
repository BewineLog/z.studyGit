package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.User;

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
		
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user = new User();
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String userId =request.getParameter("buyerId");
		
		
		
//		HttpSession session = request.getSession();
//		session.getAttribute("Product");
//		
		
		product = (Product)pImpl.getProduct(prodNo);
		user = (User)uImpl.getUser(userId);
		
		purchase.setBuyer(user);
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setOrderDate(Date.valueOf(LocalDate.now()));
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setPurchaseProd(product);
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone").replaceAll("-", ""));
		purchase.setTranCode("1");
		purchase.setTranNo(0);
		
		System.out.println(request.getParameter("receiverAddr"));
		System.out.println(request.getParameter("receiverRequest"));
		System.out.println(request.getParameter("receiverDate"));
		System.out.println("AddPurchaseAction user:::" + purchase.getBuyer().getUserId());
		System.out.println("AddPurchaseAction user:::" + purchase.toString());
		puImpl.addPurchase(purchase);
		
		
		//tranNo를 알 수 없음. DB 자동 세팅이라. 따라서 다시 get을 한번 해줘야
		//CURRVAL  의 경우 같은 세션에서 사용 가능 하기에 INSERT 후 그 증가된 값을 추출 할 수 있다.
		//출처: https://choija.tistory.com/61 [수캥이의 삶 :티스토리]
		
		// 이거 DB 한번 초기화 해줘야함. 안그럼 이전 데이터가 먼저 끌려나옴 
		
		System.out.println("addPurchaseAction tran_no::" + purchase.getTranNo());
//		Purchase = puImpl.getPurchase(Purchase.getBuyer().getUserId(), Purchase.getPurchaseProd().getProdNo());
		
		request.setAttribute("tranNo", purchase.getTranNo());
		request.setAttribute("purchase", purchase);
		request.setAttribute("product", purchase.getPurchaseProd());
		request.setAttribute("user", purchase.getBuyer());
		
//		HttpSession session = request.getSession();
//		session.setAttribute("Purchase", Purchase);
		
		return "forward:/purchase/addPurchase.jsp";
		
	}
}
