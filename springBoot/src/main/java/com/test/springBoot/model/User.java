package com.test.springBoot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="SBUSER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@SequenceGenerator(sequenceName = "USER_SEQ", allocationSize = 1, name = "USER_SEQ")
	private long id;
	@Column(name="USERNAME")
	private String userName;
	@Column(name="PWD")
	private String pwd;
	@Column(name="EMAIL")
	private String email;
	@Column(name="DC")
	private Date dc;
	
	public User() {
	}
	
	public User(String userName, String pwd, String email) {
		super();
		this.userName = userName;
		this.pwd = pwd;
		this.email = email;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDc() {
		return dc;
	}
	
	public void setDc(Date dc) {
		this.dc = dc;
	}
	
	@Override
	public String toString() {
		return "Student [ID: " + id 
				+ ", USER NAME: " + userName
				+ ", PWD: "  + pwd 
				+ ", EMAIL: "   + email 
				+ ", DATE CREATED: " + dc + "]";
	}
}
