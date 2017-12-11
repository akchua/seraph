package com.seraph.hrms.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SurveyQuestion {

	QUESTION_010("Are you willing to abide with the Rules and Regulations promulgated by PNP-SOSIA and this Agency?", "Yes or No"),
	
	QUESTION_020("Are you going to accept any place of assignment given to you if qualified?", "Yes or No"),
	
	QUESTION_030("Will you submit to a military haircut?", "Yes or No"),
	
	QUESTION_040("Do you have any Life Insurance?", "Yes or No"),
	
	QUESTION_050("Did you take any medication or has been confined in the hospital for the past three years?", "Yes or No; If yes, state the cause of your confinement."),
	
	QUESTION_060("How did you know about the Agency?", "Ads / Referral / Walk-in / Absorption / others (give details)"),
	
	QUESTION_070("Have you been convicted of any crime?", "Yes or No; If yes, give details"),
	
	QUESTION_080("Do you have pending case in court?", "Yes or No; If yes, give details"),
	
	QUESTION_090("Do you drink intoxicating liquor?", "Yes or No; If yes, give details"),
	
	QUESTION_100("Do you smoke?", "Yes or No; If yes, give details"),
	
	QUESTION_110("Do you take prohibited drugs?", "Yes or No; If yes, give details"),
	
	QUESTION_120("State in less than fifty words why you have chosen this job.", "");
	
	private final String question;

	private final String answerGuide;
	
	SurveyQuestion(final String question, String answerGuide) {
		this.question = question;
		this.answerGuide = answerGuide;
	}
	
	public String getName() {
		return toString();
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getAnswerGuide() {
		return answerGuide;
	}
}
