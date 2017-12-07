package com.seraph.hrms.database.service.impl;import org.hibernate.criterion.Order;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import com.seraph.hrms.database.dao.PersonnelDAO;import com.seraph.hrms.database.entity.Personnel;import com.seraph.hrms.database.service.PersonnelService;import com.seraph.hrms.objects.ObjectList;/** * @author  Adrian Jasper K. Chua * @version 1.0 * @since   6 December 2017 */@Servicepublic class PersonnelServiceImpl		extends AbstractService<Personnel, Long, PersonnelDAO> 		implements PersonnelService {	@Autowired	protected PersonnelServiceImpl(PersonnelDAO dao) {		super(dao);	}	@Override	public ObjectList<Personnel> findAllOrderByNameAndPosition(int pageNumber, int resultsPerPage, String searchKey) {		return dao.findAllWithPagingAndOrder(pageNumber, resultsPerPage, searchKey, new Order[] { Order.asc("personnelPosition"), Order.asc("lastName"), Order.asc("firstName")  });	}}
