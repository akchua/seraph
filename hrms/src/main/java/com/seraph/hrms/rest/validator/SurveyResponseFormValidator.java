package com.seraph.hrms.rest.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.seraph.hrms.beans.SurveyResponseFormBean;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@Component
public class SurveyResponseFormValidator extends AbstractFormValidator<SurveyResponseFormBean> {

	@Override
	public Map<String, String> validate(SurveyResponseFormBean surveyResponseForm) {
		final Map<String, String> errors = new HashMap<String, String>();
		
		String temp = "";
		
		temp = validateResponse(surveyResponseForm.getResponse());
		if(!temp.isEmpty()) errors.put("response", temp);
		
		return errors;
	}
	
	private String validateResponse(String response) {
		return validateString(response, 2, 100);
	}
}
