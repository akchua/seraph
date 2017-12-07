package com.seraph.hrms.rest.validator;

import java.util.Map;
import java.util.function.Function;

import com.seraph.hrms.beans.FormBean;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   30 Nov 2017
 */
public interface FormValidator<T extends FormBean> {

	Map<String, String> validate(T t);
	
	String notNull(Object o);
	
	String notNull(String o, Function<String, String> f);
	
	String validateString(String s, int min, int max);
	
	String validateStringNull(String s, int min, int max);
	
	String validateEmail(String email);
}
