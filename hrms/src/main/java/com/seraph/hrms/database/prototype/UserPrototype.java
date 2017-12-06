package com.seraph.hrms.database.prototype;

import com.seraph.hrms.database.entity.User;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Nov 30, 2017
 */
public interface UserPrototype extends Prototype<User, Long> {
	
	User findByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
	
	User findByUsernameOrEmail(String username, String emailAddress);
};
