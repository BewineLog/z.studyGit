package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductRestController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value="json/addProduct", method=RequestMethod.POST)
	public void addProduct(@RequestBody Product product) throws Exception{
		System.out.println("/addProduct");
		
		productService.addProduct(product);
		
		
		//나중에 void로 바꿔야함.
//		return productService.getProduct(product.getProdNo());
	}

	@RequestMapping(value = "json/getProduct/{prodNo}/{menu}", method=RequestMethod.GET)
	public Product getProduct(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("prodNo") int prodNo,
			@PathVariable("menu") String menu) throws Exception{
		
		Product product = productService.getProduct(prodNo);
		
		if (menu.equals("search") ) {
			Cookie[] cookie = request.getCookies();

			boolean isCookie = false;
			for (int i = 0; i < cookie.length; i++) {
				if (cookie[i].getName().equals("history")) {
					System.out.println("Action for running");
					isCookie = true;
					System.out.println("Action Cookie before::" + cookie[i].getValue());
					cookie[i].setValue(cookie[i].getValue() + "and" + Integer.toString(prodNo));
					System.out.println("Action Cookie::" + cookie[i].getValue());
				
					cookie[i].setPath("/");
//					System.out.println(cookie[i].getPath().toString() + "::" + cookie[i].getDomain().toString() + "::" );
					
					response.addCookie(cookie[i]);
				}
			}
			
			System.out.println("isCookie::"+isCookie);

			if (!isCookie) {
//				System.out.println("Action for cookie setting");
				Cookie c = new Cookie("history", Integer.toString(prodNo));
				response.addCookie(c);
			}
		}
		
		
		
		return product;
		
	}
	
	@RequestMapping(value="json/listProduct", method=RequestMethod.GET)
	public Map listProductGET(
			@RequestParam(value="menu", required=false) String menu
			) throws Exception{
		
		
		SearchVO search = new SearchVO();
		
		if(search.getPage() ==0 ){
			search.setPage(1);
		}
		
		search.setPageUnit(pageUnit);
		search.setPageSize(pageSize);
		
		search.setPriceRange();
		
		
		System.out.println("listProduct search :::" +search.toString());

		int totalCount = productService.getTotalCount(search);
		Page pageInfo = new Page( search.getPage(), totalCount, pageUnit, pageSize);
		
		Map map = new HashMap();
		
		map.put("list", productService.getProductList(search));
		map.put("count", totalCount);
		map.put("menu", menu);
		map.put("pageInfo", pageInfo);
	
//		modelAndView.addObject("list",productService.getProductList(search));
//		modelAndView.addObject("count",totalCount);
//		modelAndView.addObject("menu",menu);
//		modelAndView.addObject("pageInfo", pageInfo);
		
//		if(menu == null) {
//			modelAndView.addObject("menu", session.getAttribute("menu"));
//		}else {
//			modelAndView.addObject("menu",menu);
//		}
		
//		modelAndView.setViewName("forward:/product/listProduct.jsp");
		return map;
	
	}
	
	@RequestMapping(value="json/listProduct", method=RequestMethod.POST)
	public Map listProduct(
			@RequestBody(required=false) SearchVO search,
			@RequestParam(value="menu", required=false) String menu,
			@RequestParam(value="isAutoComplete", required=false) String isAutoComplete
			) throws Exception{
		
		System.out.println("product List RestController");
		System.out.println("search::" + search.toString());
		System.out.println("isAutoComplete::" + isAutoComplete);
		Map map = new HashMap();
		
		if(search.getSearchKeyword() != null && search.getSearchKeyword() != "" &&  isAutoComplete != null && isAutoComplete.equals("true")) {
			map.put("autoCompleteList", productService.getAutocompleteList(search.getSearchKeyword()));
			return map;
			//
			// 나중에 searchKeyword 변수화 하기
			//
		}
		
		if(search.getPage() ==0 ){
			search.setPage(1);
		}
		
		search.setPageUnit(pageUnit);
		search.setPageSize(pageSize);
		
		search.setPriceRange();
		
		
		System.out.println("listProduct search :::" +search.toString());

		int totalCount = productService.getTotalCount(search);
		Page pageInfo = new Page( search.getPage(), totalCount, pageUnit, pageSize);
		
		
		
		map.put("list", productService.getProductList(search));
		map.put("count", totalCount);
		map.put("menu", menu);
		map.put("pageInfo", pageInfo);
	
//		modelAndView.addObject("list",productService.getProductList(search));
//		modelAndView.addObject("count",totalCount);
//		modelAndView.addObject("menu",menu);
//		modelAndView.addObject("pageInfo", pageInfo);
		
//		if(menu == null) {
//			modelAndView.addObject("menu", session.getAttribute("menu"));
//		}else {
//			modelAndView.addObject("menu",menu);
//		}
		
//		modelAndView.setViewName("forward:/product/listProduct.jsp");
		return map;
	
	}
	
	@RequestMapping(value = "json/updateProductView", method = RequestMethod.GET)
	public Product updateProductView(@RequestParam("prodNo") int prodNo ) throws Exception{
		
		
		return productService.getProduct(prodNo);
		
	}
	
	
	@RequestMapping(value="json/updateProduct",method=RequestMethod.POST)
	public Product updateProduct(@RequestBody Product product
									) throws Exception{
		
		productService.updateProduct(product);
		
		
		return productService.getProduct(product.getProdNo());
	}
	

}
