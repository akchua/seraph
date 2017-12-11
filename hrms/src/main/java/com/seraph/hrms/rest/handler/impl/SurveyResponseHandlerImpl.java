package com.seraph.hrms.rest.handler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.beans.SurveyResponseFormBean;
import com.seraph.hrms.beans.SurveyResponseWrapperBean;
import com.seraph.hrms.database.entity.Personnel;
import com.seraph.hrms.database.entity.SurveyResponse;
import com.seraph.hrms.database.service.PersonnelService;
import com.seraph.hrms.database.service.SurveyResponseService;
import com.seraph.hrms.enums.Color;
import com.seraph.hrms.rest.handler.SurveyResponseHandler;
import com.seraph.hrms.rest.validator.SurveyResponseFormValidator;
import com.seraph.hrms.utility.Html;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@Component
@Transactional
public class SurveyResponseHandlerImpl implements SurveyResponseHandler{

	@Autowired
	private SurveyResponseService surveyResponseService;
	
	@Autowired
	private SurveyResponseFormValidator surveyResponseFormValidator;
	
	@Autowired
	private PersonnelService personnelService;
	
	@Override
	public SurveyResponse getSurveyResponse(Long surveyResponseId) {
		return surveyResponseService.find(surveyResponseId);
	}
	
	@Override
	public SurveyResponseWrapperBean getSurveyResponseByPersonnelAndIndex(Long personnelId, Integer index) {
		final List<SurveyResponse> surveyResponses = getSurveyResponseListByPersonnel(personnelId);
		return new SurveyResponseWrapperBean(surveyResponses.get(index), surveyResponses.size());
	}

	@Override
	public List<SurveyResponse> getSurveyResponseListByPersonnel(Long personnelId) {
		return surveyResponseService.findAllByPersonnelOrderedNaturally(personnelId);
	}

	@Override
	public ResultBean createSurveyResponse(List<SurveyResponseFormBean> surveyResponseForms, Long personnelId) {
		final ResultBean result;
		final Personnel personnel = personnelService.find(personnelId);
		
		if(personnel != null) {
			// bypass form validation
			List<SurveyResponse> surveyResponses = new ArrayList<SurveyResponse>();
			
			for(SurveyResponseFormBean surveyResponseForm: surveyResponseForms) {
				final SurveyResponse surveyResponse = new SurveyResponse();
				
				surveyResponse.setPersonnel(personnel);
				setSurveyResponse(surveyResponse, surveyResponseForm);
				surveyResponses.add(surveyResponse);
			}
			
			surveyResponseService.batchInsert(surveyResponses);
			result = new ResultBean(Boolean.TRUE, 
					Html.line(Html.text(Color.GREEN, "Successfully") + " created survey responses for " 
							+ Html.text(Color.BLUE, 
							personnel.getNamePrefix() + " " + personnel.getFirstName() + " " + personnel.getLastName()) + ". Thank you."));
		} else {
			result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load personnel. Please refresh the page."));
		}
		
		return result;
	}
	
	@Override
	public ResultBean updateSurveyResponse(SurveyResponseFormBean surveyResponseForm) {
		final ResultBean result;
		final Map<String, String> errors = surveyResponseFormValidator.validate(surveyResponseForm);
		
		if(errors.isEmpty()) {
			final SurveyResponse surveyResponse = surveyResponseService.find(surveyResponseForm.getId());
			
			setSurveyResponse(surveyResponse, surveyResponseForm);
			
			result = new ResultBean();
			result.setSuccess(surveyResponseService.update(surveyResponse));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " updated survey response for " 
								+ Html.text(Color.BLUE,
										surveyResponse.getPersonnel().getNamePrefix() + " " + surveyResponse.getPersonnel().getFirstName() + " " + surveyResponse.getPersonnel().getLastName()) + ". Thank you."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, "");
			result.addToExtras("errors", errors);
		}
		
		return result;
	}
	
	private void setSurveyResponse(SurveyResponse surveyResponse, SurveyResponseFormBean surveyResponseForm) {
		surveyResponse.setSurveyQuestion(surveyResponseForm.getSurveyQuestion());
		surveyResponse.setResponse(surveyResponseForm.getResponse());
	}
}
