package com.seraph.hrms.rest.endpoint;

import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seraph.hrms.beans.EmploymentFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Employment;
import com.seraph.hrms.objects.ObjectList;
import com.seraph.hrms.rest.handler.EmploymentHandler;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   10 Dec 2017
 */
@Path("/employment")
public class EmploymentEndpoint {

	@Autowired
	private EmploymentHandler employmentHandler;
	
	@GET
	@Path("/get")
	@Produces({ MediaType.APPLICATION_JSON })
	public Employment getEmployment(@QueryParam("employmentId") Long employmentId) {
		return employmentHandler.getEmployment(employmentId);
	}
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public ObjectList<Employment> getEmploymentObjectList(@QueryParam("pageNumber") Integer pageNumber, 
			@QueryParam("personnelId") Long personnelId,
			@QueryParam("searchKey") String searchKey) {
		return employmentHandler.getEmploymentObjectList(pageNumber, personnelId, searchKey);
	}
	
	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean saveEmployment(@FormParam("employmentFormData") String employmentFormData,
			@FormParam("personnelId") Long personnelId) throws IOException {
		final ResultBean result;
		System.out.println("here");

		final EmploymentFormBean employmentForm = new ObjectMapper().readValue(employmentFormData, EmploymentFormBean.class);
		if(employmentForm.getId() != null) {
			result = employmentHandler.updateEmployment(employmentForm);
		} else {
			result = employmentHandler.createEmployment(employmentForm, personnelId);
		}
		
		return result;
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean removeEmployment(@FormParam("employmentId") Long employmentId) {
		return employmentHandler.removeEmployment(employmentId);
	}
}
