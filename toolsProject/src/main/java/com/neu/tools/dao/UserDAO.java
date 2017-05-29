package com.neu.tools.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.neu.tools.dao.DAO;
import com.neu.tools.exception.UserException;
import com.neu.tools.pojo.Customer;
import com.neu.tools.pojo.User;



public class UserDAO extends DAO {
	
	public UserDAO() {
	}

	public User get(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);
			User user = (User) q.uniqueResult();
			commit();
			close();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	public Customer getCustomer(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Customer where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);
			Customer customer = (Customer) q.uniqueResult();
			commit();
			close();
			return customer;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	//getting particular user
	public User get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where personId= :personId");
			q.setInteger("personId", userId);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}
	
	//for user validator
	public User getDistinctUser(String userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where personId= :personId");
			q.setString("personId", userId);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}

	public User register(User u)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			User user = new User(u.getUserName(), u.getPassword());

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(u.getEmail());
			user.setPhoneNumber(u.getPhoneNumber());
			user.setStatus(u.getStatus());
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	//registering a buyer
	
	public Customer registerCustomer(Customer c)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			Customer customer = new Customer(c.getUserName(), c.getPassword());

			customer.setFirstName(c.getFirstName());
			customer.setLastName(c.getLastName());
			customer.setEmail(c.getEmail());
			customer.setPhoneNumber(c.getPhoneNumber());
			customer.setStatus(c.getStatus());
			getSession().save(customer);
			commit();
			return customer;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}

	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUserName(), e);
		}
	}

}
