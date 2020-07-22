package com.test.springBoot.util;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.util.Random; 
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.test.springBoot.model.User;

//For component scan
@Component
public class PwdManagement{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String getHash(String pwd) {
		 try { 
	            MessageDigest md = MessageDigest.getInstance("MD5"); 
	  
	            byte[] messageDigest = md.digest(pwd.getBytes()); 
	  
	            BigInteger no = new BigInteger(1, messageDigest); 
	  
	            String hashtext = no.toString(16); 
	            while (hashtext.length() < 32) { 
	                hashtext = "0" + hashtext; 
	            } 
	            return hashtext; 
	        }catch (NoSuchAlgorithmException e) { 
	            throw new RuntimeException(e); 
	        } 
	}
	
	public String generatePwd() {
		final int LEN = 10;
        String temp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" 
        			+ "abcdefghijklmnopqrstuvwxyz"
        			+ "0123456789";
        String pwd = "";
        Random rand = new Random();
        
        for(int i = 0; i < LEN; i ++) {
        	pwd += temp.charAt(rand.nextInt(temp.length()));
        }
        
        return pwd;
	}
	
	public void sentPwd(User user) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user.getEmail());
		msg.setSubject("Retreive Password");
		msg.setText("User name: " + user.getUserName() + "\n"
				   +"New password: " + user.getPwd()); 
		javaMailSender.send(msg);
	}
}
