package com.seraph.hrms.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Dec 17, 2016
 */
@Component
public class FileConstants {

	private final String fileHome;
	
	private final String userImageHome;
	
	private final String personnelImageHome;
	
	private final String personnelDocumentHome;
	
	private final String imageDefaultFileName;
	
	@Autowired
	public FileConstants(@Value("${file.home}") String fileHome,
						@Value("${file.image.defaultFileName}") String imageDefaultFileName) {
		this.fileHome = fileHome;
		this.userImageHome = fileHome + "program_data/user_image/";
		this.personnelImageHome = fileHome + "program_data/personnel_image/";
		this.personnelDocumentHome = fileHome + "program_data/personnel_document/";
		this.imageDefaultFileName = imageDefaultFileName;
	}

	public String getFileHome() {
		return fileHome;
	}

	public String getUserImageHome() {
		return userImageHome;
	}
	
	public String getPersonnelImageHome() {
		return personnelImageHome;
	}
	
	public String getPersonnelDocumentHome() {
		return personnelDocumentHome;
	}

	public String getImageDefaultFileName() {
		return imageDefaultFileName;
	}
}
