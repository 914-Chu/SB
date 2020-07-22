package com.test.springBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springBoot.model.User;
import com.test.springBoot.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {
	@Autowired 
	private UserDao userDao;
	
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}
	
	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}
	
	public User save(User user) {
		return userDao.save(user);
	}
	
	public List<User> saveAll(List<User> list) {
		return userDao.saveAll(list);
	}
	
	public User update(User user) {
		return userDao.update(user);
	}
	
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}
	
	public void deleteAll() {
		userDao.deleteAll();
	}
	
	public List<User> search(String input){
		return userDao.search(input);
	}
	
	public List<User> searchByName(String name){
		return userDao.searchByName(name);
	}
	
	public List<User> searchByEmail(String email){
		return userDao.searchByEmail(email);
	}
	
	public List<User> searchByDate(String date){
		return userDao.searchByDate(date);
	}

	public boolean existsByName(String name) {
		return userDao.existsByName(name);
	}
}
