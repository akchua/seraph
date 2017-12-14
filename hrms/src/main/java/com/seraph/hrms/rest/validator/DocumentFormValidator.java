package com.seraph.hrms.rest.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.seraph.hrms.beans.DocumentFormBean;
import com.seraph.hrms.enums.DocumentType;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@Component
public class DocumentFormValidator extends AbstractFormValidator<DocumentFormBean> {

	@Override
	public Map<String, String> validate(DocumentFormBean documentForm) {
		final Map<String, String> errors = new HashMap<String, String>();
		
		String temp = "";
		
		temp = validateDocumentType(documentForm.getDocumentType());
		if(!temp.isEmpty()) errors.put("documentType", temp);
		temp = validateExpirationDate(documentForm.getExpirationDate());
		if(!temp.isEmpty()) errors.put("expirationDate", temp);
		temp = validateIdentificationNumber(documentForm.getIdentificationNumber(), documentForm.getDocumentType());
		if(!temp.isEmpty()) errors.put("identificationNumber", temp);
		temp = validateRemarks(documentForm.getRemarks());
		if(!temp.isEmpty()) errors.put("remarks", temp);
		
		return errors;
	}
	
	private String validateDocumentType(DocumentType documentType) {
		return notNull(documentType);
	}
	
	private String validateExpirationDate(Date expirationDate) {
		String ret = notNull(expirationDate);
		if(!ret.isEmpty()) return ret;
		
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		Calendar exp = Calendar.getInstance();
		exp.setTime(expirationDate);
		
		if(exp.before(now)) {
			ret = "Expiration date already passed.";
		}
		
		return ret;
	}
	
	private String validateIdentificationNumber(String identificationNumber, DocumentType documentType) {
		return documentType.equals(DocumentType.ATM_APPLICATION) ? validateString(identificationNumber, 10, 10) : validateStringNull(identificationNumber, 0, 20);
	}
	
	private String validateRemarks(String remarks) {
		return validateStringNull(remarks, 0, 40);
	}
}
