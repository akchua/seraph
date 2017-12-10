package com.seraph.hrms.beans;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   10 Dec 2017
 */
public class EmploymentFormBean extends FormBean {

	private String companyName;
	
	private String position;
	
	private Float salary;
	
	private Integer years;
	
	private String causeOfDischarge;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public String getCauseOfDischarge() {
		return causeOfDischarge;
	}

	public void setCauseOfDischarge(String causeOfDischarge) {
		this.causeOfDischarge = causeOfDischarge;
	}
}
