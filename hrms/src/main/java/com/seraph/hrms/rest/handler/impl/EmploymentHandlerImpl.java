package com.seraph.hrms.rest.handler.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seraph.hrms.UserContextHolder;
import com.seraph.hrms.beans.EmploymentFormBean;
import com.seraph.hrms.beans.ResultBean;
import com.seraph.hrms.database.entity.Employment;
import com.seraph.hrms.database.entity.Personnel;
import com.seraph.hrms.database.service.EmploymentService;
import com.seraph.hrms.database.service.PersonnelService;
import com.seraph.hrms.enums.Color;
import com.seraph.hrms.objects.ObjectList;
import com.seraph.hrms.rest.handler.EmploymentHandler;
import com.seraph.hrms.rest.validator.EmploymentFormValidator;
import com.seraph.hrms.utility.Html;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   10 Dec 2017
 */
@Component
@Transactional
public class EmploymentHandlerImpl implements EmploymentHandler {

	@Autowired
	private EmploymentService employmentService;
	
	@Autowired
	private PersonnelService personnelService;
	
	@Autowired
	private EmploymentFormValidator employmentFormValidator;

	@Override
	public Employment getEmployment(Long employmentId) {
		return employmentService.find(employmentId);
	}

	@Override
	public ObjectList<Employment> getEmploymentObjectList(Integer pageNumber, Long personnelId, String searchKey) {
		return employmentService.findAllByPersonnelWithPaging(pageNumber, UserContextHolder.getItemsPerPage(), personnelId, searchKey);
	}

	@Override
	public List<Employment> getEmploymentListByPersonnel(Long personnelId) {
		return employmentService.findAllByPersonnel(personnelId);
	}

	@Override
	public ResultBean createEmployment(EmploymentFormBean employmentForm, Long personnelId) {
		final ResultBean result;
		final Personnel personnel = personnelService.find(personnelId);
		
		if(personnel != null) {
			final Map<String, String> errors = employmentFormValidator.validate(employmentForm);
			
			if(errors.isEmpty()) {
				final Employment employment = new Employment();
				
				employment.setPersonnel(personnel);
				setEmployment(employment, employmentForm);
				
				result = new ResultBean();
				result.setSuccess(employmentService.insert(employment) != null);
				if(result.getSuccess()) {
					result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " added employment - " 
									+ Html.text(Color.BLUE, 
											employment.getCompanyName()) + ". Thank you."));
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
	public ResultBean updateEmployment(EmploymentFormBean employmentForm) {
		final ResultBean result;
		final Map<String, String> errors = employmentFormValidator.validate(employmentForm);
		
		if(errors.isEmpty()) {
			final Employment employment = employmentService.find(employmentForm.getId());
			
			setEmployment(employment, employmentForm);
			
			result = new ResultBean();
			result.setSuccess(employmentService.update(employment));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " updated " 
								+ Html.text(Color.BLUE,
										employment.getCompanyName()) + "'s information. Thank you."));
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
	public ResultBean removeEmployment(Long employmentId) {
		final ResultBean result;
		
		final Employment employment = employmentService.find(employmentId);
		if(employment != null) {
			result = new ResultBean();
			
			result.setSuccess(employmentService.delete(employmentId));
			if(result.getSuccess()) {
				result.setMessage(Html.line(Html.text(Color.GREEN, "Successfully") + " removed employment - " 
						+ Html.text(Color.BLUE, 
								employment.getCompanyName()) + ". Thank you."));
			} else {
				result.setMessage(Html.line(Html.text(Color.RED, "Server Error.") + " Please try again later."));
			}
		} else {
			result = new ResultBean(Boolean.FALSE, Html.line(Html.text(Color.RED, "Failed") + " to load employment. Please refresh the page."));
		}
		
		return result;
	}
	
	private void setEmployment(Employment employment, EmploymentFormBean employmentForm) {
		employment.setCompanyName(employmentForm.getCompanyName());
		employment.setPosition(employmentForm.getPosition());
		employment.setSalary(employmentForm.getSalary());
		employment.setYears(employmentForm.getYears());
		employment.setCauseOfDischarge(employmentForm.getCauseOfDischarge());
	}
}
