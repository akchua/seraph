package com.seraph.hrms.database.service.impl;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seraph.hrms.database.dao.UserDAO;
import com.seraph.hrms.database.entity.User;
import com.seraph.hrms.database.service.UserService;
import com.seraph.hrms.objects.ObjectList;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Nov 30, 2017
 */
@Service
public class UserServiceImpl
		extends AbstractService<User, Long, UserDAO> 
		implements UserService {

	@Autowired
	protected UserServiceImpl(UserDAO dao) {
		super(dao);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return dao.findByUsernameAndPassword(username, password);
	}

	@Override
	public boolean isExistByUsername(String username) {
		return dao.findByUsername(username) != null;
	}

	@Override
	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}
	
	@Override
	public User findByUsernameOrEmail(String username, String emailAddress) {
		return dao.findByUsernameOrEmail(username, emailAddress);
	}

	@Override
	public ObjectList<User> findAllOrderByNameAndUserType(int pageNumber, int resultsPerPage, String searchKey) {
		return dao.findAllWithPagingAndOrder(pageNumber, resultsPerPage, searchKey, new Order[] { Order.asc("userType"), Order.asc("lastName"), Order.asc("firstName")  });
	}
}
