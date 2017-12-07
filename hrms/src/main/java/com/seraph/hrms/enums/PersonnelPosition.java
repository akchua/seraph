package com.seraph.hrms.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   6 Dec 2017
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PersonnelPosition {

	GUARD("Security Guard"),
	
	OFFICER("Security Officer");
	
	private final String displayName;
	
	PersonnelPosition(final String displayName) {
		this.displayName = displayName;
	}
	
	public String getName() {
		return toString();
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
