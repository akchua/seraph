package com.seraph.hrms.rest.handler;

import com.seraph.hrms.beans.PersonnelFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Personnel;
import com.seraph.hrms.objects.ObjectList;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   6 Dec 2017
 */
public interface PersonnelHandler {

	Personnel getPersonnel(Long personnelId);
	
	ObjectList<Personnel> getPersonnelObjectList(Integer pageNumber, String searchKey);
	
	ResultBean createPersonnel(PersonnelFormBean personnelForm);
	
	ResultBean updatePersonnel(PersonnelFormBean personnelForm);
	
	ResultBean removePersonnel(Long personnelId);
}
