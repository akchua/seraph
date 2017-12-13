package com.seraph.hrms.rest.handler;

import java.io.IOException;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.seraph.hrms.beans.DocumentFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Document;
import com.seraph.hrms.enums.DocumentType;
import com.seraph.hrms.objects.ObjectList;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
public interface DocumentHandler {

	Document getDocument(Long documentId);

	ObjectList<Document> getDocumentObjectList(Integer pageNumber, Long personnelId, String searchKey);
	
	ObjectList<Document> getDocumentObjectListByExpirationDate(Integer pageNumber, DocumentType documentType, String searchKey);
	
	ResultBean createDocument(DocumentFormBean documentForm, Long personnelId, InputStream in, FormDataContentDisposition info) throws IOException;
	
	ResultBean updateDocument(DocumentFormBean documentForm, InputStream in, FormDataContentDisposition info) throws IOException;
	
	ResultBean removeDocument(Long documentId);
}
