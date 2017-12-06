package com.seraph.hrms.rest.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seraph.hrms.constants.SystemConstants;
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
}
