package com.test.springBoot.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.springBoot.model.User;
import com.test.springBoot.util.PwdManagement;

@Repository
public class UserDao {
	
	@Autowired  
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User getUserById(Long id) {
		User user = userRepository.findById(id).orElse(null);
		return user;
	}
	
	public User getUserByName(String name) {
		User u = userRepository.findUserByName(name);
		return u;
	}
	
	public List<User> saveAll(List<User> list){
		List<User> result = new ArrayList<User>();
		for(User user : list) {
			if(!existsByName(user.getUserName())) {
				result.add(save(user));
			}
		}
		return result;
	}
	
	public User save(User user) {
		user.setDc(new Date());
		user.setPwd(new PwdManagement().getHash(user.getPwd()));
		return userRepository.save(user);
	}
	
	public User update(User user) {
		if(existsByName(user.getUserName())) {
			User dbUser = userRepository.findUserByName(user.getUserName());
			dbUser.setUserName(user.getUserName());
			dbUser.setPwd(user.getPwd());
			dbUser.setEmail(user.getEmail());
			return save(dbUser);
		}
		return null;
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	public void deletByUser(User user) {
		Long id = userRepository.findUserIdByName(user.getUserName());
		userRepository.deleteById(id);
	}
	
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}
	
	public boolean existsByName(String name) {
		Long id = userRepository.findUserIdByName(name);
		if(id != null) {
			return userRepository.existsById(id);
		}else {
			return false;
		}
	}
	
	public List<User> search(String input){
		return userRepository.search(input);
	}
	
	public List<User> searchByName(String name){
		return userRepository.searchByName(name);
	}
	
	public List<User> searchByEmail(String email){
		return userRepository.searchByEmail(email);
	}
	
	public List<User> searchByDate(String date){
		return userRepository.searchByDate(date);
	}
}
