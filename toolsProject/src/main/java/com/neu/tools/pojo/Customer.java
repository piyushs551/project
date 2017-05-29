package com.neu.tools.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@PrimaryKeyJoinColumn(name="personId")
public class Customer extends Person{
	
	
	
	
	@Column(name="userName", nullable= false, length = 45)
	private String userName;	
	
	@Column(name="password", nullable = false, length = 45)
	private String password;
	
	@OneToMany(mappedBy = "customer")
	private Set<Message> messageSet = new HashSet<Message>();
		
public Customer(String userName, String password){
		
		this.userName = userName;
		this.password = password;
		
	}
	
	public Customer()
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

	public Set<Message> getMessageSet() {
		return messageSet;
	}

	public void setMessageSet(Set<Message> messageSet) {
		this.messageSet = messageSet;
	}
	
	
}
