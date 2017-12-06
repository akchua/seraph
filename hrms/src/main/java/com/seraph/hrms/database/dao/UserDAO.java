package com.seraph.hrms.database.dao;

import org.hibernate.criterion.Order;

import com.seraph.hrms.database.entity.User;
import com.seraph.hrms.database.prototype.UserPrototype;
import com.seraph.hrms.objects.ObjectList;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Nov 30, 2017
 */
public interface UserDAO extends DAO<User, Long>, UserPrototype {

	ObjectList<User> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey);
	
	ObjectList<User> findAllWithPagingAndOrder(int pageNumber, int resultsPerPage, String searchKey, Order[] orders);
}
