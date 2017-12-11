package com.seraph.hrms.beans;

import com.seraph.hrms.enums.SurveyQuestion;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
public class SurveyResponseFormBean extends FormBean {

	private SurveyQuestion surveyQuestion;
	
	private String response;

	public SurveyQuestion getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
