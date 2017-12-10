package com.seraph.hrms.rest.handler;

import java.util.List;

import com.seraph.hrms.beans.EmploymentFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Employment;
import com.seraph.hrms.objects.ObjectList;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   10 Dec 2017
 */
public interface EmploymentHandler {

	Employment getEmployment(Long employmentId);

	ObjectList<Employment> getEmploymentObjectList(Integer pageNumber, Long personnelId, String searchKey);
	
	List<Employment> getEmploymentListByPersonnel(Long personnelId);
	
	ResultBean createEmployment(EmploymentFormBean employmentForm, Long personnelId);
	
	ResultBean updateEmployment(EmploymentFormBean employmentForm);
	
	ResultBean removeEmployment(Long employmentId);
}
