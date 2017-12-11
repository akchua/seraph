package com.seraph.hrms.rest.handler.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seraph.hrms.UserContextHolder;
import com.seraph.hrms.beans.PersonnelFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.beans.SurveyResponseFormBean;
import com.seraph.hrms.constants.FileConstants;
import com.seraph.hrms.database.entity.Personnel;
import com.seraph.hrms.database.entity.PersonnelImage;
import com.seraph.hrms.database.service.PersonnelImageService;
import com.seraph.hrms.database.service.PersonnelService;
import com.seraph.hrms.enums.Color;
import com.seraph.hrms.enums.SurveyQuestion;
import com.seraph.hrms.objects.ObjectList;
import com.seraph.hrms.rest.handler.PersonnelHandler;
import com.seraph.hrms.rest.handler.SurveyResponseHandler;
import com.seraph.hrms.rest.validator.PersonnelFormValidator;
import com.seraph.hrms.utility.Html;
import com.seraph.hrms.utility.StringHelper;

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
	private PersonnelImageService personnelImageService;
	
	@Autowired
	private PersonnelFormValidator personnelFormValidator;
	
	@Autowired
	private SurveyResponseHandler surveyResponseHandler;
	
	@Autowired
	private FileConstants fileConstants;
	
	@Override
	public Personnel getPersonnel(Long personnelId) {
		return personnelService.find(personnelId);
	}
	
	@Override
	public File findPersonnelImageByFileName(String fileName) {
		return new File(fileConstants.getPersonnelImageHome() + fileName);
	}

	@Override
	public ObjectList<Personnel> getPersonnelObjectList(Integer pageNumber, String searchKey) {
		return personnelService.findAllOrderByNameAndPosition(pageNumber, UserContextHolder.getItemsPerPage(), searchKey);
	}
	
	@Override
	public List<PersonnelImage> getPersonnelImageList(Long personnelId) {
		return personnelImageService.findAllByPersonnelId(personnelId);
	}

	@Override
	public ResultBean createPersonnel(PersonnelFormBean personnelForm) {
		final ResultBean result;
		final Map<String, String> errors = personnelFormValidator.validate(personnelForm);
		
		if(errors.isEmpty()) {
			final Personnel personnel = new Personnel();
			
			personnel.setImage(fileConstants.getImageDefaultFileName());
			personnel.setCreator(UserContextHolder.getUser().getUserEntity());
			setPersonnel(personnel, personnelForm);
			
			result = new ResultBean();
			result.setSuccess(personnelService.insert(personnel) != null);
			if(result.getSuccess()) {
				createBlankSurvey(personnel);
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " added personnel - " 
								+ Html.text(Color.BLUE, 
										personnel.getNamePrefix() + " " + personnel.getFirstName() + " " + personnel.getLastName()) + ". Thank you."));
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
	public ResultBean savePersonnelImage(Long personnelId, InputStream in, FormDataContentDisposition info)
			throws IOException {
		final ResultBean result;
		final String fileName = UUID.randomUUID().toString() + "." + StringHelper.getFileExtension(info.getFileName());
		
		File imageFile = new File(fileConstants.getPersonnelImageHome() + fileName);
		if(imageFile.getParentFile() != null) imageFile.getParentFile().mkdirs();
		
		if(!imageFile.exists()) {
			Files.copy(in, imageFile.toPath());
			final Personnel personnel = personnelService.find(personnelId);
			if(personnel != null) {
				result = new ResultBean();
				
				final PersonnelImage personnelImage = new PersonnelImage();
				personnelImage.setPersonnel(personnel);
				personnelImage.setFileName(fileName);
				
				result.setSuccess(personnelImageService.insert(personnelImage) != null);
				if(result.getSuccess()) {
					this.setPersonnelImageAsThumbnail(personnelImage.getId());
					result.setMessage(Html.line(Color.GREEN, "Upload Successful."));
				} else {
					result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
				}
			} else {
				result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load personnel. Please refresh the page."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, "Error please try uploading again.");
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
								+ Html.text(Color.BLUE,
										personnel.getNamePrefix() + " " + personnel.getFirstName() + " " + personnel.getLastName()) + "'s information. Thank you."));
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
	public ResultBean setPersonnelImageAsThumbnail(Long personnelImageId) {
		final ResultBean result;
		final PersonnelImage personnelImage = personnelImageService.find(personnelImageId);
		
		if(personnelImage != null) {
			result = new ResultBean();
			final Personnel personnel = personnelImage.getPersonnel();
			
			personnel.setImage(personnelImage.getFileName());
			result.setSuccess(personnelService.update(personnel));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " set Image as picture for " + Html.text(Color.BLUE, personnel.getFormattedName()) + "."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load personnel image. Please refresh the page."));
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
						+ Html.text(Color.BLUE, 
								personnel.getNamePrefix() + " " + personnel.getFirstName() + " " + personnel.getLastName()) + ". Thank you."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load personnel. Please refresh the page."));
		}
		
		return result;
	}
	
	@Override
	public ResultBean removePersonnelImage(Long personnelImageId) {
		final ResultBean result;
		final PersonnelImage personnelImage = personnelImageService.find(personnelImageId);
		
		if(personnelImage != null) {
			result = new ResultBean();
			final Personnel personnel = personnelImage.getPersonnel();
			
			// REMOVE AS THUMBNAIL IF DELETED
			if(personnel.getImage().equals(personnelImage.getFileName())) {
				personnel.setImage(fileConstants.getImageDefaultFileName());
				personnelService.update(personnel);
			}
			
			result.setSuccess(personnelImageService.delete(personnelImage));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " removed Image."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load personnel image. Please refresh the page."));
		}
		
		return result;
	}
	
	private void setPersonnel(Personnel personnel, PersonnelFormBean personnelForm) {
		personnel.setPersonnelPosition(personnelForm.getPersonnelPosition());
		personnel.setFirstName(personnelForm.getFirstName());
		personnel.setLastName(personnelForm.getLastName());
		personnel.setMiddleName(personnelForm.getMiddleName());
		personnel.setContactNumber(personnelForm.getContactNumber());
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
	
	private void createBlankSurvey(Personnel personnel) {
		surveyResponseHandler.createSurveyResponse(
				Stream.of(SurveyQuestion.values())
					.parallel()
					.map((sq) -> {
						final SurveyResponseFormBean srf = new SurveyResponseFormBean();
						srf.setSurveyQuestion(sq);
						srf.setResponse("");
						return srf;
					})
					.collect(Collectors.toList()),
				personnel.getId());
	}
}
