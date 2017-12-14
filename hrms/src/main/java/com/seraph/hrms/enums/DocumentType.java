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
	
	ATM_APPLICATION("ATM Application"),
	
	BARANGAY_CLEARANCE("Barangay Clearance"),
	
	BIR_TIN("BIR/TIN"),
	
	BIRTH_CERTIFICATE("Birth Certificate(NSO)"),
	
	CERTIFICATE_OF_EMPLOYMENT("Certificate of Employment"),
	
	COURT_CLEARANCE("Court/RID Clearance"),
	
	DIPLOMA_TOR("Diploma or TOR"),
	
	DRUG_TEST("Drug Test"),
	
	INSURANCE("Insurance"),
	
	MEDICAL_CERTIFICATE("Medical Certificate"),
	
	NBI_CLEARANCE("NBI Clearance"),
	
	NP_CLEARANCE("NP Clearance"),
	
	PAG_IBIG("Pag-ibig"),
	
	PHILHEALTH("Philhealth"),
	
	PMRF("PMRF"),
	
	POLICE_CLEARANCE("Police Clearance"),
	
	SSS("SSS(E-1)"),
	
	TRAINING_CERTIFICATE("Training Certificate"),
	
	WHOLE_BODY_PICTURE("Whole Body Picture(In Uniform)");
	
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
