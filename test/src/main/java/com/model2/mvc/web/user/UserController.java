package com.model2.mvc.web.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> ȸ������ Controller
@Controller
@RequestMapping("/user/*")
public class UserController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method ���� ����
		
	public UserController(){
		System.out.println(this.getClass());
	}
	
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	// is need?
	
//	@RequestMapping("/addUserView")
	@RequestMapping(value="addUser", method=RequestMethod.GET)
	public String addUser() throws Exception {

		System.out.println("/addUserView");
		
		return "redirect:/user/addUserView.jsp";
	}
	
//	@RequestMapping("/addUser")
	@RequestMapping(value="addUser", method=RequestMethod.POST)
	public String addUser( @ModelAttribute("user") User user ) throws Exception {

		System.out.println("/addUser");
		//Business Logic
		userService.addUser(user);
		
		return "redirect:/user/loginView.jsp";
	}
	
//	@RequestMapping("/getUser")
	@RequestMapping(value="getUser", method=RequestMethod.GET)
	public String getUser( @RequestParam("userId") String userId , Model model ) throws Exception {
		
		System.out.println("/getUser");
		//Business Logic
		User user = userService.getUser(userId);
		// Model �� View ����
		model.addAttribute("user", user);
		
		return "forward:/user/getUser.jsp";
	}
	
//	@RequestMapping("/updateUserView")
	@RequestMapping(value="updateUser", method=RequestMethod.GET)
	public String updateUser( @RequestParam("userId") String userId , Model model ) throws Exception{

		System.out.println("/updateUserView");
		//Business Logic
		User user = userService.getUser(userId);
		// Model �� View ����
		model.addAttribute("user", user);
		
		return "forward:/user/updateUser.jsp";
	}
	
//	@RequestMapping("/updateUser")
	@RequestMapping(value="updateUser", method=RequestMethod.POST)
	public String updateUser( @ModelAttribute("user") User user , Model model , HttpSession session) throws Exception{

		System.out.println("/updateUser");
		//Business Logic
		userService.updateUser(user);
		
		String sessionId=((User)session.getAttribute("user")).getUserId();
		if(sessionId.equals(user.getUserId())){
			session.setAttribute("user", user);
		}
		
		return "forward:/user/getUser?userId="+user.getUserId() + "&isUpdate=clear";
	}
	
//	@RequestMapping("/loginView")
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() throws Exception{
		
		System.out.println("/loginView");

		return "redirect:/user/loginView.jsp";
	}
	
//	@RequestMapping("/login")
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(@ModelAttribute("user") User user , HttpSession session ) throws Exception{
		
		System.out.println("/login");
		//Business Logic
		User dbUser=userService.loginUser(user);
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
			
			if(user.getRole() != null && user.getRole().equals("admin")) {
				session.setAttribute("menu", "manage");
			}else {
				session.setAttribute("menu", "search");
			}
		}
		
		return "redirect:/index.jsp";
	}
	
//	@RequestMapping("/logout")
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session ) throws Exception{
		
		System.out.println("/logout");
		
		session.invalidate();
		
		return "redirect:/index.jsp";
	}
	
//	@RequestMapping("/checkDuplication")
	@RequestMapping(value="checkDuplication", method=RequestMethod.POST)
	public String checkDuplication( @RequestParam("userId") String userId , Model model ) throws Exception{
		
		System.out.println("/checkDuplication");
		//Business Logic
		boolean result=userService.checkDuplication(userId);
		// Model �� View ����
		model.addAttribute("result", new Boolean(result));
		model.addAttribute("userId", userId);

		return "forward:/user/checkDuplication.jsp";
	}
	
//	@RequestMapping("/listUser")
	@RequestMapping(value="listUser")
	public String listUser( @ModelAttribute("search") SearchVO search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/listUser");
		
		if(search.getPage() ==0 ){
			search.setPage(1);
		}
		search.setPageSize(pageSize);
		
		if(request.getParameter("removeUserId") != null) {
			System.out.println("listUser removeUserId:" + request.getParameter("removeUserId").toString());
			userService.removeUser(request.getParameter("removeUserId"));
		}
		
		// Business logic ����
		List<Object> map=userService.getUserList(search);
		
		Page pageInfo = new Page( search.getPage(), userService.getTotalCount(), pageUnit, pageSize);
		System.out.println(pageInfo);
		
		
		
		// Model �� View ����
		model.addAttribute("list", map);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("search", search);
		
		return "forward:/user/listUser.jsp";
	}
}