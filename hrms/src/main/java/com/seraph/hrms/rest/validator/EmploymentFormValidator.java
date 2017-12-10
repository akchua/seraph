package com.seraph.hrms.rest.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.seraph.hrms.beans.EmploymentFormBean;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   10 Dec 2017
 */
@Component
public class EmploymentFormValidator extends AbstractFormValidator<EmploymentFormBean> {

	@Override
	public Map<String, String> validate(EmploymentFormBean employmentForm) {
		final Map<String, String> errors = new HashMap<String, String>();
		
		String temp = "";
		
		temp = validateCompanyName(employmentForm.getCompanyName());
		if(!temp.isEmpty()) errors.put("companyName", temp);
		temp = validatePosition(employmentForm.getPosition());
		if(!temp.isEmpty()) errors.put("position", temp);
		temp = validateSalary(employmentForm.getSalary());
		if(!temp.isEmpty()) errors.put("salary", temp);
		temp = validateYears(employmentForm.getYears());
		if(!temp.isEmpty()) errors.put("years", temp);
		temp = validateCauseOfDischarge(employmentForm.getCauseOfDischarge());
		if(!temp.isEmpty()) errors.put("causeOfDischarge", temp);
		
		return errors;
	}
	
	private String validateCompanyName(String companyName) {
		return validateString(companyName, 2, 20);
	}
	
	private String validatePosition(String position) {
		return validateString(position, 2, 20);
	}
	
	private String validateSalary(Float salary) {
		return validateFloat(salary, 1, 1000000);
	}
	
	private String validateYears(Integer years) {
		return validateInteger(years, 0, 100);
	}
	
	private String validateCauseOfDischarge(String causeOfDischarge) {
		return validateString(causeOfDischarge, 2, 100);
	}
}
