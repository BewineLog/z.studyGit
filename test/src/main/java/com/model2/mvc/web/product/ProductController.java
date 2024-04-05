package com.model2.mvc.web.product;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping("/addProduct")
	public ModelAndView addProduct(@ModelAttribute("product") Product product, HttpServletRequest request) throws Exception{
		System.out.println("/addProduct");
		
		//
		//
		//
		List<MultipartFile> files = product.getFile();
		int idx = 0;
		
		if (product.getFile() != null) {
			for (MultipartFile file : files) {
				String fileName = file.getOriginalFilename();

				if (product.getFileName() == null || product.getFileName().equals("null")) {
					System.out.println("pass"); // 안돌면 지워도 됨
					continue;
				}

				long size = file.getSize();

				System.out.println("fileName & size :: " + product.getFileName() + " " + size);

				String fileExtension = fileName.substring(fileName.indexOf('.'), fileName.length()); // 여기서 터짐

				System.out.println("file Extension: " + fileExtension);

				System.out.println("path::" + request.getServletContext().getRealPath("/images/uploadFiles/"));

				String uploadFolder = request.getServletContext().getRealPath("/images/uploadFiles/"); // 동작원리 알아야할

				System.out.println("uploadPath: " + uploadFolder + product.getFileNames(idx) + fileExtension);

				File saveFile = new File(uploadFolder + product.getFileNames(idx) + fileExtension);

				product.setFileName(product.getFileNames(idx) + fileExtension);

				try {
					file.transferTo(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				idx++;
			}
		}

		//
		//
		//
		
		System.out.println("final fileName:" + product.getFileName());
		
		productService.addProduct(product);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/product/addProduct.jsp?menu=manage");
		modelAndView.addObject("product", product);
		
		return modelAndView;
	}

	@RequestMapping("/getProduct")
	public ModelAndView getProduct(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("prodNo") int prodNo,
			@RequestParam("menu") String menu) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		
		Product product = productService.getProduct(prodNo);
		
		String fileName = product.getFileName();
		
		if(fileName != null && fileName.contains(",")) {
			product.setFileNames(Arrays.asList(product.getFileName().split(",")));
		}else {
			product.setFileNames(Arrays.asList(product.getFileName()));
		}
		
		modelAndView.addObject("product", product);
		
		if(menu.equals("manage")){
			modelAndView.setViewName("forward:/product/updateProductView.jsp?menu=" + menu);
		}else {
			modelAndView.setViewName("forward:/product/getProduct.jsp?menu=" + menu);
		}
		
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
		
		
		
		return modelAndView;
		
	}
	
	@RequestMapping("/listProduct")
	public ModelAndView listProduct(
			@ModelAttribute(value="search") SearchVO search,
			@RequestParam(value="menu", required=false) String menu,
			Model model,
			HttpSession session
			) throws Exception{
		
		if(search.getPage() ==0 ){
			search.setPage(1);
		}
		
		search.setPageUnit(pageUnit);
		search.setPageSize(pageSize);
		
		search.setPriceRange();
		
		
		System.out.println("listProduct search :::" +search.toString());
		
		ModelAndView modelAndView = new ModelAndView();
		int totalCount = productService.getTotalCount(search);
		Page pageInfo = new Page( search.getPage(), totalCount, pageUnit, pageSize);
	
		modelAndView.addObject("list",productService.getProductList(search));
		modelAndView.addObject("count",totalCount);
		modelAndView.addObject("menu",menu);
		modelAndView.addObject("pageInfo", pageInfo);
		
		if(menu == null) {
			modelAndView.addObject("menu", session.getAttribute("menu"));
		}else {
			modelAndView.addObject("menu",menu);
		}
		
		modelAndView.setViewName("forward:/product/listProduct.jsp");
		return modelAndView;
	
	}
	
	@RequestMapping("/updateProductView")
	public ModelAndView updateProductView(@ModelAttribute("product") Product product) throws Exception{
		productService.updateProduct(product);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("forward:/product/updateProduct.jsp");
		modelAndView.addObject("product", productService.getProduct(product.getProdNo()));
		
		
		return modelAndView;
	}
	
	
	@RequestMapping("/updateProduct")
	public ModelAndView updateProduct(@RequestParam("prodNo") int prodNo
									) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(productService.getProduct(prodNo));
		modelAndView.setViewName("forward:/product/listProduct");
		
		return modelAndView;
	}
	
	

}
