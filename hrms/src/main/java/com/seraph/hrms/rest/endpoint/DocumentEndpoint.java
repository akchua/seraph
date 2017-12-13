package com.seraph.hrms.rest.endpoint;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seraph.hrms.beans.DocumentFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Document;
import com.seraph.hrms.enums.DocumentType;
import com.seraph.hrms.objects.ObjectList;
import com.seraph.hrms.rest.handler.DocumentHandler;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@Path("/document")
public class DocumentEndpoint {

	@Autowired
	private DocumentHandler documentHandler;
	
	@GET
	@Path("/get")
	@Produces({ MediaType.APPLICATION_JSON })
	public Document getDocument(@QueryParam("documentId") Long documentId) {
		return documentHandler.getDocument(documentId);
	}
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public ObjectList<Document> getDocumentObjectList(@QueryParam("pageNumber") Integer pageNumber, 
			@QueryParam("personnelId") Long personnelId,
			@QueryParam("searchKey") String searchKey) {
		return documentHandler.getDocumentObjectList(pageNumber, personnelId, searchKey);
	}
	
	@GET
	@Path("/listbyexpiration")
	@Produces({ MediaType.APPLICATION_JSON })
	public ObjectList<Document> getDocumentObjectListByExpirationDate(@QueryParam("pageNumber") Integer pageNumber, 
			@QueryParam("documentType") DocumentType documentType,
			@QueryParam("searchKey") String searchKey) {
		return documentHandler.getDocumentObjectListByExpirationDate(pageNumber, documentType, searchKey);
	}
	
	@POST
	@Path("/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)	
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean saveDocument(@FormDataParam("documentFormData") String documentFormData,
			@FormDataParam("personnelId") Long personnelId,
			@FormDataParam("file") InputStream in,
			@FormDataParam("file") FormDataContentDisposition info) throws IOException {
		final ResultBean result;

		final DocumentFormBean documentForm = new ObjectMapper().readValue(documentFormData, DocumentFormBean.class);
		if(documentForm.getId() != null) {
			result = documentHandler.updateDocument(documentForm, in, info);
		} else {
			result = documentHandler.createDocument(documentForm, personnelId, in, info);
		}
		
		return result;
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean removeDocument(@FormParam("documentId") Long documentId) {
		return documentHandler.removeDocument(documentId);
	}
}
