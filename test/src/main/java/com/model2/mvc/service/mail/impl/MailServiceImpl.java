package com.model2.mvc.service.mail.impl;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.mail.MailService;

import java.util.concurrent.ConcurrentHashMap;
import javax.mail.internet.MimeMessage;

@Service("mailServiceImpl")
public class MailServiceImpl implements MailService{

	@Autowired
	@Qualifier("mailSender")
	private JavaMailSenderImpl mailSender;
	
//	private static final String senderEmail = "ljh71506@gmail.com";
	private static Map<String,Integer> map;
	
	
	public MailServiceImpl() {
		map = new ConcurrentHashMap<String,Integer>();
		System.out.println(this.getClass());
	}
	
	public void makeRandomNumber(String receiverEmail) {
		System.out.println("Random Number Generate");
		Random random = new Random();
		
		int randomNumber = random.nextInt(888888)+111111;
		System.out.println(receiverEmail +" : " + randomNumber);
		map.put(receiverEmail, randomNumber);
		
	}
	
	public void mailSend(String receiverEmail) {
		makeRandomNumber(receiverEmail);
		
		String setFrom = "ljh71506@gmail.com";
		String title =  "회원 가입 인증 이메일입니다.";
		String content = 
				"홈페이지를 방문해주셔서 감사합니다." + 
                "<br><br>" + 
			    "인증 번호는 " + map.get(receiverEmail) + "입니다." + 
			    "<br>" + 
			    "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(setFrom);
			helper.setTo(receiverEmail);
			helper.setSubject(title);
			helper.setText(content,true);
			
			mailSender.send(message);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public boolean checkAuth(String receiverEmail, int authCode) {
		
		System.out.println("checkAuth:" + map.get(receiverEmail).intValue() + " " + authCode);
		if(map.get(receiverEmail).intValue() == authCode) {
			return true;
		}else {
			return false;
		}
	}
	

	

}
