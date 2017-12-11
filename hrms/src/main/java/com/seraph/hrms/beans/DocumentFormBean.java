package com.seraph.hrms.beans;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seraph.hrms.deserializer.json.DateDeserializer;
import com.seraph.hrms.enums.DocumentType;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
public class DocumentFormBean extends FormBean {

	private DocumentType documentType;
	
	@JsonDeserialize(using = DateDeserializer.class)
	private Date expirationDate;
	
	private String identificationNumber;
	
	private String remarks;

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
