package com.seraph.hrms.rest.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.seraph.hrms.beans.ReferenceFormBean;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@Component
public class ReferenceFormValidator extends AbstractFormValidator<ReferenceFormBean> {

	@Override
	public Map<String, String> validate(ReferenceFormBean referenceForm) {
		final Map<String, String> errors = new HashMap<String, String>();
		
		String temp = "";
		
		temp = validateFirstName(referenceForm.getFirstName());
		if(!temp.isEmpty()) errors.put("firstName", temp);
		temp = validateLastName(referenceForm.getLastName());
		if(!temp.isEmpty()) errors.put("lastName", temp);
		temp = validateContactNumber(referenceForm.getContactNumber());
		if(!temp.isEmpty()) errors.put("contactNumber", temp);
		
		return errors;
	}
	
	private String validateFirstName(String firstName) {
		return validateString(firstName, 2, 20);
	}
	
	private String validateLastName(String lastName) {
		return validateString(lastName, 2, 20);
	}
	
	private String validateContactNumber(String contactNumber) {
		return validateString(contactNumber, 2, 20);
	}
}
