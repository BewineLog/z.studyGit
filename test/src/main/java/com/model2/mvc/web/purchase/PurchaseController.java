package com.model2.mvc.web.purchase;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;



@Controller
@RequestMapping("/purchase/*")
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
	
	@RequestMapping(value="addPurchase", method=RequestMethod.GET)
	public String addPurchase(
			@RequestParam("prodNo") int prodNo,
			HttpSession session,
			Model model
			) throws Exception{
		
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("product", productService.getProduct(prodNo));
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	@RequestMapping(value="addPurchase", method=RequestMethod.POST)
	public String addPurchase(
			@ModelAttribute("purchase") Purchase purchase,
			@RequestParam("prodNo") int prodNo,
			@RequestParam("buyerId") String buyerId,
			Model model
			) throws Exception{
		
		//주소 요청사항 희망일자 안나옴 
		
		purchase.setBuyer(userService.getUser(buyerId));
		purchase.setPurchaseProd(productService.getProduct(prodNo));
		
		purchase.setTranCode("1");
		
		
		purchaseService.addPurchase(purchase);
		
		
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/addPurchase.jsp";
	}
	
	@RequestMapping("getPurchase")
	public String getPurchase(
			@RequestParam("tranNo") int tranNo,
			Model model
			) throws Exception{
		
		Purchase purchase = purchaseService.getPurchaseByTranNo(tranNo);
//		purchase.setBuyer(userService.getUser(purchase.getBuyer().getUserId()));
//		purchase.setPurchaseProd(productService.getProduct(purchase.getPurchaseProd().getProdNo()));
		
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/addPurchase.jsp?isGetPurchase=true";
		
	}
	
	@RequestMapping("listPurchase")
	public String listPurchase(@ModelAttribute("search") SearchVO search, HttpSession session, Model model  ) throws Exception{
		
		User user = (User) session.getAttribute("user");
		int count = purchaseService.getTotalCount(user.getUserId());
		
		if(search.getPage() ==0 ){
			search.setPage(1);
		}
		search.setPageUnit(pageUnit);
		search.setPageSize(pageSize);
		
		Page pageInfo = new Page(search.getPage(),count,pageUnit,pageSize);
		
		List<Object> list = purchaseService.getPurchaseList(user.getUserId(), pageInfo);
		
		//User 어떻게 담을지 고민해보자 
		
//		model.addAttribute("userName",user.getUserName());
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pageInfo",pageInfo);
		
		return "forward:/purchase/listPurchase.jsp";
	}
	
	@RequestMapping("updatePurchaseView")
	public String updatePurchaseView(
			@RequestParam("tranNo") int tranNo,
			Model model
			) throws Exception{
		
		model.addAttribute("purchase", purchaseService.getPurchase(tranNo));
		return "forward:/purchase/updatePurchaseView.jsp";
	}
	
	
	
	@RequestMapping("updatePurchase")
	public String updatePurchase(
			@RequestParam("tranNo") int tranNo,
			@RequestParam("prodNo") int prodNo,
			@ModelAttribute("purchase") Purchase purchase,
			Model model
			) throws Exception{
		
		purchase.setTranNo(tranNo);
		purchase.getPurchaseProd().setProdNo(prodNo);
		
		purchaseService.updatePurchase(purchase);
		
		return "forward:/purchase/listPurchase";
	}
	
	
	//page 변수 넘어오는데 어떻게 처리할까 ...
	@RequestMapping("updateTranCodeByProd")
	public String updateTranCodeByProd(
			@RequestParam("prodNo") int prodNo,
			@RequestParam("tranCode") String tranCode,
			@RequestParam(value="menu", required=false) String menu,
			Model model
			) throws Exception{
		Purchase purchase = purchaseService.getPurchase(prodNo);
		purchase.setTranCode(tranCode);
		purchaseService.updatePurchase(purchase);
		
		
		if (menu != null && menu.equals("manage")) {
			model.addAttribute("menu", "manage");
			return "forward:/product/listProduct";  //? 왜 애만 menu가 필요하지..?
		}else {
			return "forward:/purchase/listPurchase";
		}
	}
	
	@RequestMapping("updateTranCode")
	public String updateTranCode(
			@RequestParam("tranNo") int tranNo,
			@RequestParam("tranCode") String tranCode,
			@RequestParam(value="menu", required=false) String menu,
			Model model
			) throws Exception{
		Purchase purchase = purchaseService.getPurchaseByTranNo(tranNo);
		purchase.setTranCode(tranCode);
		purchaseService.updatePurchase(purchase);
		
		
		if (menu != null && menu.equals("manage")) {
			model.addAttribute("menu", "manage");
			return "forward:/product/listProduct";  //? 왜 애만 menu가 필요하지..?
		}else {
			return "forward:/purchase/listPurchase";
		}
	}
	

}
