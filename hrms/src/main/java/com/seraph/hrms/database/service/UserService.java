package com.seraph.hrms.database.service;

import com.seraph.hrms.database.entity.User;
import com.seraph.hrms.database.prototype.UserPrototype;
import com.seraph.hrms.objects.ObjectList;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Nov 30, 2017
 */
public interface UserService extends Service<User, Long>, UserPrototype {

	boolean isExistByUsername(String username);
	
	ObjectList<User> findAllOrderByNameAndUserType(int pageNumber, int resultsPerPage, String searchKey);
}
