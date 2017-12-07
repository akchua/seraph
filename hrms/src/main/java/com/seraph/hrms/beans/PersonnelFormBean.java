package com.seraph.hrms.beans;

import java.util.Date;

import com.seraph.hrms.enums.CivilStatus;
import com.seraph.hrms.enums.Gender;
import com.seraph.hrms.enums.PersonnelPosition;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   6 Dec 2017
 */
public class PersonnelFormBean extends FormBean {

private PersonnelPosition personnelPosition;
	
	private String firstName;
	
	private String lastName;
	
	private String middleName;
	
	private String motherMaidenName;
	
	private String cityAddress;
	
	private String provincialAddress;
	
	private String birthplace;
	
	private Date birthdate;
	
	private String height;
	
	private String weight;
	
	private Gender gender;
	
	private CivilStatus civilStatus;
	
	private String hairColor;
	
	private String eyeColor;
	
	private String complexion;
	
	private String identifyingMarks;
	
	private String dialectsSpoken;
	
	private String religion;
	
	private String nameOfSpouse;
	
	private String occupationOfSpouse;
	
	private String nameOfFather;
	
	private String occupationOfFather;
	
	private String nameOfMother;
	
	private String occupationOfMother;
	
	private String emergencyContactPerson;
	
	private String contactPersonRelation;
	
	private String contactPersonAddress;
	
	private String recommendedBy;
	
	private String highSchool;
	
	private String highSchoolGradYear;
	
	private String vocationalSchool;
	
	private String vocationalSchoolGradYear;
	
	private String college;
	
	private String collegeDegree;
	
	private String collegeGradYear;
	
	private String lastSecuritySchool;
	
	private String organization;
	
	private String specialSkills;
	
	private String hobbies;

	public PersonnelPosition getPersonnelPosition() {
		return personnelPosition;
	}

	public void setPersonnelPosition(PersonnelPosition personnelPosition) {
		this.personnelPosition = personnelPosition;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMotherMaidenName() {
		return motherMaidenName;
	}

	public void setMotherMaidenName(String motherMaidenName) {
		this.motherMaidenName = motherMaidenName;
	}

	public String getCityAddress() {
		return cityAddress;
	}

	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}

	public String getProvincialAddress() {
		return provincialAddress;
	}

	public void setProvincialAddress(String provincialAddress) {
		this.provincialAddress = provincialAddress;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public CivilStatus getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(CivilStatus civilStatus) {
		this.civilStatus = civilStatus;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public String getComplexion() {
		return complexion;
	}

	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}

	public String getIdentifyingMarks() {
		return identifyingMarks;
	}

	public void setIdentifyingMarks(String identifyingMarks) {
		this.identifyingMarks = identifyingMarks;
	}

	public String getDialectsSpoken() {
		return dialectsSpoken;
	}

	public void setDialectsSpoken(String dialectsSpoken) {
		this.dialectsSpoken = dialectsSpoken;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getNameOfSpouse() {
		return nameOfSpouse;
	}

	public void setNameOfSpouse(String nameOfSpouse) {
		this.nameOfSpouse = nameOfSpouse;
	}

	public String getOccupationOfSpouse() {
		return occupationOfSpouse;
	}

	public void setOccupationOfSpouse(String occupationOfSpouse) {
		this.occupationOfSpouse = occupationOfSpouse;
	}

	public String getNameOfFather() {
		return nameOfFather;
	}

	public void setNameOfFather(String nameOfFather) {
		this.nameOfFather = nameOfFather;
	}

	public String getOccupationOfFather() {
		return occupationOfFather;
	}

	public void setOccupationOfFather(String occupationOfFather) {
		this.occupationOfFather = occupationOfFather;
	}

	public String getNameOfMother() {
		return nameOfMother;
	}

	public void setNameOfMother(String nameOfMother) {
		this.nameOfMother = nameOfMother;
	}

	public String getOccupationOfMother() {
		return occupationOfMother;
	}

	public void setOccupationOfMother(String occupationOfMother) {
		this.occupationOfMother = occupationOfMother;
	}

	public String getEmergencyContactPerson() {
		return emergencyContactPerson;
	}

	public void setEmergencyContactPerson(String emergencyContactPerson) {
		this.emergencyContactPerson = emergencyContactPerson;
	}

	public String getContactPersonRelation() {
		return contactPersonRelation;
	}

	public void setContactPersonRelation(String contactPersonRelation) {
		this.contactPersonRelation = contactPersonRelation;
	}

	public String getContactPersonAddress() {
		return contactPersonAddress;
	}

	public void setContactPersonAddress(String contactPersonAddress) {
		this.contactPersonAddress = contactPersonAddress;
	}

	public String getRecommendedBy() {
		return recommendedBy;
	}

	public void setRecommendedBy(String recommendedBy) {
		this.recommendedBy = recommendedBy;
	}

	public String getHighSchool() {
		return highSchool;
	}

	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}

	public String getHighSchoolGradYear() {
		return highSchoolGradYear;
	}

	public void setHighSchoolGradYear(String highSchoolGradYear) {
		this.highSchoolGradYear = highSchoolGradYear;
	}

	public String getVocationalSchool() {
		return vocationalSchool;
	}

	public void setVocationalSchool(String vocationalSchool) {
		this.vocationalSchool = vocationalSchool;
	}

	public String getVocationalSchoolGradYear() {
		return vocationalSchoolGradYear;
	}

	public void setVocationalSchoolGradYear(String vocationalSchoolGradYear) {
		this.vocationalSchoolGradYear = vocationalSchoolGradYear;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getCollegeDegree() {
		return collegeDegree;
	}

	public void setCollegeDegree(String collegeDegree) {
		this.collegeDegree = collegeDegree;
	}

	public String getCollegeGradYear() {
		return collegeGradYear;
	}

	public void setCollegeGradYear(String collegeGradYear) {
		this.collegeGradYear = collegeGradYear;
	}

	public String getLastSecuritySchool() {
		return lastSecuritySchool;
	}

	public void setLastSecuritySchool(String lastSecuritySchool) {
		this.lastSecuritySchool = lastSecuritySchool;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getSpecialSkills() {
		return specialSkills;
	}

	public void setSpecialSkills(String specialSkills) {
		this.specialSkills = specialSkills;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
}
