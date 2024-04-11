package com.model2.mvc.web.mail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.domain.Mail;
import com.model2.mvc.service.mail.MailService;

@RestController
@RequestMapping("/mail/*")
public class MailRestController {
	
	@Autowired
	@Qualifier("mailServiceImpl")
	private MailService mailServiceImpl;

	public MailRestController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value="/sendAuthMail", method=RequestMethod.POST)
	public Map<String,String> sendAuthMail(@RequestBody Mail mail) {
		
		Map<String,String> map = new HashMap<String,String>();
		
		System.out.println("receivermail:" + mail.getReceiverMail());
		
		if(mail.getReceiverMail() == null || mail.getReceiverMail().equals("null")) {
			map.put("result", "not ok");
			return map;
			
		}
		mailServiceImpl.mailSend(mail.getReceiverMail());
		System.out.println("return ok");
		map.put("result", "ok");
		return map;
	}
	
	
	@RequestMapping(value="/authCodeCheck", method=RequestMethod.POST)
	public Map<String,String> authCodeCheck(@RequestBody Mail mail){
		Map<String,String> map = new HashMap<String,String>();
		boolean result = mailServiceImpl.checkAuth(mail.getReceiverMail(), mail.getAuthCode());
		
		if(result == true) {
			map.put("result", "true");
		}else {
			map.put("result", "false");
		}
		
		return map;
		
	}
}
