package com.seraph.hrms.rest.handler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.seraph.hrms.beans.PersonnelFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Personnel;
import com.seraph.hrms.database.entity.PersonnelImage;
import com.seraph.hrms.objects.ObjectList;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   6 Dec 2017
 */
public interface PersonnelHandler {

	Personnel getPersonnel(Long personnelId);
	
	File findPersonnelImageByFileName(String fileName);
	
	ObjectList<Personnel> getPersonnelObjectList(Integer pageNumber, String searchKey);
	
	List<PersonnelImage> getPersonnelImageList(Long personnelId);
	
	ResultBean createPersonnel(PersonnelFormBean personnelForm);
	
	ResultBean savePersonnelImage(Long personnelId, InputStream in, FormDataContentDisposition info) throws IOException;
	
	ResultBean updatePersonnel(PersonnelFormBean personnelForm);
	
	ResultBean setPersonnelImageAsThumbnail(Long personnelImageId);
	
	ResultBean removePersonnel(Long personnelId);
	
	ResultBean removePersonnelImage(Long personnelImageId);
}
