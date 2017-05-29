package com.neu.tools.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="message")
@Inheritance(strategy=InheritanceType.JOINED)
public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="messageId", unique=true, nullable=false)
	private int messageId;
	
	
	@Column(name="message", nullable=false)
	private String message;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "personId")
	private Customer customer;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "postId")
	private Post post;
	
	@Column(name="messageTo", nullable=false)
	private int messageTo;
	
	

	public int getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(int messageTo) {
		this.messageTo = messageTo;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	
	
}
