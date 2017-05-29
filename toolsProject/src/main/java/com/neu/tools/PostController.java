package com.neu.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neu.tools.dao.UserDAO;
import com.neu.tools.exception.PostException;
import com.neu.tools.dao.PostDAO;
import com.neu.tools.pojo.Customer;
import com.neu.tools.pojo.Message;
import com.neu.tools.pojo.Post;
import com.neu.tools.pojo.User;
import com.neu.tools.validator.CustomerValidator;
import com.neu.tools.validator.PostValidator;

@Controller
@RequestMapping("/post/*")
public class PostController {

	@Autowired
	@Qualifier("postDao")
	PostDAO postDao;
	
	@Autowired
	@Qualifier("postValidator")
	PostValidator postValidator;
	
	@InitBinder("customer")
	private void initBinder1(WebDataBinder binder) {
		binder.setValidator(postValidator);
	}
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("mailSender")
	MailSender mailSender;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="/post/add", method = RequestMethod.GET)
    public ModelAndView initializeForm(HttpServletRequest request) throws Exception {            
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("post", new Post());
        mv.setViewName("post-form");
        return mv;
    }
	
	 @RequestMapping(value = "/post/add", method = RequestMethod.POST)
	    public ModelAndView addCategory(@ModelAttribute("post") Post post, BindingResult result) throws Exception {
		 		
		 postValidator.validate(post, result);

			if (result.hasErrors()) {
				return new ModelAndView("post-form", "post", post);
			}

	                   
//	            //HttpSession session = request.getSession();
//	            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "+ post.getPostedBy());
//	            User u = userDao.get(post.getPostedBy());
//	            post.setUser(u);
//	            post = postDao.create(post);
//	            
//	            return new ModelAndView("post-success", "post", post);
//	            
	            try {
	    			if (post.getFilename().trim() != "" || post.getFilename() != null) {
	    				File directory;
	    				String check = File.separator; // Checking if system is linux
	    												// based or windows based by
	    												// checking seprator used.
	    				String path = null;
	    				if (check.equalsIgnoreCase("\\")) {
	    					path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
	    																				  // so we need to replace build in the path
	    																						}

	    				if (check.equalsIgnoreCase("/")) {
	    					path = servletContext.getRealPath("").replace("build/", "");
	    					path += "/"; // Adding trailing slash for Mac systems.
	    				}
	    				directory = new File(path + "\\" + post.getFilename());
	    				boolean temp = directory.exists();
	    				if (!temp) {
	    					temp = directory.mkdir();
	    				}
	    				if (temp) {
	    					// We need to transfer to a file
	    					File localFile;
	                        CommonsMultipartFile photoInMemory = post.getPhoto();
	                        String name = photoInMemory.getOriginalFilename();
	                        localFile =new File("C:\\webtools\\images",name);
	                        photoInMemory.transferTo(localFile);	    					
	                        post.setFilename(localFile.getPath());
//	    					System.out.println("File is stored at" + localFile.getPath());
//	    					System.out.print("registerNewUser");
//	    					User u = userDao.register(post);
	    					
	    					User u = userDao.get(post.getPostedBy());
	    		            post.setUser(u);
	    		            post = postDao.create(post);

	    				} else {
	    					System.out.println("Failed to create directory!");
	    				}
	    			}

	    		} catch (IllegalStateException e) {
	    			System.out.println("*** IllegalStateException: " + e.getMessage());
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			System.out.println("*** IOException: " + e.getMessage());
	    		} catch (PostException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    			System.out.println(e.getMessage());
		            return new ModelAndView("error", "errorMessage", "error while login");
	    		}

	            return new ModelAndView("post-success", "post", post);

	        }
	            
	         
	        
	        
	    
	 
	 @RequestMapping(value = "/post/list", method = RequestMethod.GET)
		public ModelAndView addListing(HttpServletRequest request) throws Exception {

			try {				
				HttpSession session = request.getSession();
				User u = (User) request.getAttribute("user");
				List<Post> post = postDao.list();
				return new ModelAndView("customer-post-list", "post", post);
				
			} catch (PostException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}
	 
	 @RequestMapping(value = "/post/search", method = RequestMethod.GET)
		public ModelAndView searchedList(HttpServletRequest request) throws Exception {

			try {				
				HttpSession session = request.getSession();
				User u = (User) request.getAttribute("user");
				String s = request.getParameter("search");
//				List<Post> post = postDao.searchedList(s);
				List<Post> post = postDao.list();
				List<Post> updatedPost = new ArrayList<Post>();
				for(Post post1 : post)
				{
					if(post1.getAddress().contains(s))
					{	
						updatedPost.add(post1);
						
					}
					
				}
				return new ModelAndView("customer-post-list", "post", updatedPost);
				
				
			} catch (PostException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}
	 
	 /**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/post/specificList", method = RequestMethod.GET)
		public ModelAndView specificList(HttpServletRequest request) throws Exception {

			try {				
				HttpSession session = request.getSession();
				User u = (User) session.getAttribute("user");
				System.out.println(u.getPersonId());
				List<Post> post = postDao.specificList(u.getPersonId());
				return new ModelAndView("user-specific-post", "post", post);
				
			} catch (PostException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}
	 
	 @RequestMapping(value="/post/reply", method = RequestMethod.GET)
	    public ModelAndView sendMessage1(HttpServletRequest request) throws Exception {            
	        
		 Customer customer = (Customer)request.getSession().getAttribute("customer");
	        
		    int personId = customer.getPersonId();
	        System.out.println(customer.getPersonId()+"--------------------------------------------------------");
	        int postId = Integer.parseInt(request.getParameter("reply"));
	        Post post = postDao.getById(postId);
	        List<Message> messageAllList = postDao.allMessage();
	        ModelAndView mv = new ModelAndView();
	        for(Message message : messageAllList)
	        {
	            if(message.getPost().getId() == postId && message.getCustomer().getPersonId() == personId)
	            {
	                mv.setViewName("message-already-sent");
	                return mv;
	            }
	        }
	        
	        mv.addObject("post", post);
	        mv.addObject("message", new Message());
	        mv.setViewName("message-page");
	        return mv;
	    }
	 
	 @RequestMapping(value="/post/reply", method = RequestMethod.POST)
	    public ModelAndView sendgttybgv(HttpServletRequest request, Message message, BindingResult result) throws Exception {            
	     
		 	String messageSent = request.getParameter("message");
		 	int postId = Integer.parseInt(request.getParameter("send"));
		 	Post post = postDao.getById(postId);
		 	Customer customer = (Customer) request.getSession().getAttribute("customer");
		 	System.out.println(customer.getPersonId());
		 	postDao.saveMessage(messageSent, post, customer);
		 	
		 	
		 //
		 	//int postId = Integer.parseInt(request.getParameter("reply"));
		 	//Post post = postDao.getById(postId);
	        ModelAndView mv = new ModelAndView();
	        mv.addObject("post", post);
	        mv.addObject("message",message);
	        mv.setViewName("message-success");
	        return mv;
	    }
	 
	 @RequestMapping(value="/post/viewMessage", method = RequestMethod.GET)
	    public ModelAndView viewMessage(HttpServletRequest request) throws Exception {            
	        
		 	User user = (User)request.getSession().getAttribute("user");
		 	int personId = user.getPersonId();
		 	System.out.println(personId+"--------------------------------------------------------------------------------");
	        
	        List<Message> messages = postDao.messages(personId);
	        ModelAndView mv = new ModelAndView();
	        mv.addObject("messages", messages);
	        
	        mv.setViewName("user-message-page");
	        return mv;
	    }
	 
	 @RequestMapping(value="/post/mail", method = RequestMethod.GET)
	    public ModelAndView sendMail(HttpServletRequest request) throws Exception {            
	        
		 	String email1 = request.getParameter("mail");
		 	
		 	SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(email1);
            email.setSubject("Real-estate notification");
            email.setText("Hi Thanks for applying to from ");
            mailSender.send(email);
		 	 ModelAndView mv = new ModelAndView();
		        
		        mv.setViewName("mail-sent");
		        return mv;
	    }
	 
	 @RequestMapping(value = "/post/delete", method = RequestMethod.POST)
		public ModelAndView deletePost(HttpServletRequest request) throws Exception {

			try {				
				HttpSession session = request.getSession();
				Post p = (Post) request.getAttribute("post");
				List<Post> post = postDao.list();
				return new ModelAndView("message-post", "post", post);
				
			} catch (PostException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
	 }	
	 
	 
	 
}
