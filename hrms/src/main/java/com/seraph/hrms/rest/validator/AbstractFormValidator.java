package com.seraph.hrms.rest.validator;

import java.util.function.Function;

import com.seraph.hrms.beans.FormBean;
import com.seraph.hrms.utility.EmailUtil;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   30 Nov 2017
 */
public abstract class AbstractFormValidator<T extends FormBean> 
		implements FormValidator<T> {
	
	public String notNull(Object o) {
		return o != null ? "" : "This field cannot be empty.";
	}
	
	public String notNull(String s, Function<String, String> f) {
		String ret = notNull(s);
		if(!ret.isEmpty()) return ret;
		
		ret = f.apply(s);
		
		return ret;
	}
	
	public String validateString(String s, int min, int max) {
		String ret = notNull(s);
		if(!ret.isEmpty()) return ret;
		if(min <= -1 && max <= -1) return s;
		
		if(min <= -1) {
			ret = s.trim().length() <= max ? "" : "This field cannot have more than " + max + " characters.";
		} else if(max <= -1) {
			ret = s.trim().length() >= min ? "" : "This field must be at least " + min + " characters long.";
		} else {
			ret = s.trim().length() >= min && s.trim().length() <= max 
					? "" : "This field requires " + min + " to " + max + " characters.";
		}
		
		return ret;
	}
	
	public String validateEmail(String email) {
		String ret = notNull(email);
		if(!ret.isEmpty()) return ret;
		
		ret = EmailUtil.validateEmail(email) ? "" : "Invalid email.";
		
		return ret;
	}
}
