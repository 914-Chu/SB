package com.test.springBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.springBoot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	@Query("SELECT u FROM User u WHERE u.userName = ?1")
	User findUserByName(String userName);
	
	@Query("SELECT u.id FROM User u WHERE u.userName = ?1")
	Long findUserIdByName(String userName);
	
	@Query("SELECT u FROM User u WHERE u.userName LIKE %?1%")
	List<User> searchByName(String name);
	
	@Query("SELECT u FROM User u WHERE u.email LIKE %?1%")
	List<User> searchByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE TO_CHAR(u.dc, 'dd-mm-yyyy') LIKE %?1%")
	List<User> searchByDate(String date);
	
	@Query("SELECT u FROM User u WHERE u.userName LIKE %?1% OR u.email LIKE %?1% OR u.dc LIKE %?1%")
	List<User> search(String input);
}
