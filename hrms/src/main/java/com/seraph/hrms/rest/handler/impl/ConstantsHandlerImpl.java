package com.seraph.hrms.rest.handler.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seraph.hrms.constants.SystemConstants;
import com.seraph.hrms.enums.CivilStatus;
import com.seraph.hrms.enums.DocumentType;
import com.seraph.hrms.enums.Gender;
import com.seraph.hrms.enums.PersonnelPosition;
import com.seraph.hrms.enums.UserType;
import com.seraph.hrms.rest.handler.ConstantsHandler;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   6 Dec 2017
 */
@Component
public class ConstantsHandlerImpl implements ConstantsHandler {

	@Autowired
	private SystemConstants systemConstants;
	
	@Override
	public String getVersion() {
		return systemConstants.getVersion();
	}

	@Override
	public List<UserType> getUserTypeList() {
		return Stream.of(UserType.values()).collect(Collectors.toList());
	}

	@Override
	public List<Gender> getGenderList() {
		return Stream.of(Gender.values()).collect(Collectors.toList());
	}

	@Override
	public List<PersonnelPosition> getPersonnelPositionList() {
		return Stream.of(PersonnelPosition.values()).collect(Collectors.toList());
	}

	@Override
	public List<CivilStatus> getCivilStatusList() {
		return Stream.of(CivilStatus.values()).collect(Collectors.toList());
	}

	@Override
	public List<DocumentType> getDocumentTypeList() {
		return Stream.of(DocumentType.values()).collect(Collectors.toList());
	}
}
