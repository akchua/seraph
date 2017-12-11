package com.seraph.hrms.beans;

import com.seraph.hrms.database.entity.SurveyResponse;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
public class SurveyResponseWrapperBean {

	private SurveyResponse surveyResponse;
	
	private Integer totalQuestions;
	
	public SurveyResponseWrapperBean(SurveyResponse surveyResponse, Integer totalQuestions) {
		setSurveyResponse(surveyResponse);
		setTotalQuestions(totalQuestions);
	}

	public SurveyResponse getSurveyResponse() {
		return surveyResponse;
	}

	public void setSurveyResponse(SurveyResponse surveyResponse) {
		this.surveyResponse = surveyResponse;
	}

	public Integer getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
}
