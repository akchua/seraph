package com.seraph.hrms.database.service.impl;import org.hibernate.criterion.Order;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import com.seraph.hrms.database.dao.DocumentDAO;import com.seraph.hrms.database.entity.Document;import com.seraph.hrms.database.service.DocumentService;import com.seraph.hrms.objects.ObjectList;/** * @author  Adrian Jasper K. Chua * @version 1.0 * @since   11 December 2017 */@Servicepublic class DocumentServiceImpl		extends AbstractService<Document, Long, DocumentDAO> 		implements DocumentService {	@Autowired	protected DocumentServiceImpl(DocumentDAO dao) {		super(dao);	}	@Override	public ObjectList<Document> findAllByPersonnelWithPagingOrderByType(int pageNumber, int resultsPerPage,			Long personnelId, String searchKey) {		return dao.findAllWithPagingAndOrder(pageNumber, resultsPerPage, personnelId, searchKey, new Order[] { Order.asc("documentType")});	}}
