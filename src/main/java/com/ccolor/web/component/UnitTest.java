package com.ccolor.web.component;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ccolor.web.util.MailUtil;

import javax.mail.Authenticator;

public class UnitTest {
	public static void main(String[] args) {

        //user
        //final String user = "angle319";
        final String user = "angle319angle319";
        //password
        //final String pwd = "19850812";
        final String pwd = "a86632534";

        //接收者的email.
        String to = "angle319@foxmail.com";

        //寄件人的email
        //String from = "angle319@mail.ccolor.com.tw";

        // 寄件的smtp伺服器
        String host = "smtp.gmail.com";
        //String host = "mail.ccolor.com.tw";
        
        // 主旨
        String subject = "Java 寄來的信";

        //內文
        String body = "<html><p>Java程式發送出來的信</p></html>";

        MailUtil mu=new MailUtil(user, pwd);
        mu.sendByGMail(to, subject, body);
    }
}
