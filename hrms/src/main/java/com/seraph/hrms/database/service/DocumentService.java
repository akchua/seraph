package com.seraph.hrms.database.service;import com.seraph.hrms.database.entity.Document;import com.seraph.hrms.database.prototype.DocumentPrototype;import com.seraph.hrms.objects.ObjectList;/** * @author  Adrian Jasper K. Chua * @version 1.0 * @since   11 December 2017 */public interface DocumentService extends Service<Document, Long>, DocumentPrototype {		ObjectList<Document> findAllByPersonnelWithPagingOrderByType(int pageNumber, int resultsPerPage, Long personnelId, String searchKey);}