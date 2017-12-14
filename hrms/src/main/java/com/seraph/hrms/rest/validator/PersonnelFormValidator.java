package com.seraph.hrms.rest.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.seraph.hrms.beans.PersonnelFormBean;
import com.seraph.hrms.enums.CivilStatus;
import com.seraph.hrms.enums.Gender;
import com.seraph.hrms.enums.PersonnelPosition;

/**
 * @author Adrian Jasper K. Chua
 * @version 1.0
 * @since 6 Dec 2017
 */
@Component
public class PersonnelFormValidator extends AbstractFormValidator<PersonnelFormBean> {

	@Override
	public Map<String, String> validate(PersonnelFormBean personnelForm) {
		final Map<String, String> errors = new HashMap<String, String>();

		String temp = "";

		temp = validatePersonnelPosition(personnelForm.getPersonnelPosition());
		if (!temp.isEmpty())
			errors.put("personnelPosition", temp);
		temp = validateFirstName(personnelForm.getFirstName());
		if (!temp.isEmpty())
			errors.put("firstName", temp);
		temp = validateLastName(personnelForm.getLastName());
		if (!temp.isEmpty())
			errors.put("lastName", temp);
		temp = validateMiddleName(personnelForm.getMiddleName());
		if (!temp.isEmpty())
			errors.put("middleName", temp);
		temp = validateContactNumber(personnelForm.getContactNumber());
		if (!temp.isEmpty())
			errors.put("contactNumber", temp);
		temp = validateMotherMaidenName(personnelForm.getMotherMaidenName());
		if (!temp.isEmpty())
			errors.put("motherMaidenName", temp);
		temp = validateCityAddress(personnelForm.getCityAddress());
		if (!temp.isEmpty())
			errors.put("cityAddress", temp);
		temp = validateProvincialAddress(personnelForm.getProvincialAddress());
		if (!temp.isEmpty())
			errors.put("provincialAddress", temp);
		temp = validateBirthplace(personnelForm.getBirthplace());
		if (!temp.isEmpty())
			errors.put("birthplace", temp);
		temp = validateBirthdate(personnelForm.getBirthdate());
		if (!temp.isEmpty())
			errors.put("birthdate", temp);
		temp = validateHeight(personnelForm.getHeight());
		if (!temp.isEmpty())
			errors.put("height", temp);
		temp = validateWeight(personnelForm.getWeight());
		if (!temp.isEmpty())
			errors.put("weight", temp);
		temp = validateGender(personnelForm.getGender());
		if (!temp.isEmpty())
			errors.put("gender", temp);
		temp = validateCivilStatus(personnelForm.getCivilStatus());
		if (!temp.isEmpty())
			errors.put("civilStatus", temp);
		temp = validateHairColor(personnelForm.getHairColor());
		if (!temp.isEmpty())
			errors.put("hairColor", temp);
		temp = validateEyeColor(personnelForm.getEyeColor());
		if (!temp.isEmpty())
			errors.put("eyeColor", temp);
		temp = validateComplexion(personnelForm.getComplexion());
		if (!temp.isEmpty())
			errors.put("complexion", temp);
		temp = validateIdentifyingMarks(personnelForm.getIdentifyingMarks());
		if (!temp.isEmpty())
			errors.put("identifyingMarks", temp);
		temp = validateDialectsSpoken(personnelForm.getDialectsSpoken());
		if (!temp.isEmpty())
			errors.put("dialectsSpoken", temp);
		temp = validateReligion(personnelForm.getReligion());
		if (!temp.isEmpty())
			errors.put("religion", temp);
		temp = validateNameOfSpouse(personnelForm.getNameOfSpouse(), personnelForm.getCivilStatus());
		if (!temp.isEmpty())
			errors.put("nameOfSpouse", temp);
		temp = validateOccupationOfSpouse(personnelForm.getOccupationOfSpouse(), personnelForm.getCivilStatus());
		if (!temp.isEmpty())
			errors.put("occupationOfSpouse", temp);
		temp = validateNameOfFather(personnelForm.getNameOfFather());
		if (!temp.isEmpty())
			errors.put("nameOfFather", temp);
		temp = validateOccupationOfFather(personnelForm.getOccupationOfFather());
		if (!temp.isEmpty())
			errors.put("occupationOfFather", temp);
		temp = validateNameOfMother(personnelForm.getNameOfMother());
		if (!temp.isEmpty())
			errors.put("nameOfMother", temp);
		temp = validateOccupationOfMother(personnelForm.getOccupationOfMother());
		if (!temp.isEmpty())
			errors.put("occupationOfMother", temp);
		temp = validateEmergencyContactPerson(personnelForm.getEmergencyContactPerson());
		if (!temp.isEmpty())
			errors.put("emergencyContactPerson", temp);
		temp = validateContactPersonRelation(personnelForm.getContactPersonRelation());
		if (!temp.isEmpty())
			errors.put("contactPersonRelation", temp);
		temp = validateContactPersonAddress(personnelForm.getContactPersonAddress());
		if (!temp.isEmpty())
			errors.put("contactPersonAddress", temp);
		temp = validateContactPersonNumber(personnelForm.getContactPersonNumber());
		if (!temp.isEmpty())
			errors.put("contactPersonNumber", temp);
		temp = validateRecommendedBy(personnelForm.getRecommendedBy());
		if (!temp.isEmpty())
			errors.put("recommendedBy", temp);
		temp = validateHighSchool(personnelForm.getHighSchool());
		if (!temp.isEmpty())
			errors.put("highSchool", temp);
		temp = validateHighSchoolGradYear(personnelForm.getHighSchoolGradYear());
		if (!temp.isEmpty())
			errors.put("highSchoolGradYear", temp);
		temp = validateVocationalSchool(personnelForm.getVocationalSchool());
		if (!temp.isEmpty())
			errors.put("vocationalSchool", temp);
		temp = validateVocationalSchoolGradYear(personnelForm.getVocationalSchoolGradYear());
		if (!temp.isEmpty())
			errors.put("vocationalSchoolGradYear", temp);
		temp = validateCollege(personnelForm.getCollege());
		if (!temp.isEmpty())
			errors.put("college", temp);
		temp = validateCollegeDegree(personnelForm.getCollegeDegree());
		if (!temp.isEmpty())
			errors.put("collegeDegree", temp);
		temp = validateCollegeGradYear(personnelForm.getCollegeGradYear());
		if (!temp.isEmpty())
			errors.put("collegeGradYear", temp);
		temp = validateLastSecuritySchool(personnelForm.getLastSecuritySchool());
		if (!temp.isEmpty())
			errors.put("lastSecuritySchool", temp);
		temp = validateOrganization(personnelForm.getOrganization());
		if (!temp.isEmpty())
			errors.put("organization", temp);
		temp = validateSpecialSkills(personnelForm.getSpecialSkills());
		if (!temp.isEmpty())
			errors.put("specialSkills", temp);
		temp = validateHobbies(personnelForm.getHobbies());
		if (!temp.isEmpty())
			errors.put("hobbies", temp);

		return errors;
	}

	private String validatePersonnelPosition(PersonnelPosition personnelPosition) {
		return notNull(personnelPosition);
	}

	private String validateFirstName(String firstName) {
		return validateString(firstName, 2, 20);
	}

	private String validateLastName(String lastName) {
		return validateString(lastName, 2, 20);
	}

	private String validateMiddleName(String middleName) {
		return validateString(middleName, 2, 20);
	}

	private String validateContactNumber(String contactNumber) {
		return validateString(contactNumber, 2, 20);
	}

	private String validateMotherMaidenName(String motherMaidenName) {
		return validateStringNull(motherMaidenName, 0, 60);
	}

	private String validateCityAddress(String cityAddress) {
		return validateStringNull(cityAddress, 0, 100);
	}

	private String validateProvincialAddress(String provincialAddress) {
		return validateStringNull(provincialAddress, 0, 100);
	}

	private String validateBirthplace(String birthplace) {
		return validateString(birthplace, 2, 100);
	}

	private String validateBirthdate(Date birthdate) {
		String ret = notNull(birthdate);
		if (!ret.isEmpty())
			return ret;

		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		Calendar bday = Calendar.getInstance();
		bday.setTime(birthdate);

		if (now.get(Calendar.YEAR) - bday.get(Calendar.YEAR) < 0) {
			ret = "Invalid birthdate.";
		} else if (now.get(Calendar.YEAR) - bday.get(Calendar.YEAR) < 18) {
			ret = "Birthdate resolves to age under 18.";
		}

		return ret;
	}

	private String validateHeight(String height) {
		return validateString(height, 2, 20);
	}

	private String validateWeight(String weight) {
		return validateString(weight, 2, 20);
	}

	private String validateGender(Gender gender) {
		return notNull(gender);
	}

	private String validateCivilStatus(CivilStatus civilStatus) {
		return notNull(civilStatus);
	}

	private String validateHairColor(String hairColor) {
		return validateStringNull(hairColor, 0, 20);
	}

	private String validateEyeColor(String eyeColor) {
		return validateStringNull(eyeColor, 0, 20);
	}

	private String validateComplexion(String complexion) {
		return validateStringNull(complexion, 0, 20);
	}

	private String validateIdentifyingMarks(String identifyingMarks) {
		return validateString(identifyingMarks, 2, 100);
	}

	private String validateDialectsSpoken(String dialectsSpoken) {
		return validateString(dialectsSpoken, 2, 40);
	}

	private String validateReligion(String religion) {
		return validateString(religion, 2, 20);
	}

	private String validateNameOfSpouse(String nameOfSpouse, CivilStatus civilStatus) {
		if (civilStatus != null && civilStatus.equals(CivilStatus.MARRIED)) {
			return validateString(nameOfSpouse, 2, 60);
		}
		return validateStringNull(nameOfSpouse, 0, 0);
	}

	private String validateOccupationOfSpouse(String occupationOfSpouse, CivilStatus civilStatus) {
		if (civilStatus != null && civilStatus.equals(CivilStatus.MARRIED)) {
			return validateString(occupationOfSpouse, 2, 60);
		}
		return validateStringNull(occupationOfSpouse, 0, 0);
	}

	private String validateNameOfFather(String nameOfFather) {
		return validateString(nameOfFather, 2, 60);
	}

	private String validateOccupationOfFather(String occupationOfFather) {
		return validateString(occupationOfFather, 2, 40);
	}

	private String validateNameOfMother(String nameOfMother) {
		return validateString(nameOfMother, 2, 60);
	}

	private String validateOccupationOfMother(String occupationOfMother) {
		return validateString(occupationOfMother, 2, 40);
	}

	private String validateEmergencyContactPerson(String emergencyContactPerson) {
		return validateString(emergencyContactPerson, 2, 60);
	}

	private String validateContactPersonRelation(String contactPersonRelation) {
		return validateString(contactPersonRelation, 2, 20);
	}

	private String validateContactPersonAddress(String contactPersonAddress) {
		return validateString(contactPersonAddress, 2, 100);
	}

	private String validateContactPersonNumber(String contactPersonNumber) {
		return validateString(contactPersonNumber, 2, 20);
	}

	private String validateRecommendedBy(String recommendedBy) {
		return validateString(recommendedBy, 2, 60);
	}

	private String validateHighSchool(String highSchool) {
		return validateString(highSchool, 2, 60);
	}

	private String validateHighSchoolGradYear(String highSchoolGradYear) {
		return validateString(highSchoolGradYear, 2, 20);
	}

	private String validateVocationalSchool(String vocationalSchool) {
		return validateStringNull(vocationalSchool, 0, 60);
	}

	private String validateVocationalSchoolGradYear(String vocationalSchoolGradYear) {
		return validateStringNull(vocationalSchoolGradYear, 0, 20);
	}

	private String validateCollege(String college) {
		return validateString(college, 2, 60);
	}

	private String validateCollegeDegree(String collegeDegree) {
		return validateString(collegeDegree, 2, 60);
	}

	private String validateCollegeGradYear(String collegeGradYear) {
		return validateString(collegeGradYear, 2, 20);
	}

	private String validateLastSecuritySchool(String lastSecuritySchool) {
		return validateString(lastSecuritySchool, 2, 60);
	}

	private String validateOrganization(String organization) {
		return validateStringNull(organization, 0, 100);
	}

	private String validateSpecialSkills(String specialSkills) {
		return validateString(specialSkills, 2, 100);
	}

	private String validateHobbies(String hobbies) {
		return validateString(hobbies, 2, 100);
	}
}
