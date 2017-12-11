package com.seraph.hrms.database.entity;import javax.persistence.Basic;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.EnumType;import javax.persistence.Enumerated;import javax.persistence.FetchType;import javax.persistence.JoinColumn;import javax.persistence.ManyToOne;import javax.persistence.Table;import javax.validation.constraints.NotNull;import org.hibernate.annotations.NotFound;import org.hibernate.annotations.NotFoundAction;import org.hibernate.annotations.Where;import com.seraph.hrms.database.entity.base.BaseObject;import com.seraph.hrms.enums.SurveyQuestion;/** * @author  Adrian Jasper K. Chua * @version 1.0 * @since   11 December 2017 */@Entity(name = "SurveyResponse")@Table(name = SurveyResponse.TABLE_NAME)public class SurveyResponse extends BaseObject {	private static final long serialVersionUID = -6845408368602045333L;		public static final String TABLE_NAME = "survey_response";		private Personnel personnel;		private SurveyQuestion surveyQuestion;		private String response;	@ManyToOne(targetEntity = Personnel.class, fetch = FetchType.LAZY)	@JoinColumn(name = "personnel_id")	@Where(clause = "valid = 1")	@NotFound(action = NotFoundAction.IGNORE)	public Personnel getPersonnel() {		return personnel;	}	public void setPersonnel(Personnel personnel) {		this.personnel = personnel;	}	@Enumerated(EnumType.STRING)	@NotNull	@Column(name = "survey_question")	public SurveyQuestion getSurveyQuestion() {		return surveyQuestion;	}	public void setSurveyQuestion(SurveyQuestion surveyQuestion) {		this.surveyQuestion = surveyQuestion;	}	@Basic	@Column(name = "response")	public String getResponse() {		return response;	}	public void setResponse(String response) {		this.response = response;	}}
