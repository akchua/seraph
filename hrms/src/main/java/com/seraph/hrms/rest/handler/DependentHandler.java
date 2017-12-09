package com.seraph.hrms.rest.handler;

import java.util.List;

import com.seraph.hrms.beans.DependentFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Dependent;
import com.seraph.hrms.objects.ObjectList;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   9 Dec 2017
 */
public interface DependentHandler {

	Dependent getDependent(Long dependentId);

	ObjectList<Dependent> getDependentObjectList(Integer pageNumber, Long personnelId, String searchKey);
	
	List<Dependent> getDependentListByPersonnel(Long personnelId);
	
	ResultBean createDependent(DependentFormBean dependentForm, Long personnelId);
	
	ResultBean updateDependent(DependentFormBean dependentForm);
	
	ResultBean removeDependent(Long dependentId);
}
