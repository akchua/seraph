package com.seraph.hrms;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.seraph.hrms.rest.endpoint.ConstantsEndpoint;
import com.seraph.hrms.rest.endpoint.DependentEndpoint;
import com.seraph.hrms.rest.endpoint.EmploymentEndpoint;
import com.seraph.hrms.rest.endpoint.PersonnelEndpoint;
import com.seraph.hrms.rest.endpoint.SecurityEndpoint;
import com.seraph.hrms.rest.endpoint.UserEndpoint;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Nov 30, 2017
 */
@Component
@ApplicationPath("services")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		
		register(MultiPartFeature.class);
		
		register(SecurityEndpoint.class);
		register(UserEndpoint.class);
		register(PersonnelEndpoint.class);
		register(DependentEndpoint.class);
		register(EmploymentEndpoint.class);
		
		register(ConstantsEndpoint.class);
	}
}
