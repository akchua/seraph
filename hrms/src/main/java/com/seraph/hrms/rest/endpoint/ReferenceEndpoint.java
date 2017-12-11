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
import com.seraph.hrms.beans.ReferenceFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Reference;
import com.seraph.hrms.objects.ObjectList;
import com.seraph.hrms.rest.handler.ReferenceHandler;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@Path("/reference")
public class ReferenceEndpoint {

	@Autowired
	private ReferenceHandler referenceHandler;
	
	@GET
	@Path("/get")
	@Produces({ MediaType.APPLICATION_JSON })
	public Reference getReference(@QueryParam("referenceId") Long referenceId) {
		return referenceHandler.getReference(referenceId);
	}
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public ObjectList<Reference> getReferenceObjectList(@QueryParam("pageNumber") Integer pageNumber, 
			@QueryParam("personnelId") Long personnelId,
			@QueryParam("searchKey") String searchKey) {
		return referenceHandler.getReferenceObjectList(pageNumber, personnelId, searchKey);
	}
	
	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean saveReference(@FormParam("referenceFormData") String referenceFormData,
			@FormParam("personnelId") Long personnelId) throws IOException {
		final ResultBean result;

		final ReferenceFormBean referenceForm = new ObjectMapper().readValue(referenceFormData, ReferenceFormBean.class);
		if(referenceForm.getId() != null) {
			result = referenceHandler.updateReference(referenceForm);
		} else {
			result = referenceHandler.createReference(referenceForm, personnelId);
		}
		
		return result;
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean removeReference(@FormParam("referenceId") Long referenceId) {
		return referenceHandler.removeReference(referenceId);
	}
}
