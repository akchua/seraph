package com.seraph.hrms.rest.handler;

import java.util.List;

import com.seraph.hrms.enums.CivilStatus;
import com.seraph.hrms.enums.DocumentType;
import com.seraph.hrms.enums.Gender;
import com.seraph.hrms.enums.PersonnelPosition;
import com.seraph.hrms.enums.UserType;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   6 Dec 2017
 */
public interface ConstantsHandler {

	String getVersion();
	
	List<UserType> getUserTypeList();
	
	List<Gender> getGenderList();
	
	List<PersonnelPosition> getPersonnelPositionList();
	
	List<CivilStatus> getCivilStatusList();
	
	List<DocumentType> getDocumentTypeList();
}
