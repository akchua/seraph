package com.seraph.hrms.rest.handler.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seraph.hrms.UserContextHolder;
import com.seraph.hrms.beans.DependentFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Dependent;
import com.seraph.hrms.database.entity.Personnel;
import com.seraph.hrms.database.service.DependentService;
import com.seraph.hrms.database.service.PersonnelService;
import com.seraph.hrms.enums.Color;
import com.seraph.hrms.objects.ObjectList;
import com.seraph.hrms.rest.handler.DependentHandler;
import com.seraph.hrms.rest.validator.DependentFormValidator;
import com.seraph.hrms.utility.Html;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   9 Dec 2017
 */
@Component
@Transactional
public class DependentHandlerImpl implements DependentHandler {

	@Autowired
	private DependentService dependentService;
	
	@Autowired
	private PersonnelService personnelService;
	
	@Autowired
	private DependentFormValidator dependentFormValidator;

	@Override
	public Dependent getDependent(Long dependentId) {
		return dependentService.find(dependentId);
	}

	@Override
	public ObjectList<Dependent> getDependentObjectList(Integer pageNumber, Long personnelId, String searchKey) {
		return dependentService.findAllByPersonnelWithPagingOrderByName(pageNumber, UserContextHolder.getItemsPerPage(), personnelId, searchKey);
	}

	@Override
	public List<Dependent> getDependentListByPersonnel(Long personnelId) {
		return dependentService.findAllByPersonnelOrderByName(personnelId);
	}

	@Override
	public ResultBean createDependent(DependentFormBean dependentForm, Long personnelId) {
		final ResultBean result;
		final Personnel personnel = personnelService.find(personnelId);
		
		if(personnel != null) {
			final Map<String, String> errors = dependentFormValidator.validate(dependentForm);
			
			if(errors.isEmpty()) {
				final Dependent dependent = new Dependent();
				
				dependent.setPersonnel(personnel);
				setDependent(dependent, dependentForm);
				
				result = new ResultBean();
				result.setSuccess(dependentService.insert(dependent) != null);
				if(result.getSuccess()) {
					result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " added dependent - " 
									+ Html.text(Color.BLUE, 
											dependent.getFirstName() + " " + dependent.getLastName()) + ". Thank you."));
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
	public ResultBean updateDependent(DependentFormBean dependentForm) {
		final ResultBean result;
		final Map<String, String> errors = dependentFormValidator.validate(dependentForm);
		
		if(errors.isEmpty()) {
			final Dependent dependent = dependentService.find(dependentForm.getId());
			
			setDependent(dependent, dependentForm);
			
			result = new ResultBean();
			result.setSuccess(dependentService.update(dependent));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " updated " 
								+ Html.text(Color.BLUE,
										dependent.getFirstName() + " " + dependent.getLastName()) + "'s information. Thank you."));
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
	public ResultBean removeDependent(Long dependentId) {
		final ResultBean result;
		
		final Dependent dependent = dependentService.find(dependentId);
		if(dependent != null) {
			result = new ResultBean();
			
			result.setSuccess(dependentService.delete(dependentId));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " removed dependent - " 
						+ Html.text(Color.BLUE, 
								dependent.getFirstName() + " " + dependent.getLastName()) + ". Thank you."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load dependent. Please refresh the page."));
		}
		
		return result;
	}
	
	private void setDependent(Dependent dependent, DependentFormBean dependentForm) {
		dependent.setFirstName(dependentForm.getFirstName());
		dependent.setLastName(dependentForm.getLastName());
		dependent.setAge(dependentForm.getAge());
		dependent.setGender(dependentForm.getGender());
		dependent.setRelationship(dependentForm.getRelationship());
	}
}
