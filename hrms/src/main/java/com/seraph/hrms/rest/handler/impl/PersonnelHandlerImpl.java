package com.seraph.hrms.rest.handler.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seraph.hrms.UserContextHolder;
import com.seraph.hrms.beans.PersonnelFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Personnel;
import com.seraph.hrms.database.service.PersonnelService;
import com.seraph.hrms.enums.Color;
import com.seraph.hrms.enums.Gender;
import com.seraph.hrms.objects.ObjectList;
import com.seraph.hrms.rest.handler.PersonnelHandler;
import com.seraph.hrms.rest.validator.PersonnelFormValidator;
import com.seraph.hrms.utility.Html;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   6 Dec 2017
 */
@Component
@Transactional
public class PersonnelHandlerImpl implements PersonnelHandler {

	@Autowired
	private PersonnelService personnelService;
	
	@Autowired
	private PersonnelFormValidator personnelFormValidator;
	
	@Override
	public Personnel getPersonnel(Long personnelId) {
		return personnelService.find(personnelId);
	}

	@Override
	public ObjectList<Personnel> getPersonnelObjectList(Integer pageNumber, String searchKey) {
		return personnelService.findAllOrderByNameAndPosition(pageNumber, UserContextHolder.getItemsPerPage(), searchKey);
	}

	@Override
	public ResultBean createPersonnel(PersonnelFormBean personnelForm) {
		final ResultBean result;
		final Map<String, String> errors = personnelFormValidator.validate(personnelForm);
		
		if(errors.isEmpty()) {
			final Personnel personnel = new Personnel();
			
			personnel.setCreator(UserContextHolder.getUser().getUserEntity());
			setPersonnel(personnel, personnelForm);
			
			result = new ResultBean();
			result.setSuccess(personnelService.insert(personnel) != null);
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " added personnel - " 
									+ Html.text(Color.BLUE, (personnel.getGender().equals(Gender.MALE) ? "Mr." : "Ms.") 
									+ " " + personnel.getFirstName() + " " + personnel.getLastName()) + ". Thank you."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, "");
			result.addToExtras("errors", errors);
		}
		
		return result;
	}

	@Override
	public ResultBean updatePersonnel(PersonnelFormBean personnelForm) {
		final ResultBean result;
		final Map<String, String> errors = personnelFormValidator.validate(personnelForm);
		
		if(errors.isEmpty()) {
			final Personnel personnel = personnelService.find(personnelForm.getId());
			
			setPersonnel(personnel, personnelForm);
			
			result = new ResultBean();
			result.setSuccess(personnelService.update(personnel));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " updated " 
									+ Html.text(Color.BLUE, (personnel.getGender().equals(Gender.MALE) ? "Mr." : "Ms.") 
									+ " " + personnel.getFirstName() + " " + personnel.getLastName()) + "'s information. Thank you."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, "");
			result.addToExtras("errors", errors);
		}
		
		return result;
	}

	@Override
	public ResultBean removePersonnel(Long personnelId) {
		final ResultBean result;
		
		final Personnel personnel = personnelService.find(personnelId);
		if(personnel != null) {
			result = new ResultBean();
			
			result.setSuccess(personnelService.delete(personnelId));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " removed personnel - " 
						+ Html.text(Color.BLUE, (personnel.getGender().equals(Gender.MALE) ? "Mr." : "Ms.") 
						+ " " + personnel.getFirstName() + " " + personnel.getLastName()) + ". Thank you."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load personnel. Please refresh the page."));
		}
		
		return result;
	}
	
	private void setPersonnel(Personnel personnel, PersonnelFormBean personnelForm) {
		personnel.setPersonnelPosition(personnelForm.getPersonnelPosition());
		personnel.setFirstName(personnelForm.getFirstName());
		personnel.setLastName(personnelForm.getLastName());
		personnel.setMiddleName(personnelForm.getMiddleName());
		personnel.setMotherMaidenName(personnelForm.getMotherMaidenName());
		personnel.setCityAddress(personnelForm.getCityAddress());
		personnel.setProvincialAddress(personnelForm.getProvincialAddress());
		personnel.setBirthplace(personnelForm.getBirthplace());
		personnel.setBirthdate(personnelForm.getBirthdate());
		personnel.setHeight(personnelForm.getHeight());
		personnel.setWeight(personnelForm.getWeight());
		personnel.setGender(personnelForm.getGender());
		personnel.setCivilStatus(personnelForm.getCivilStatus());
		personnel.setHairColor(personnelForm.getHairColor());
		personnel.setEyeColor(personnelForm.getEyeColor());
		personnel.setComplexion(personnelForm.getComplexion());
		personnel.setIdentifyingMarks(personnelForm.getIdentifyingMarks());
		personnel.setDialectsSpoken(personnelForm.getDialectsSpoken());
		personnel.setReligion(personnelForm.getReligion());
		personnel.setNameOfSpouse(personnelForm.getNameOfSpouse());
		personnel.setOccupationOfSpouse(personnelForm.getOccupationOfSpouse());
		personnel.setNameOfFather(personnelForm.getNameOfFather());
		personnel.setOccupationOfFather(personnelForm.getOccupationOfFather());
		personnel.setNameOfMother(personnelForm.getNameOfMother());
		personnel.setOccupationOfMother(personnelForm.getOccupationOfMother());
		personnel.setEmergencyContactPerson(personnelForm.getEmergencyContactPerson());
		personnel.setContactPersonRelation(personnelForm.getContactPersonRelation());
		personnel.setContactPersonAddress(personnelForm.getContactPersonAddress());
		personnel.setRecommendedBy(personnelForm.getRecommendedBy());
		personnel.setHighSchool(personnelForm.getHighSchool());
		personnel.setHighSchoolGradYear(personnelForm.getHighSchoolGradYear());
		personnel.setVocationalSchool(personnelForm.getVocationalSchool());
		personnel.setVocationalSchoolGradYear(personnelForm.getVocationalSchoolGradYear());
		personnel.setCollege(personnelForm.getCollege());
		personnel.setCollegeGradYear(personnelForm.getCollegeGradYear());
		personnel.setCollegeDegree(personnelForm.getCollegeDegree());
		personnel.setLastSecuritySchool(personnelForm.getLastSecuritySchool());
		personnel.setOrganization(personnelForm.getOrganization());
		personnel.setSpecialSkills(personnelForm.getSpecialSkills());
		personnel.setHobbies(personnelForm.getHobbies());
	}
}
