package com.neu.tools.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.tools.pojo.Post;
import com.neu.tools.pojo.User;

@Component
public class PostValidator implements Validator {
	
	@Override
	public boolean supports(Class aClass) {
		
		return aClass.equals(User.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Post post = (Post) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "error.invalid.type", "type Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.description", "description Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.invalid.address", "address Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.invalid.price", "price Required");
		
		String photo = post.getPhoto().getOriginalFilename();
		
		if(photo.equals(""))
		{
			errors.rejectValue("photo", "error.invalid.photo", "please upload a picture");
		}
		else if(!photo.endsWith("jpg") && !photo.endsWith("png") && !photo.endsWith("jpeg"))
		{
			errors.rejectValue("photo", "error.invalid.photo", "please upload specific format");
		}
		
		String p = "(?=.*[^ ])[a-zA-Z0-9 ]+" ;
		
		if(!post.getType().matches(p)){
            errors.rejectValue("type", "error.invalid.firstName", "Only Alphanumeric Values Allowed");
            return;
        }
        else if(!post.getDescription().matches(p)){
                errors.rejectValue("description", "error.invalid.lastName", "Only Alphanumeric Values Allowed");
                return;
            }
        else if(!post.getAddress().matches(p)){
                        errors.rejectValue("address", "error.invalid.userName", "Only Alphanumeric Values Allowed");
                        return;
                    }
                    
		
	}

}
