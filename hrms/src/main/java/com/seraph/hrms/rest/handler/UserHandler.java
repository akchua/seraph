package com.seraph.hrms.rest.handler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.seraph.hrms.beans.PasswordFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.beans.SettingsFormBean;
import com.seraph.hrms.beans.UserFormBean;
import com.seraph.hrms.database.entity.User;
import com.seraph.hrms.database.entity.UserImage;
import com.seraph.hrms.enums.UserType;
import com.seraph.hrms.objects.ObjectList;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Nov 30, 2017
 */
public interface UserHandler {
	
	User getUser(Long userId);
	
	File findUserImageByFileName(String fileName);
	
	ObjectList<User> getUserObjectList(Integer pageNumber, String searchKey);
	
	List<UserImage> getUserImageList(Long userId);
	
	User getUserByUsernameOrEmail(String username, String emailAddress);
	
	ResultBean createUser(UserFormBean userForm);
	
	ResultBean saveUserImage(Long userId, InputStream in, FormDataContentDisposition info) throws IOException;
	
	ResultBean updateUser(UserFormBean userForm);
	
	ResultBean setUserImageAsThumbnail(Long userImageId);
	
	ResultBean removeUser(Long userId);
	
	ResultBean removeUserImage(Long userImageId);
	
	ResultBean changePassword(PasswordFormBean passwordForm);
	
	ResultBean resetPassword(Long userId);
	
	ResultBean changeSettings(SettingsFormBean settingsForm);
	
	List<UserType> getUserTypeList();
}
