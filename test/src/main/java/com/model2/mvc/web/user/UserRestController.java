package com.model2.mvc.web.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> ȸ������ RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method ���� ����
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
		
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="json/addUser", method=RequestMethod.GET)
	public User addUserGet( @ModelAttribute User user ) throws Exception {

		System.out.println("/addUser");
		//Business Logic
		userService.addUser(user);
		
		//나중에 void로 바꿔야함.
		return userService.getUser(user.getUserId());
	}
	
	@RequestMapping(value="json/addUser", method=RequestMethod.POST)
	public User addUser( @RequestBody User user ) throws Exception {

		System.out.println("/addUser");
		//Business Logic
		userService.addUser(user);
		
		//나중에 void로 바꿔야함.
		return userService.getUser(user.getUserId());
	}
	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUserGet( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		return userService.getUser(userId);
	}
	
	@RequestMapping( value="json/getUser", method=RequestMethod.POST )
	public User getUser( @RequestBody User user ) throws Exception{
		
		System.out.println("/user/json/getUser : POST");
		System.out.println("::" + user);
		
		//Business Logic
		return userService.getUser(user.getUserId());
	}

	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user, 
									HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		
		System.out.println("dbuser::" + dbUser);
		
		return dbUser;
	}
	
	@RequestMapping(value="json/listUser", method=RequestMethod.POST)
	//, @RequestBody(required = false) String removeUserId
	public Map listUser( @RequestBody(required=false) SearchVO search) throws Exception{
		
		System.out.println("/listUser");
		
		if(search.getPage() ==0 ){
			search.setPage(1);
		}
		search.setPageSize(pageSize);
		search.setPriceRange();
		
//		if(removeUserId != null) {
//			System.out.println("listUser removeUserId:" +removeUserId);
//			userService.removeUser(removeUserId);
//		}
		
		Page pageInfo = new Page( search.getPage(), userService.getTotalCount(), pageUnit, pageSize);
		// Business logic ����
		Map map = new HashMap();
		map.put("list", userService.getUserList(search));
		map.put("pageInfo", pageInfo);
		map.put("search", search);
		
		return map;
		
		
	}
	
	@RequestMapping(value="json/updateUser", method=RequestMethod.POST)
	public User updateUser(@RequestBody User user) throws Exception{

		System.out.println("/updateUser");
		//Business Logic
		userService.updateUser(user);
		
		return userService.getUser(user.getUserId());
	}
	
	@RequestMapping(value="json/checkDuplication", method=RequestMethod.POST)
	public boolean checkDuplication( @RequestBody User user ) throws Exception{
		
		System.out.println("/checkDuplication");
		//Business Logic
		System.out.println("::" + user);
		
		System.out.println("::"+user.getUserId());
		boolean result=userService.checkDuplication(user.getUserId());
		

		return result;
	}
}