package com.neu.tools.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.tools.dao.UserDAO;
import com.neu.tools.exception.UserException;
import com.neu.tools.pojo.User;

@Component
public class UserValidator implements Validator {

	@Autowired
    @Qualifier("userDao")
	UserDAO userDao;
	
	@Override
	public boolean supports(Class aClass) {
		
		return aClass.equals(User.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email", "Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "error.invalid.phoneNumber", "Number Required");
		
		try {
            User user1 = userDao.getDistinctUser(user.getUserName());
            ///System.out.println(u+"==========================================");
            //String pattern = "(?=.*[^ ])[a-zA-Z0-9 ]+" ;
            if(user1 != null)
            {
                errors.rejectValue("userName", "error.invalid.userName", "Username already exists");
            }
        } catch (UserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
        
        //Regex for register page
        
        String p = "(?=.*[^ ])[a-zA-Z0-9 ]+" ;
        String emailp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String phonep = "\\d{3}-\\d{7}";
        if(!user.getFirstName().matches(p)){
            errors.rejectValue("firstName", "error.invalid.firstName", "Only Alphanumeric Values Allowed");
            return;
        }
        else if(!user.getLastName().matches(p)){
                errors.rejectValue("lastName", "error.invalid.lastName", "Only Alphanumeric Values Allowed");
                return;
            }
        else if(!user.getUserName().matches(p)){
                        errors.rejectValue("userName", "error.invalid.userName", "Only Alphanumeric Values Allowed");
                        return;
                    }
                    
       else if(!user.getEmail().matches(emailp)){
                        errors.rejectValue("email", "error.invalid.email", "Invalid Email ID");
                        return;
                }        
       else if(!user.getPhoneNumber().matches(phonep)){
                        errors.rejectValue("phoneNumber", "error.invalid.phoneNumber", "Correct Pattern: xxx-xxxxxxx");
                        return;
                }
                
    }
		
		
	}


