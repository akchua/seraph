package com.seraph.hrms.rest.handler.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seraph.hrms.constants.FileConstants;
import com.seraph.hrms.rest.handler.FileHandler;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@Component
public class FileHandlerImpl implements FileHandler {

	@Autowired
	private FileConstants fileConstants;
	
	@Override
	public File findDocumentByFileName(String fileName) {
		return new File(fileConstants.getPersonnelDocumentHome() + fileName);
	}
}
