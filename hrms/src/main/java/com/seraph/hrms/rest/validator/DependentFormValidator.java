package com.seraph.hrms.rest.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.seraph.hrms.beans.DependentFormBean;
import com.seraph.hrms.enums.Gender;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   9 Dec 2017
 */
@Component
public class DependentFormValidator extends AbstractFormValidator<DependentFormBean> {

	@Override
	public Map<String, String> validate(DependentFormBean dependentForm) {
		final Map<String, String> errors = new HashMap<String, String>();
		
		String temp = "";
		
		temp = validateFirstName(dependentForm.getFirstName());
		if(!temp.isEmpty()) errors.put("firstName", temp);
		temp = validateLastName(dependentForm.getLastName());
		if(!temp.isEmpty()) errors.put("lastName", temp);
		temp = validateAge(dependentForm.getAge());
		if(!temp.isEmpty()) errors.put("age", temp);
		temp = validateGender(dependentForm.getGender());
		if(!temp.isEmpty()) errors.put("gender", temp);
		temp = validateRelationship(dependentForm.getRelationship());
		if(!temp.isEmpty()) errors.put("relationship", temp);
		
		return errors;
	}
	
	private String validateFirstName(String firstName) {
		return validateString(firstName, 2, 20);
	}
	
	private String validateLastName(String lastName) {
		return validateString(lastName, 2, 20);
	}
	
	private String validateAge(Integer age) {
		return validateInteger(age, 0, 23);
	}
	
	private String validateGender(Gender gender) {
		return notNull(gender);
	}
	
	private String validateRelationship(String relationship) {
		return validateString(relationship, 2, 40);
	}
}
