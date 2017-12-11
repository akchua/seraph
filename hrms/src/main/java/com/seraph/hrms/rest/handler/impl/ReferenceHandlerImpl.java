package com.seraph.hrms.rest.handler.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seraph.hrms.UserContextHolder;
import com.seraph.hrms.beans.ReferenceFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Personnel;
import com.seraph.hrms.database.entity.Reference;
import com.seraph.hrms.database.service.PersonnelService;
import com.seraph.hrms.database.service.ReferenceService;
import com.seraph.hrms.enums.Color;
import com.seraph.hrms.objects.ObjectList;
import com.seraph.hrms.rest.handler.ReferenceHandler;
import com.seraph.hrms.rest.validator.ReferenceFormValidator;
import com.seraph.hrms.utility.Html;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
@Component
@Transactional
public class ReferenceHandlerImpl implements ReferenceHandler {

	@Autowired
	private ReferenceService referenceService;
	
	@Autowired
	private PersonnelService personnelService;
	
	@Autowired
	private ReferenceFormValidator referenceFormValidator;

	@Override
	public Reference getReference(Long referenceId) {
		return referenceService.find(referenceId);
	}

	@Override
	public ObjectList<Reference> getReferenceObjectList(Integer pageNumber, Long personnelId, String searchKey) {
		return referenceService.findAllByPersonnelWithPagingOrderByName(pageNumber, UserContextHolder.getItemsPerPage(), personnelId, searchKey);
	}

	@Override
	public List<Reference> getReferenceListByPersonnel(Long personnelId) {
		return referenceService.findAllByPersonnelOrderByName(personnelId);
	}

	@Override
	public ResultBean createReference(ReferenceFormBean referenceForm, Long personnelId) {
		final ResultBean result;
		final Personnel personnel = personnelService.find(personnelId);
		
		if(personnel != null) {
			final Map<String, String> errors = referenceFormValidator.validate(referenceForm);
			
			if(errors.isEmpty()) {
				final Reference reference = new Reference();
				
				reference.setPersonnel(personnel);
				setReference(reference, referenceForm);
				
				result = new ResultBean();
				result.setSuccess(referenceService.insert(reference) != null);
				if(result.getSuccess()) {
					result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " added reference - " 
									+ Html.text(Color.BLUE, 
											reference.getFirstName() + " " + reference.getLastName()) + ". Thank you."));
				} else {
					result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
				}
			} else {
				result = new ResultBean(Boolean.FALSE, "");
				result.addToExtras("errors", errors);
			}
		} else {
			result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load personnel. Please refresh the page."));
		}
		
		return result;
	}

	@Override
	public ResultBean updateReference(ReferenceFormBean referenceForm) {
		final ResultBean result;
		final Map<String, String> errors = referenceFormValidator.validate(referenceForm);
		
		if(errors.isEmpty()) {
			final Reference reference = referenceService.find(referenceForm.getId());
			
			setReference(reference, referenceForm);
			
			result = new ResultBean();
			result.setSuccess(referenceService.update(reference));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " updated " 
								+ Html.text(Color.BLUE,
										reference.getFirstName() + " " + reference.getLastName()) + "'s information. Thank you."));
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
	public ResultBean removeReference(Long referenceId) {
		final ResultBean result;
		
		final Reference reference = referenceService.find(referenceId);
		if(reference != null) {
			result = new ResultBean();
			
			result.setSuccess(referenceService.delete(referenceId));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " removed reference - " 
						+ Html.text(Color.BLUE, 
								reference.getFirstName() + " " + reference.getLastName()) + ". Thank you."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load reference. Please refresh the page."));
		}
		
		return result;
	}
	
	private void setReference(Reference reference, ReferenceFormBean referenceForm) {
		reference.setFirstName(referenceForm.getFirstName());
		reference.setLastName(referenceForm.getLastName());
		reference.setContactNumber(referenceForm.getContactNumber());
	}
}
