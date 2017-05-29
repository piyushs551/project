package com.neu.tools.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.neu.tools.pojo.Customer;
import com.neu.tools.pojo.User;

public class UserInterceptor extends HandlerInterceptorAdapter {

	String errorPage;

    public String getErrorPage() {
        return errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = (HttpSession) request.getSession();
        
        if(session.getAttribute("user") != null || session.getAttribute("customer") !=null){
            System.out.println("in interceptor");
            return true;
        }
        if(session.getAttribute("user") == null && session.getAttribute("customer") == null){
        System.out.println("no user");
        if(session.getAttribute("user") == null){
        ModelAndView mav = new ModelAndView("home", "user", new User());
        // eventually populate the model
        throw new ModelAndViewDefiningException(mav);
        }
        }else if(session.getAttribute("customer") == null){
        	ModelAndView mav = new ModelAndView("home", "customer", new Customer());
            // eventually populate the model
            throw new ModelAndViewDefiningException(mav);
        }
        //response.sendRedirect(errorPage);
        return false;
        
        
        
    }
	
}
