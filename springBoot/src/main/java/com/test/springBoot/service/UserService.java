package com.test.springBoot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.springBoot.model.User;

@Service
public interface UserService {
	public List<User> getAllUsers();
	
	public User getUserById(Long id);
	
	public User getUserByName(String name);
		
	public User save(User user);
	
	public List<User> saveAll(List<User> list);
	
	public User update(User user);
	
	public void deleteById(Long id);
	
	public void deleteAll();
	
	public List<User> search(String input);
	
	public List<User> searchByName(String name);
	
	public List<User> searchByEmail(String email);
	
	public List<User> searchByDate(String date);
	
	public boolean existsByName(String name);
}
