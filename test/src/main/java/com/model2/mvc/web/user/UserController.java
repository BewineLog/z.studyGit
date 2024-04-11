package com.model2.mvc.web.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.KakaoUser;
import com.model2.mvc.service.domain.OAuthToken;
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
	
	
	public ResponseEntity<String> getKakaoToken(String code) {
		System.out.println("getKakaoToken");
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "8029a1f4dc6d130e053ccca76946eb2e");
		params.add("redirect_uri", "http://192.168.0.16:8080/user/kakaoLogin");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// POST 방식으로 Http 요청한다. 그리고 response 변수의 응답 받는다.
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token", // https://{요청할 서버 주소}
				HttpMethod.POST, // 요청할 방식
				kakaoTokenRequest, // 요청할 때 보낼 데이터
				String.class // 요청 시 반환되는 데이터 타입
		);
		
		System.out.println("response:" + response);	
		
		return response;
		
		
	}
	
	public OAuthToken getTokenToVO(ResponseEntity<String> response) throws Exception{
		OAuthToken token = new OAuthToken();
		
		
		ObjectMapper objMapper = new ObjectMapper();
		
		token = objMapper.readValue(response.getBody(), OAuthToken.class);
		
		System.out.println("getTokenToVo:" + token.toString());
		return token;
	}
	
	public KakaoUser getTokenToKakaoVO(ResponseEntity<String> response) throws Exception{
		KakaoUser kakaoUser = new KakaoUser();
		
		ObjectMapper objMapper = new ObjectMapper();
		
		kakaoUser = objMapper.readValue(response.getBody(), KakaoUser.class);
		
		return kakaoUser;
	}
	
	public ResponseEntity<String> getKakaoUserData(OAuthToken token) {
		
		//
		//no body error
		//
		
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "Content-type: application/x-www-form-urlencoded;charset=utf-8");
		headers.add("Authorization", "Bearer " + token.getAccess_token());
		
		HttpEntity<String> kakaoProfileRequest= new HttpEntity<>(headers);
		
		ResponseEntity<String> response = rt.exchange(
			"https://kapi.kakao.com/v2/user/me",
			HttpMethod.GET,
			kakaoProfileRequest,
			String.class
			);

		System.out.println("response:" + response);
		return response;
		
		
	}
	
	public User setKakaoUserVO(KakaoUser kakaoUser) {
		User user = new User();
		
		user.setUserId(kakaoUser.getId());
		user.setUserName(kakaoUser.getId());
		user.setPassword("1234");// 아직 비밀번호를 카카오에서 못받아옴. 따라서 일단 디폴트로 1234 해놓고 나중에 변경
		//
		//기타 정보들도 나중에 넘어오게 되면 설
		//
		
		user.setIsKakao("1"); // 1 == true, 2== false
		
		System.out.println("kakao ==> user :" + user.toString());
		
		return user;
	}
	
//	@RequestMapping("/addUserView")
	@RequestMapping(value="addUser", method=RequestMethod.GET)
	public String addUser() throws Exception {

		System.out.println("/addUserView");
		
		return "redirect:/user/addUserView.jsp";
	}
	
//	@RequestMapping("/addUser")
	@RequestMapping(value="addUser", method=RequestMethod.POST)
	public String addUser( @ModelAttribute("user") User user, @RequestBody String authCode ) throws Exception {

		System.out.println("/addUser");
		//Business Logic
		
		if(authCode.equals("false")) {
			System.out.println("not updated");//나중에 로직 견고히 해야함 .
		}else {
			userService.addUser(user);
		}
		
		return "redirect:/user/loginView.jsp";
	}
	
//	@RequestMapping("/getUser")
	@RequestMapping(value="getUser", method=RequestMethod.GET)
	public String getUserGET( @RequestParam("userId") String userId , Model model ) throws Exception {
		
		System.out.println("/getUser");
		//Business Logic
		User user = userService.getUser(userId);
		// Model �� View ����
		model.addAttribute("user", user);
		
		return "forward:/user/getUser.jsp";
	}
	
	@RequestMapping(value="getUser", method=RequestMethod.POST)
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
			System.out.println("Session user set");
			session.setAttribute("user", dbUser);
			
			if(user.getRole() != null && user.getRole().equals("admin")) {
				session.setAttribute("menu", "manage");
			}else {
				session.setAttribute("menu", "search");
			}
		}
		
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(value="/kakaoLogin", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam("code") String code, HttpSession session) throws Exception {
		ResponseEntity<String>  kakaoUserInfo;
		
		
		System.out.println("code:" + code);
		
		ResponseEntity<String> response = this.getKakaoToken(code);
		
		OAuthToken token = this.getTokenToVO(response);
		System.out.println("OAuthToken: " + token.toString());
		
		kakaoUserInfo = getKakaoUserData(token);
		
		System.out.println("kakaoUserInfo:" + kakaoUserInfo);
		
		//https://kapi.kakao.com/v2/user/me
		
		System.out.println("kakaoUserInfo getBody:"+ kakaoUserInfo.getBody());
		
		
		KakaoUser kakaoUser = new KakaoUser();
		kakaoUser = getTokenToKakaoVO(kakaoUserInfo);
		
		System.out.println("kakaoUser:" + kakaoUser.toString());
		System.out.println("kakaoUser nickName:"+kakaoUser.getProperties().get("nickname"));
		
		
		User user = setKakaoUserVO(kakaoUser);
		User dbUser = userService.getUser(user.getUserId().trim());
		
		if(dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
			session.setAttribute("user", dbUser);
			
		}else {
			userService.addUser(user);
			session.setAttribute("user", user);
		}
		session.setAttribute("menu", "search");
		
		
		
		return "redirect:/index.jsp";
	}
	
	
	@RequestMapping(value="/kakaoLogin", method=RequestMethod.POST)
	public String kakaoLoginPost(@RequestParam("code") String code) {
		
		System.out.println("POST code: " + code);
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