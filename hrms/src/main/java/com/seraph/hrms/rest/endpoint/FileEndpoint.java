package com.seraph.hrms.rest.endpoint;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.seraph.hrms.rest.handler.FileHandler;

/**
 * @author Adrian Jasper K. Chua
 * @version 1.0
 * @since 11 Dec 2017
 */
@Path("/file")
public class FileEndpoint {

	@Autowired
	private FileHandler fileHandler;
	
	@GET
	@Path("/document/{fileName}")
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public Response getDocumentByFileName(@PathParam("fileName") String fileName) throws IOException {
		File document = fileHandler.findDocumentByFileName(fileName);
		if(document.exists())
			return Response.ok(document, MediaType.APPLICATION_OCTET_STREAM)
				.header("Content-Disposition", "attachment; filename=\"" + document.getName() + "\"" )
				.build();
		else return null;
	}
}
