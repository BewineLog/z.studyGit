package com.model2.mvc.web.purchase;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;



@Controller
public class PurchaseController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	public PurchaseController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/addPurchaseView.do")
	public String addPurchaseView(
			@RequestParam("prodNo") int prodNo,
			HttpSession session,
			Model model
			) throws Exception{
		
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("product", productService.getProduct(prodNo));
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	@RequestMapping("/addPurchase.do")
	public String addPurchase(
			@ModelAttribute("purchase") Purchase purchase,
			@RequestParam("prodNo") int prodNo,
			@RequestParam("buyerId") String buyerId,
			Model model
			) throws Exception{
		
		purchase.setBuyer(userService.getUser(buyerId));
		purchase.setPurchaseProd(productService.getProduct(prodNo));
		
		purchase.setTranCode("1");
		
		
		purchaseService.addPurchase(purchase);
		
		
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/addPurchase.jsp";
	}
	
	@RequestMapping("/getPurchase.do")
	public String getPurchase(
			@RequestParam("tranNo") int tranNo,
			Model model
			) throws Exception{
		
		Purchase purchase = purchaseService.getPurchaseByTranNo(tranNo);
		purchase.setBuyer(userService.getUser(purchase.getBuyer().getUserId()));
		purchase.setPurchaseProd(productService.getProduct(purchase.getPurchaseProd().getProdNo()));
		
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/addPurchase.jsp?isGetPurchase=true";
		
	}
	
	@RequestMapping("/listPurchase.do")
	public String listPurchase(@ModelAttribute("search") SearchVO search, HttpSession session, Model model  ) throws Exception{
		
		User user = (User) session.getAttribute("user");
		int count = purchaseService.getTotalCount(user.getUserId());
		if(search.getPage() == 0) {
			search.setPage(1);
		}
		
		Page pageInfo = new Page(search.getPage(),count,pageUnit,pageSize);
		
		model.addAttribute("count", count);
		model.addAttribute("pageInfo",pageInfo);
		
		return "";
	}
	

}
