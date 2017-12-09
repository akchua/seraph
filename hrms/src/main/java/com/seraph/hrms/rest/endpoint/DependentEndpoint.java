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
import com.seraph.hrms.beans.DependentFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Dependent;
import com.seraph.hrms.objects.ObjectList;
import com.seraph.hrms.rest.handler.DependentHandler;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   9 Dec 2017
 */
@Path("/dependent")
public class DependentEndpoint {

	@Autowired
	private DependentHandler dependentHandler;
	
	@GET
	@Path("/get")
	@Produces({ MediaType.APPLICATION_JSON })
	public Dependent getDependent(@QueryParam("dependentId") Long dependentId) {
		return dependentHandler.getDependent(dependentId);
	}
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public ObjectList<Dependent> getDependentObjectList(@QueryParam("pageNumber") Integer pageNumber, 
			@QueryParam("personnelId") Long personnelId,
			@QueryParam("searchKey") String searchKey) {
		return dependentHandler.getDependentObjectList(pageNumber, personnelId, searchKey);
	}
	
	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean saveDependent(@FormParam("dependentFormData") String dependentFormData,
			@FormParam("personnelId") Long personnelId) throws IOException {
		final ResultBean result;

		final DependentFormBean dependentForm = new ObjectMapper().readValue(dependentFormData, DependentFormBean.class);
		if(dependentForm.getId() != null) {
			result = dependentHandler.updateDependent(dependentForm);
		} else {
			result = dependentHandler.createDependent(dependentForm, personnelId);
		}
		
		return result;
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean removeDependent(@FormParam("dependentId") Long dependentId) {
		return dependentHandler.removeDependent(dependentId);
	}
}
