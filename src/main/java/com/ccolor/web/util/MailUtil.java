package com.ccolor.web.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	private String user;
	private String pwd;
	private String sendProtocol="smtp";
	public MailUtil(String user, String pwd) {
		this.user = user;
		this.pwd = pwd;
	}
	/**
	 * 
	 * @param to
	 * @param host
	 * @param title
	 * @param body html/type
	 * @return
	 */
	public boolean sendByGMail(String to,String title,String body){
			return sendMail("smtp.gmail.com",465,true,true,to,title,body);
		
	}
	@SuppressWarnings("static-access")
	public boolean sendMail(String host,int port,boolean isAuth,boolean isSSL,String to,String title,String body){
		 Properties properties = System.getProperties();
	        properties.setProperty("mail.transport.protocol", sendProtocol);
	        properties.setProperty("mail.smtp.host", host);
	        properties.setProperty("mail.smtp.port", String.valueOf(port));
	        properties.put("mail.smtp.auth", isAuth);
	        properties.put("mail.smtp.ssl.enable", isSSL);
	        properties.put("mail.smtp.starttls.enable", false);  
	        Authenticator au=null;
	        if(isAuth){
	        	au=new Authenticator() {  
		            protected PasswordAuthentication getPasswordAuthentication() {  
		                return new PasswordAuthentication(user, pwd);  
		            }
		        };
	        }
	        Session mailSession = Session.getDefaultInstance(properties, au); 
	        try {
	            MimeMessage message = new MimeMessage(mailSession);
	           //message.setFrom(new InternetAddress(from));
	            message.addRecipient(Message.RecipientType.TO,
	                    new InternetAddress(to));
	            message.setSubject(title);
	            message.setContent(body.toString(), "text/html; charset=UTF-8");
	            Transport transport = mailSession.getTransport();
	            transport.send(message);
	            System.out.println("發送成功");
	            return true;
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	            return false;
	        }
	}
}
