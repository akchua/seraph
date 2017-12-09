package com.seraph.hrms.rest.endpoint;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seraph.hrms.beans.PersonnelFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Personnel;
import com.seraph.hrms.database.entity.PersonnelImage;
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
	@Path("/getimage/{fileName}")
	@Produces("image/*")
	public Response getPersonnelImageByFileName(@PathParam("fileName") String fileName) throws IOException {
		File personnelImage = personnelHandler.findPersonnelImageByFileName(fileName);
		if(personnelImage.exists())
			return Response.ok(personnelImage, new MimetypesFileTypeMap().getContentType(personnelImage))
				.build();
		else return null;
	}
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public ObjectList<Personnel> getPersonnelObjectList(@QueryParam("pageNumber") Integer pageNumber, @QueryParam("searchKey") String searchKey) {
		return personnelHandler.getPersonnelObjectList(pageNumber, searchKey);
	}
	
	@GET
	@Path("/imagelist")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<PersonnelImage> getPersonnelImageList(@QueryParam("personnelId") Long personnelId) {
		return personnelHandler.getPersonnelImageList(personnelId);
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
	@Path("/uploadimage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)	
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean uploadPersonnelImage(@FormDataParam("personnelId") Long personnelId,
			@FormDataParam("imageFile") InputStream in,
			@FormDataParam("imageFile") FormDataContentDisposition info) throws IOException {
		return personnelHandler.savePersonnelImage(personnelId, in, info);
	}
	
	@POST
	@Path("/setthumbnail")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean setPersonnelImageAsThumbnail(@FormParam("personnelImageId") Long personnelImageId) {
		return personnelHandler.setPersonnelImageAsThumbnail(personnelImageId);
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean removePersonnel(@FormParam("personnelId") Long personnelId) {
		return personnelHandler.removePersonnel(personnelId);
	}
	
	@POST
	@Path("/removeimage")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean removePersonnelImage(@FormParam("personnelImageId") Long personnelImageId) {
		return personnelHandler.removePersonnelImage(personnelImageId);
	}
}
