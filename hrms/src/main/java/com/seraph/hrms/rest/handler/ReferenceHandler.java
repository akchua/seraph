package com.seraph.hrms.rest.handler;

import java.util.List;

import com.seraph.hrms.beans.ReferenceFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Reference;
import com.seraph.hrms.objects.ObjectList;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
public interface ReferenceHandler {

	Reference getReference(Long referenceId);

	ObjectList<Reference> getReferenceObjectList(Integer pageNumber, Long personnelId, String searchKey);
	
	List<Reference> getReferenceListByPersonnel(Long personnelId);
	
	ResultBean createReference(ReferenceFormBean referenceForm, Long personnelId);
	
	ResultBean updateReference(ReferenceFormBean referenceForm);
	
	ResultBean removeReference(Long referenceId);
}
