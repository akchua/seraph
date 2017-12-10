package com.seraph.hrms.rest.validator;

import java.util.Map;

import com.seraph.hrms.beans.FormBean;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   30 Nov 2017
 */
public interface FormValidator<T extends FormBean> {

	Map<String, String> validate(T t);
}
