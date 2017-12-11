package com.seraph.hrms.rest.handler;

import java.util.List;

import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.beans.SurveyResponseFormBean;
import com.seraph.hrms.beans.SurveyResponseWrapperBean;
import com.seraph.hrms.database.entity.SurveyResponse;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
public interface SurveyResponseHandler {

	SurveyResponse getSurveyResponse(Long surveyResponseId);
	
	SurveyResponseWrapperBean getSurveyResponseByPersonnelAndIndex(Long personnelId, Integer index);

	List<SurveyResponse> getSurveyResponseListByPersonnel(Long personnelId);
	
	ResultBean createSurveyResponse(List<SurveyResponseFormBean> surveyResponseForms, Long personnelId);
	
	ResultBean updateSurveyResponse(SurveyResponseFormBean surveyResponseForm);
}
