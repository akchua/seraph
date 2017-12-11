package com.seraph.hrms.database.dao.impl;import java.util.List;import org.apache.commons.lang3.StringUtils;import org.hibernate.criterion.Junction;import org.hibernate.criterion.MatchMode;import org.hibernate.criterion.Order;import org.hibernate.criterion.Restrictions;import org.springframework.stereotype.Repository;import com.seraph.hrms.database.dao.DependentDAO;import com.seraph.hrms.database.entity.Dependent;import com.seraph.hrms.objects.ObjectList;/** * @author  Adrian Jasper K. Chua * @version 1.0 * @since   6 December 2017 */@Repositorypublic class DependentDAOImpl		extends AbstractDAO<Dependent, Long> 		implements DependentDAO {	@Override	public ObjectList<Dependent> findAllWithPaging(int pageNumber, int resultsPerPage, Long personnelId,			String searchKey) {		return findAllWithPagingAndOrder(pageNumber, resultsPerPage, personnelId, searchKey, null);	}	@Override	public ObjectList<Dependent> findAllWithPagingAndOrder(int pageNumber, int resultsPerPage, Long personnelId,			String searchKey, Order[] orders) {		final Junction conjunction = Restrictions.conjunction();		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));		conjunction.add(Restrictions.eq("personnel.id", personnelId));				if(StringUtils.isNotBlank(searchKey))		{			for(String s : searchKey.split("\\s+")) {				conjunction.add(Restrictions.disjunction()						.add(Restrictions.ilike("firstName", s, MatchMode.ANYWHERE))						.add(Restrictions.ilike("lastName", s, MatchMode.ANYWHERE)));			}		}				return findAllByCriterion(pageNumber, resultsPerPage, null, null, null, orders, conjunction);	}	@Override	public List<Dependent> findAllByPersonnelWithOrder(Long personnelId, Order[] orders) {		final Junction conjunction = Restrictions.conjunction();		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));		conjunction.add(Restrictions.eq("personnel.id", personnelId));				return findAllByCriterionList(null, null, null, orders, conjunction);	}}
