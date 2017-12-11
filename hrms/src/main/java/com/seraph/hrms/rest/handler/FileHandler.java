package com.seraph.hrms.rest.handler;

import java.io.File;

/**
 * @author  Adrian Jasper K. Chua
 * @version 1.0
 * @since   11 Dec 2017
 */
public interface FileHandler {

	File findDocumentByFileName(String fileName);
}
