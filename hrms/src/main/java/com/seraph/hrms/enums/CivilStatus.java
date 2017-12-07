package com.seraph.hrms.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   6 Dec 2017
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CivilStatus {

	SINGLE("Single"),
	
	MARRIED("Married"),
	
	SEPARATED("Separated"),
	
	WIDOWED("Widowed");
	
	private final String displayName;
	
	CivilStatus(final String displayName) {
		this.displayName = displayName;
	}
	
	public String getName() {
		return toString();
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
