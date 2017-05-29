package com.neu.tools.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="user")
@PrimaryKeyJoinColumn(name="personId")
public class User extends Person{
	
	
	
	
	@Column(name="userName", nullable= false, length = 45)
	private String userName;	
	
	@Column(name="password", nullable = false, length = 45)
	private String password;
		
public User(String userName, String password){
		
		this.userName = userName;
		this.password = password;
		
	}
	
	public User()
	{
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
