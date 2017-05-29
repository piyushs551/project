package com.neu.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.tools.dao.UserDAO;
import com.neu.tools.validator.CustomerValidator;
import com.neu.tools.validator.UserValidator;
import com.neu.tools.exception.UserException;
import com.neu.tools.pojo.Customer;
import com.neu.tools.pojo.User;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	@Autowired
    @Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator userValidator;
	
	@Autowired
	@Qualifier("customerValidator")
	CustomerValidator customerValidator;
	
	@InitBinder("user")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}
	
	@InitBinder("customer")
	private void initBinder1(WebDataBinder binder) {
		binder.setValidator(customerValidator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "user-home";
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		
		try {

			System.out.print("loginUser");

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
			Customer c = userDao.getCustomer(request.getParameter("username"), request.getParameter("password"));
			if(u == null && c== null){
				System.out.println("UserName/Password does not exist");
				request.getSession().setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			
			
			if(c == null){
			if(u.getStatus().equalsIgnoreCase("seller")){
				request.getSession().setAttribute("user", u);
			return "user-home";
			}
			}
			if(u == null){
			if(c.getStatus().equalsIgnoreCase("customer")){
				request.getSession().setAttribute("customer", c);
				return "customer-home";
			}
			}
				
			return null;
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");
		
		

		return new ModelAndView("register-user", "user", new User());

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {

		userValidator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("register-user", "user", user);
		}

		try {

			System.out.print("registerNewUser");

			User u = userDao.register(user);
			
			request.getSession().setAttribute("user", u);
			
			return new ModelAndView("user-home", "user", u);

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
	@RequestMapping(value = "/user/signupCustomer", method = RequestMethod.GET)
	protected ModelAndView registerCusomer() throws Exception {
		System.out.print("register a customer!!!!!1");

		return new ModelAndView("register-customer", "customer", new Customer());

	}
	
	@RequestMapping(value = "/user/signupCustomer", method = RequestMethod.POST)
	protected ModelAndView registerNewCustomer(HttpServletRequest request,  @ModelAttribute("customer") Customer customer, BindingResult result) throws Exception {

		customerValidator.validate(customer, result);

		if (result.hasErrors()) {
			return new ModelAndView("register-customer", "customer", customer);
		}

		try {

			System.out.print("registerNewUser");

			Customer u = userDao.registerCustomer(customer);
			
			request.getSession().setAttribute("customer", u);
			
			return new ModelAndView("customer-home", "customer", u);

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
	@RequestMapping(value = "/user/userHome", method = RequestMethod.GET)
	protected ModelAndView homeRedirect() throws Exception {
		System.out.print("redirecting to home");

		return new ModelAndView("user-home");

	}
	
	@RequestMapping(value = "/user/customerHome", method = RequestMethod.GET)
	protected ModelAndView customerHomeRedirect() throws Exception {
		System.out.print("redirecting to home");

		return new ModelAndView("customer-home");

	}
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	protected String userLogout(HttpServletRequest request) throws Exception {
		System.out.print("redirecting to index==================");
		request.getSession().invalidate();
		return "redirect: /tools";

	}
}
