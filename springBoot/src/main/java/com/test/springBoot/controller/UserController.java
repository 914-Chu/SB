package com.test.springBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.springBoot.model.User;
import com.test.springBoot.service.UserServiceImpl;
import com.test.springBoot.util.PwdManagement;

import ch.qos.logback.core.net.LoginAuthenticator;

@RestController
@RequestMapping("/test")
public class UserController{
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	//Instantiation cause null pointer exception for JavaMailSender
	@Autowired
	private PwdManagement pwdManagement;
	
	@GetMapping("/getAll")
	public List<User> getAll(){
		List<User> list = userServiceImpl.getAllUsers();
		return list;
	}
	
	@GetMapping("/getOneById/{id}")
	public User getOneById(@PathVariable Long id){
		User user = userServiceImpl.getUserById(id);
		return user;
	}
	
	@GetMapping("/search/{input}")
	public List<User> search(@PathVariable String input){
		List<User> list = userServiceImpl.search(input);
		return list;
	}
	
	@GetMapping("/searchByName/{name}")
	public List<User> searchByName(@PathVariable String name){
		List<User> list = userServiceImpl.searchByName(name);
		return list;
	}
	
	@GetMapping("/searchByEmail/{email}")
	public List<User> searchByEmail(@PathVariable String email){
		List<User> list = userServiceImpl.searchByEmail(email);
		return list;
	}
	
	@GetMapping("/searchByDate/{date}")
	public List<User> searchByDate(@PathVariable String date){
		List<User> list = userServiceImpl.searchByDate(date);
		return list;
	}
	
	@GetMapping("/existsByName/{name}")
	public boolean existsByName(@PathVariable String name) {
		boolean exists = userServiceImpl.existsByName(name);
		return exists;
	}
	
	@GetMapping("/login")
	public User login(@RequestBody User user) {
		if(userServiceImpl.existsByName(user.getUserName())) {
			User temp = userServiceImpl.getUserByName(user.getUserName());
			if(temp != null && temp.getPwd().equals(pwdManagement.getHash(user.getPwd()))) {
				user.setId(temp.getId());
				user.setEmail(temp.getEmail());
				user.setDc(temp.getDc());
				return user;
			}
		}
		return null;
	}
	
	@PostMapping("/register")
	public User register(@RequestBody User user){
		if(!userServiceImpl.existsByName(user.getUserName())) {
			User temp = userServiceImpl.save(user);
			return temp;
		}else {
			return null;
		}
	}
	
	@PostMapping("/createAll")
	public List<User> createAll(@RequestBody List<User> list){
		List<User> result = userServiceImpl.saveAll(list);
		return result;
	}
	
	@PutMapping("/update")
	public User update(@RequestBody User user){
		User temp = userServiceImpl.update(user);
		return temp;
	}
	
	@PutMapping("/retrieve/{name}")
	public boolean retrieve(@PathVariable String name) {
		if(name != null && !"".equals(name) && userServiceImpl.existsByName(name)){
			User temp = userServiceImpl.getUserByName(name);
			String newPwd = pwdManagement.generatePwd();
			temp.setPwd(newPwd);
			pwdManagement.sentPwd(temp);
			userServiceImpl.update(temp);
			return true;
		}else {
			return false;
		}
	}
	
	@DeleteMapping("/deleteOneById/{id}")
	public void deleteOneById(@PathVariable Long id) {
		userServiceImpl.deleteById(id);
	}
	
	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		userServiceImpl.deleteAll();
	}
	

}