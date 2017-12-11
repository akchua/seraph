package com.seraph.hrms.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DocumentType {

	PS_LICENSE("P.S. License"),
	
	INSURANCE("Insurance"),
	
	TRAINING_CERTIFICATE("Training Certificate"),
	
	NBI_CLEARANCE("NBI Clearance"),
	
	DRUG_TEST("Drug Test"),
	
	MEDICAL_CERTIFICATE("Medical Certificate"),
	
	DIPLOMA_TOR("Diploma or TOR"),
	
	BIRTH_CERTIFICATE("Birth Certificate(NSO)"),
	
	CERTIFICATE_OF_EMPLOYMENT("Certificate of Employment"),

	WHOLE_BODY_PICTURE("Whole Body Picture(In Uniform)"),
	
	POLICE_CLEARANCE("Police Clearance"),
	
	COURT_CLEARANCE("Court/RID Clearance"),
	
	SSS("SSS(E-1)"),
	
	BIR_TIN("BIR/TIN"),
	
	PHILHEALTH("Philhealth"),
	
	PAG_IBIG("Pag-ibig"),
	
	NP_CLEARANCE("NP Clearance"),
	
	ATM_APPLICATION("ATM Application"),
	
	PMRF("PMRF");
	
	private final String displayName;
	
	DocumentType(final String displayName) {
		this.displayName = displayName;
	}
	
	public String getName() {
		return toString();
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
