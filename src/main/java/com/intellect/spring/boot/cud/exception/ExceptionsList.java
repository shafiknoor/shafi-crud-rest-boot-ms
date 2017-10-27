package com.intellect.spring.boot.cud.exception;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "exceptions"
})

public class ExceptionsList {
 
	@JsonProperty("exceptions")
	private List<Exceptions> exceptionList = new ArrayList<Exceptions>();
	 
	/**
	 * @return the claimExceptionList
	 */
	@JsonProperty("exceptions")
	public List<Exceptions> getExceptionList() {
		return exceptionList;
	}

	/**
	 * @param claimExceptionList the claimExceptionList to set
	 */
	@JsonProperty("exceptions")
	public void setExceptionList(List<Exceptions> exceptionList) {
		this.exceptionList = exceptionList;
	}
}
