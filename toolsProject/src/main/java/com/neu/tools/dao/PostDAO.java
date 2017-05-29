package com.neu.tools.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;


import com.neu.tools.exception.PostException;
import com.neu.tools.exception.UserException;
import com.neu.tools.pojo.Customer;
import com.neu.tools.pojo.Message;
import com.neu.tools.pojo.Post;
import com.neu.tools.pojo.User;



public class PostDAO extends DAO {
	
	public Post get(String title) throws PostException {
        try {
            begin();
            Query q=getSession().createQuery("from Category where title= :title");
            q.setString("title",title);
            Post post=(Post)q.uniqueResult();
            commit();
            return post;
        } catch (HibernateException e) {
            rollback();
            throw new PostException("Could not obtain the named category " + title + " " + e.getMessage());
        }
    }
	
	public Post getById(int postId) throws PostException {
        try {
            begin();
            Query q=getSession().createQuery("from Post where id= :postId");
            q.setInteger("postId",postId);
            Post post=(Post)q.uniqueResult();
            commit();
            return post;
        } catch (HibernateException e) {
            rollback();
            throw new PostException("Could not obtain the named category " + e.getMessage());
        }
    }
	
	 public Post create(Post post)
	            throws PostException {
	        try {
	            begin();            
	            getSession().save(post);     
	            commit();
	            return post;
	        } catch (HibernateException e) {
	            rollback();
	            
	            throw new PostException("Exception while creating post: " + e.getMessage());
	        }
	    }
	 
	 public void delete(Post post)
	            throws PostException {
	        try {
	            begin();
	            getSession().delete(post);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new PostException("Could not delete post", e);
	        }
	    }
	 
	 public List<Post> list() throws PostException{
	    	
	    	try {
	            begin();
	            Query q = getSession().createQuery("from Post");
	            List<Post> adverts = q.list();
	            commit();	
	            return adverts;
	        } catch (HibernateException e) {
	            rollback();
	            throw new PostException("Could not detect posts", e);
	        }
	    	
	    }
	 
	 public List<Post> searchedList(String s) throws PostException{
	    	
	    	try {
	            begin();
	            Query q = getSession().createQuery("from Post where address = :add");
	            q.setString("add", s);
	            List<Post> searchedList = q.list();
	            commit();	
	            return searchedList;
	        } catch (HibernateException e) {
	            rollback();
	            throw new PostException("Could not detect any particular posts", e);
	        }
	    	
	    }

	 //saving a message
	 
	 public void saveMessage(String message, Post post, Customer customer)
				throws UserException {
			try {
				begin();
				System.out.println("saving the message");

				Message message1 = new Message();
				
				message1.setMessage(message);
			 	message1.setPost(post);
			 	message1.setCustomer(customer);
				message1.setMessageTo(post.getUser().getPersonId());
				getSession().save(message1);
				commit();
				

			} catch (HibernateException e) {
				rollback();
				throw new UserException("Exception while creating user: " + e.getMessage());
			}
		}
	 
	 public List<Message> messages(int personId) throws PostException{
	    	
	    	try {
	            begin();
	            
	            Query q = getSession().createQuery("from Message where messageTo = :personId");
	            q.setInteger("personId", personId);
	            List<Message> message = q.list();
	            commit();	
	            return message;
	        } catch (HibernateException e) {
	            rollback();
	            throw new PostException("Could not detect posts", e);
	        }
	    	
	    }
	 
	 public List<Message> allMessage() throws PostException{
	    	
	    	try {
	            begin();
	            
	            Query q = getSession().createQuery("from Message");
	            
	            List<Message> message = q.list();
	            commit();	
	            return message;
	        } catch (HibernateException e) {
	            rollback();
	            throw new PostException("Could not detect posts", e);
	        }
	    	
	    }
	 
	 
	 public List<Post> specificList(int personId) throws PostException{
	    	
	    	try {
	            begin();
	            
	            Query q = getSession().createQuery("from Post where user_personId = :personId");
	            q.setInteger("personId", personId);
	            List<Post> post = q.list();
	            commit();	
	            return post;
	        } catch (HibernateException e) {
	            rollback();
	            throw new PostException("Could not detect posts", e);
	        }
	    	
	    }
	 
	 
	
}
