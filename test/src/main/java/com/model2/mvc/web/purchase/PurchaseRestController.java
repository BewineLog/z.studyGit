package com.model2.mvc.web.purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;



@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {
	
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

	public PurchaseRestController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="json/addPurchase/{prodNo}/{userId}", method=RequestMethod.GET)
	public Purchase addPurchaseGET(
			@PathVariable int prodNo,
			@PathVariable String userId, //원래대로 application이라면 로그인 되어 있다는 전제가 깔려 있어야함 // 다만 테스트 이기 때문에 전송 받는 걸로
			HttpSession session
			) throws Exception{
		
		Purchase purchase = new Purchase();
		
		purchase.setPurchaseProd(productService.getProduct(prodNo));
		purchase.setBuyer(userService.getUser(userId));
		
		
		System.out.println("::"+purchase.toString());
		return purchase;
	}
	
	@RequestMapping(value="json/addPurchase/{prodNo}/{buyerId}", method=RequestMethod.POST)
	public Purchase addPurchase(
			@RequestBody Purchase purchase,
			@PathVariable("prodNo") int prodNo,
			@PathVariable("buyerId") String buyerId,
			Model model
			) throws Exception{
		
		System.out.println("add Purchase POST:" + purchase.toString());
		System.out.println("add Purchase POST:" + prodNo);
		System.out.println("add Purchase POST:" + buyerId);
		
		purchase.setBuyer(userService.getUser(buyerId));
		purchase.setPurchaseProd(productService.getProduct(prodNo));
		
		purchase.setTranCode("1");
		
		System.out.println("addPurchase POST:" + purchase.toString());
		
		purchaseService.addPurchase(purchase);
		
		
		
		return purchase;
	}
	
	@RequestMapping(value="json/getPurchase/{tranNo}", method=RequestMethod.GET)
	public Purchase getPurchaseGET(
			@PathVariable int tranNo
			) throws Exception{
		
		Purchase purchase = purchaseService.getPurchaseByTranNo(tranNo);
		
		return purchase;
		
	}
	
	@RequestMapping(value="json/getPurchase", method=RequestMethod.POST)
	public Purchase getPurchase(
			@RequestBody Purchase purchaseIn
			) throws Exception{
		
		Purchase purchase = purchaseService.getPurchaseByTranNo(purchaseIn.getTranNo());
		
		return purchase;
		
	}
	
	
	@RequestMapping(value="json/listPurchase/{userId}", method=RequestMethod.GET)
	public JSONObject listPurchaseGET(
			@PathVariable String userId,//원래대로 application이라면 로그인 되어 있다는 전제가 깔려 있어야함 // 다만 테스트 이기 때문에 전송 받는 걸로
			HttpSession session) throws Exception{
		
		SearchVO search = new SearchVO();
//		User user = (User) session.getAttribute("user");
//		int count = purchaseService.getTotalCount(user.getUserId());
		int count = purchaseService.getTotalCount(userId);
		
		if(search.getPage() ==0 ){
			search.setPage(1);
		}
		search.setPageUnit(pageUnit);
		search.setPageSize(pageSize);
		
		Page pageInfo = new Page(search.getPage(),count,pageUnit,pageSize);
		
//		List<Object> list = purchaseService.getPurchaseList(user.getUserId(), pageInfo);
		List<Object> list = purchaseService.getPurchaseList(userId,pageInfo);
		
		
//		model.addAttribute("list", list);
//		model.addAttribute("count", count);
//		model.addAttribute("pageInfo",pageInfo);
		
		
//		Map map = new HashMap();
//		map.put("list",list);
//		map.put("count", count);
//		map.put("pageInfo", pageInfo);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("list", list);
		jsonObj.put("count", count);
		jsonObj.put("pageInfo", pageInfo);
		
		
		return jsonObj;
	}
	
	@RequestMapping(value="json/listPurchase/{userId}", method=RequestMethod.POST)
	public JSONObject listPurchase(@RequestBody SearchVO search, 
			@PathVariable String userId,//원래대로 application이라면 로그인 되어 있다는 전제가 깔려 있어야함 // 다만 테스트 이기 때문에 전송 받는 걸로
			HttpSession session ) throws Exception{
		
//		User user = (User) session.getAttribute("user");
//		int count = purchaseService.getTotalCount(user.getUserId());
		int count = purchaseService.getTotalCount(userId);
		if(search.getPage() ==0 ){
			search.setPage(1);
		}
		search.setPageUnit(pageUnit);
		search.setPageSize(pageSize);
		
		Page pageInfo = new Page(search.getPage(),count,pageUnit,pageSize);
		
//		List<Object> list = purchaseService.getPurchaseList(user.getUserId(), pageInfo);
		List<Object> list = purchaseService.getPurchaseList(userId,pageInfo);
		
//		model.addAttribute("list", list);
//		model.addAttribute("count", count);
//		model.addAttribute("pageInfo",pageInfo);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("list", list);
		jsonObj.put("count", count);
		jsonObj.put("pageInfo", pageInfo);
		
		
		return jsonObj;
	}
	
//	@RequestMapping(value="json/updatePurchaseView/{tranNo}", method=RequestMethod.POST)
	@RequestMapping(value="json/updatePurchaseView", method=RequestMethod.POST)
	public Purchase updatePurchaseView(
//			@PathVariable int tranNo,
			@RequestBody Purchase purchase,
			Model model
			) throws Exception{
		
		return purchaseService.getPurchaseByTranNo(purchase.getTranNo());
	}
	
//	@RequestMapping(value="json/updatePurchase/{tranNo}/{prodNo}", method=RequestMethod.POST)
	@RequestMapping(value="json/updatePurchase", method=RequestMethod.POST)
	public JSONObject updatePurchase(
//			@PathVariable("tranNo") int tranNo,
//			@PathVariable("prodNo") int prodNo,
			
			@RequestBody Purchase purchase
			) throws Exception{
		
		
		System.out.println("::"+purchase.toString());
//		purchaseService.updatePurchase(purchase);
		
		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("purchase", purchase);
		
		return jsonObject;
	}
	
	
	//
	//prodNo는 이제 안쓸듯?
	//
	@RequestMapping(value="json/updateTranCodeByProdNo/{prodNo}/{tranCode}/{menu}", method=RequestMethod.GET)
	public Purchase updateTranCodeByProd(
			@PathVariable("prodNo") int prodNo,
			@PathVariable("tranCode") String tranCode,
			@PathVariable(value="menu", required=false) String menu
			) throws Exception{
		System.out.println(prodNo + "::" + tranCode + "::" + menu);
		Purchase purchase = purchaseService.getPurchase(prodNo);
		purchase.setTranCode(tranCode);
		purchaseService.updatePurchase(purchase);
		
		return purchaseService.getPurchaseByTranNo(purchase.getTranNo());
		
	}
	
	@RequestMapping(value="json/updateTranCode/{tranNo}/{tranCode}/{menu}",method=RequestMethod.GET)
	public Purchase updateTranCode(
			@PathVariable("tranNo") int tranNo,
			@PathVariable("tranCode") String tranCode,
			@PathVariable(value="menu", required=false) String menu
			) throws Exception{
		Purchase purchase = purchaseService.getPurchaseByTranNo(tranNo);
		purchase.setTranCode(tranCode);
		purchaseService.updatePurchase(purchase);
		
		return purchaseService.getPurchaseByTranNo(tranNo);
		
	}
	

}
