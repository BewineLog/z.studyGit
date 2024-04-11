package com.model2.mvc.service.mail;

public interface MailService {

	public void makeRandomNumber(String receiverEmail);
	
	public void mailSend(String receiverEmail);
	
	public boolean checkAuth(String receiverEmail, int authCode);

}
