package com.seraph.hrms.database.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.seraph.hrms.database.dao.UserDAO;
import com.seraph.hrms.database.entity.User;
import com.seraph.hrms.objects.ObjectList;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Nov 30, 2017
 */
@Repository
public class UserDAOImpl
		extends AbstractDAO<User, Long> 
		implements UserDAO {
	
	@Override
	public ObjectList<User> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey) {
		return findAllWithPagingAndOrder(pageNumber, resultsPerPage, searchKey, null);
	}
	
	@Override
	public ObjectList<User> findAllWithPagingAndOrder(int pageNumber, int resultsPerPage, String searchKey,
			Order[] orders) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		
		if(StringUtils.isNotBlank(searchKey))
		{
			for(String s : searchKey.split("\\s+")) {
				conjunction.add(Restrictions.disjunction()
						.add(Restrictions.ilike("firstName", s, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("lastName", s, MatchMode.ANYWHERE)));
			}
		}
		
		return findAllByCriterion(pageNumber, resultsPerPage, null, null, null, orders, conjunction);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.eq("username", username));
		conjunction.add(Restrictions.eq("password", password));
		
		return findUniqueResult(null, null, null, conjunction);
	}

	@Override
	public User findByUsername(String username) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.eq("username", username));
		
		return findUniqueResult(null, null, null, conjunction);
	}

	@Override
	public User findByUsernameOrEmail(String username, String emailAddress) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.disjunction()
				.add(Restrictions.eq("username", username))
				.add(Restrictions.eq("emailAddress", emailAddress)));
		
		return findUniqueResult(null, null, null, conjunction);
	}
}
