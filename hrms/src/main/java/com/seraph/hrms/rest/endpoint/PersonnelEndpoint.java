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
import com.seraph.hrms.beans.PersonnelFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Personnel;
import com.seraph.hrms.objects.ObjectList;
import com.seraph.hrms.rest.handler.PersonnelHandler;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   7 Dec 2017
 */
@Path("/personnel")
public class PersonnelEndpoint {

	@Autowired
	private PersonnelHandler personnelHandler;
	
	@GET
	@Path("/get")
	@Produces({ MediaType.APPLICATION_JSON })
	public Personnel getPersonnel(@QueryParam("personnelId") Long personnelId) {
		return personnelHandler.getPersonnel(personnelId);
	}
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public ObjectList<Personnel> getPersonnelObjectList(@QueryParam("pageNumber") Integer pageNumber, @QueryParam("searchKey") String searchKey) {
		return personnelHandler.getPersonnelObjectList(pageNumber, searchKey);
	}
	
	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean savePersonnel(@FormParam("personnelFormData") String personnelFormData) throws IOException {
		final ResultBean result;

		final PersonnelFormBean personnelForm = new ObjectMapper().readValue(personnelFormData, PersonnelFormBean.class);
		if(personnelForm.getId() != null) {
			result = personnelHandler.updatePersonnel(personnelForm);
		} else {
			result = personnelHandler.createPersonnel(personnelForm);
		}
		
		return result;
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean removePersonnel(@FormParam("personnelId") Long personnelId) {
		return personnelHandler.removePersonnel(personnelId);
	}
}
