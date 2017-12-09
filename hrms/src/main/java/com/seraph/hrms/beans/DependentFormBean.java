package com.seraph.hrms.beans;

import com.seraph.hrms.enums.Gender;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   9 Dec 2017
 */
public class DependentFormBean extends FormBean {

	private String lastName;
	
	private String firstName;
	
	private Integer age;
	
	private Gender gender;
	
	private String relationship;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
}
