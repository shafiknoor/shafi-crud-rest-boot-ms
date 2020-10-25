package com.intellect.spring.boot.cud.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ApplicationExceptionsList extends Exception {
	
	private static final long serialVersionUID = 1L;

	private List<ApplicationException> appExceptionsList = new ArrayList<ApplicationException>();

	/**
	 * @return the appExceptionsList
	 */
	public List<ApplicationException> getAppExceptionsList() {
		return appExceptionsList;
	}

	/**
	 * @param appExceptionsList the appExceptionsList to set
	 */
	public void setAppExceptionsList(
			List<ApplicationException> appExceptionsList) {
		this.appExceptionsList = appExceptionsList;
	}
	
	public void addAppExceptionsList(ApplicationException appException){
		this.appExceptionsList.add(appException);
	}
	
	
	
}
