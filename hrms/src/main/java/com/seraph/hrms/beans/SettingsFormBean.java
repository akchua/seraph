package com.seraph.hrms.beans;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   Nov 30, 2017
 */
public class SettingsFormBean extends FormBean {

	private Integer itemsPerPage;
	
	public SettingsFormBean() {
		
	}
	
	public SettingsFormBean(Integer itemsPerPage) {
		setItemsPerPage(itemsPerPage);
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
}
