package com.seraph.hrms.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   6 Dec 2017
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PersonnelPosition {

	GUARD("Security Guard", "SG"),
	
	OFFICER("Security Officer", "SO");
	
	private final String displayName;
	
	private final String shortHand;
	
	PersonnelPosition(final String displayName, final String shortHand) {
		this.displayName = displayName;
		this.shortHand = shortHand;
	}
	
	public String getName() {
		return toString();
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getShortHand() {
		return shortHand;
	}
}
