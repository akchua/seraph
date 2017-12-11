package com.seraph.hrms.rest.handler.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seraph.hrms.UserContextHolder;
import com.seraph.hrms.beans.DocumentFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.constants.FileConstants;
import com.seraph.hrms.database.entity.Document;
import com.seraph.hrms.database.entity.Personnel;
import com.seraph.hrms.database.service.DocumentService;
import com.seraph.hrms.database.service.PersonnelService;
import com.seraph.hrms.enums.Color;
import com.seraph.hrms.objects.ObjectList;
import com.seraph.hrms.rest.handler.DocumentHandler;
import com.seraph.hrms.rest.validator.DocumentFormValidator;
import com.seraph.hrms.utility.Html;
import com.seraph.hrms.utility.StringHelper;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@Component
@Transactional
public class DocumentHandlerImpl implements DocumentHandler {

	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private DocumentFormValidator documentFormValidator;
	
	@Autowired
	private PersonnelService personnelService;
	
	@Autowired
	private FileConstants fileConstants;
	
	@Override
	public Document getDocument(Long documentId) {
		return documentService.find(documentId);
	}

	@Override
	public ObjectList<Document> getDocumentObjectList(Integer pageNumber, Long personnelId, String searchKey) {
		return documentService.findAllByPersonnelWithPagingOrderByType(pageNumber, UserContextHolder.getItemsPerPage(), personnelId, searchKey);
	}

	@Override
	public ResultBean createDocument(DocumentFormBean documentForm, Long personnelId, InputStream in,
			FormDataContentDisposition info) throws IOException {
		final ResultBean result;
		final Personnel personnel = personnelService.find(personnelId);
		
		if(personnel != null) {
			final Map<String, String> errors = documentFormValidator.validate(documentForm);
			
			if(errors.isEmpty()) {
				final Document document = new Document();
				
				document.setPersonnel(personnel);
				document.setDocumentType(documentForm.getDocumentType());
				setDocument(document, documentForm);
				
				result = new ResultBean();
				result.setSuccess(documentService.insert(document) != null);
				if(result.getSuccess()) {
					saveDocumentFile(document, in, info);
					result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " added document - " 
									+ Html.text(Color.BLUE, 
											document.getDocumentType().getDisplayName()) + ". Thank you."));
				} else {
					result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
				}
			} else {
				result = new ResultBean(Boolean.FALSE, "");
				result.addToExtras("errors", errors);
			}
		} else {
			result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load personnel. Please refresh the page."));
		}
		
		return result;
	}
	
	@Override
	public ResultBean updateDocument(DocumentFormBean documentForm, InputStream in, FormDataContentDisposition info)
			throws IOException {
		final ResultBean result;
		final Map<String, String> errors = documentFormValidator.validate(documentForm);
		
		if(errors.isEmpty()) {
			final Document document = documentService.find(documentForm.getId());
			
			setDocument(document, documentForm);
			
			result = new ResultBean();
			result.setSuccess(documentService.update(document));
			if(result.getSuccess()) {
				if(info.getSize() != 0l) saveDocumentFile(document, in, info);
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " updated document - " 
								+ Html.text(Color.BLUE,
										document.getDocumentType().getDisplayName()) + ". Thank you."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, "");
			result.addToExtras("errors", errors);
		}
		
		return result;
	}

	@Override
	public ResultBean removeDocument(Long documentId) {
		final ResultBean result;
		
		final Document document = documentService.find(documentId);
		if(document != null) {
			result = new ResultBean();
			
			result.setSuccess(documentService.delete(documentId));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " removed document - " 
						+ Html.text(Color.BLUE, 
								document.getDocumentType().getDisplayName()) + ". Thank you."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load document. Please refresh the page."));
		}
		
		return result;
	}
	
	private void saveDocumentFile(Document document, InputStream in, FormDataContentDisposition info) throws IOException {
		final String fileName = UUID.randomUUID().toString() + "." + StringHelper.getFileExtension(info.getFileName());
		
		File file = new File(fileConstants.getPersonnelDocumentHome() + fileName);
		if(file.getParentFile() != null) file.getParentFile().mkdirs();
		
		if(!file.exists()) {
			Files.copy(in, file.toPath());
				
			document.setFileName(fileName);
			
			documentService.update(document);
		} else {
			saveDocumentFile(document, in, info);
		}
	}
	
	private void setDocument(Document document, DocumentFormBean documentForm) {
		document.setExpirationDate(documentForm.getExpirationDate());
		document.setIdentificationNumber(documentForm.getIdentificationNumber());
		document.setRemarks(documentForm.getRemarks());
	}
}
