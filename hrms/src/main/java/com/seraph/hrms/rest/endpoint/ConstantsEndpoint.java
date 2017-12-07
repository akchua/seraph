package com.seraph.hrms.rest.endpoint;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.seraph.hrms.enums.CivilStatus;
import com.seraph.hrms.enums.Gender;
import com.seraph.hrms.enums.PersonnelPosition;
import com.seraph.hrms.enums.UserType;
import com.seraph.hrms.rest.handler.ConstantsHandler;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   6 Dec 2017
 */
@Path("/constants")
public class ConstantsEndpoint {

	@Autowired
	private ConstantsHandler constantsHandler;
	
	@GET
	@Path("/version")
	@Produces({ MediaType.TEXT_PLAIN })
	public String getVersion() {
		return constantsHandler.getVersion();
	}
	
	@GET
	@Path("/usertype")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<UserType> getUserTypeList() {
		return constantsHandler.getUserTypeList();
	}
	
	@GET
	@Path("/gender")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Gender> getGenderList() {
		return constantsHandler.getGenderList();
	}

	@GET
	@Path("/personnelposition")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<PersonnelPosition> getPersonnelPositionList() {
		return constantsHandler.getPersonnelPositionList();
	}
	
	@GET
	@Path("/civilstatus")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<CivilStatus> getCivilStatusList() {
		return constantsHandler.getCivilStatusList();
	}
}
