package com.intellect.spring.boot.cud.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "type",
    "code",
    "message",
    "detail"
})


public class Exceptions {
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("code")
	private String exceptionCode;
	
	@JsonProperty("message")
	private String exceptionMsg;
	
	@JsonProperty("detail")
	private String exceptionDetails;

	/**
	 * @return the type
	 */
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the exceptionCode
	 */
	@JsonProperty("code")
	public String getExceptionCode() {
		return exceptionCode;
	}

	/**
	 * @param exceptionCode the exceptionCode to set
	 */
	@JsonProperty("code")
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	/**
	 * @return the exceptionMsg
	 */
	@JsonProperty("message")
	public String getExceptionMsg() {
		return exceptionMsg;
	}

	/**
	 * @param exceptionMsg the exceptionMsg to set
	 */
	@JsonProperty("message")
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	/**
	 * @return the exceptionDetails
	 */
	@JsonProperty("detail")
	public String getExceptionDetails() {
		return exceptionDetails;
	}

	/**
	 * @param exceptionDetails the exceptionDetails to set
	 */
	@JsonProperty("detail")
	public void setExceptionDetails(String exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}

	
	

}
