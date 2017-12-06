package com.seraph.hrms.rest.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seraph.hrms.beans.UserBean;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Nov 30, 2017
 */
public interface SecurityHandler {

	void logout(HttpServletRequest request, HttpServletResponse response);
	
	UserBean getUser();
}
