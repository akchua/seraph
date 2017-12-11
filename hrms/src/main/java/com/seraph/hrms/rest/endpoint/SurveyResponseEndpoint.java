package com.seraph.hrms.rest.endpoint;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.beans.SurveyResponseFormBean;
import com.seraph.hrms.beans.SurveyResponseWrapperBean;
import com.seraph.hrms.database.entity.SurveyResponse;
import com.seraph.hrms.rest.handler.SurveyResponseHandler;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@Path("/surveyresponse")
public class SurveyResponseEndpoint {

	@Autowired
	private SurveyResponseHandler surveyResponseHandler;
	
	@GET
	@Path("/get")
	@Produces({ MediaType.APPLICATION_JSON })
	public SurveyResponse getSurveyResponse(@QueryParam("surveyResponseId") Long surveyResponseId) {
		return surveyResponseHandler.getSurveyResponse(surveyResponseId);
	}
	
	@GET
	@Path("/getindex")
	@Produces({ MediaType.APPLICATION_JSON })
	public SurveyResponseWrapperBean getSurveyResponseByPersonnelAndIndex(@QueryParam("personnelId") Long personnelId,
				@QueryParam("index") Integer index) {
		return surveyResponseHandler.getSurveyResponseByPersonnelAndIndex(personnelId, index);
	}
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<SurveyResponse> getSurveyResponseListByPersonnel(@QueryParam("personnelId") Long personnelId) {
		return surveyResponseHandler.getSurveyResponseListByPersonnel(personnelId);
	}
	
	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean saveSurveyResponse(@FormParam("surveyResponseFormData") String surveyResponseFormData,
			@FormParam("personnelId") Long personnelId) throws IOException {
		return surveyResponseHandler.updateSurveyResponse(new ObjectMapper().readValue(surveyResponseFormData, SurveyResponseFormBean.class));
	}
}
