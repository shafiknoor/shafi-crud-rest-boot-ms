package com.intellect.spring.boot.cud.exception;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(ApplicationExceptionHandler.class);

	@Autowired
	private JSONMapperUtil jsonMapperUtil;
	
	/**
	 * Method to handle application exceptions
	 * 
	 * @param ex
	 * @param request
	 * @return response with the error object.
	 */
	@ExceptionHandler(value = { ApplicationExceptionsList.class,
			ApplicationException.class })
	protected ResponseEntity<Object> handleApplictionException(Exception ex,
			WebRequest request) {

		logger.debug("From ApplicationHandler", ex);

		ApplicationExceptionsList appExpBundle = null;

		int HTTP_STATUS_CODE = 404;

		if (ex instanceof ApplicationException) {

			appExpBundle = new ApplicationExceptionsList();
			appExpBundle.addAppExceptionsList((ApplicationException) ex);

		} else if (ex instanceof ApplicationExceptionsList) {

			appExpBundle = (ApplicationExceptionsList) ex;
		}

		List<ApplicationException> appExpList = appExpBundle
				.getAppExceptionsList();

		ExceptionsList expBundle = new ExceptionsList();
		List<Exceptions> exceptionsList = new ArrayList<Exceptions>();

		for (ApplicationException appExp : appExpList) {

			HTTP_STATUS_CODE = appExp.getHttpStatusCode();

			Exceptions error = new Exceptions();
			error.setExceptionCode(appExp.getErrorCode());
			error.setExceptionMsg(limitErrMsgSize(appExp.getErrorMsg()));
			error.setExceptionDetails(appExp.getErrorDetails());
			error.setType(appExp.getType() != null ? appExp.getType()
					.toString() : "");
			exceptionsList.add(error);
		}

		expBundle.setExceptionList(exceptionsList);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Object>(expBundle, headers,
				HttpStatus.valueOf(HTTP_STATUS_CODE));
	}

	/**
	 * Method to handle all exceptions.
	 * 
	 * @param ex
	 * @param request
	 * @return response entity
	 */
	@ExceptionHandler(value = { Exception.class, HttpClientErrorException.class })
	protected ResponseEntity<Object> handleAllException(RuntimeException ex,
			WebRequest request) {
		logger.debug("From ApplicationHandler", ex);

		int HTTP_STATUS_CODE = 404;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ExceptionsList expBundle = new ExceptionsList();
		List<Exceptions> exceptionsList = new ArrayList<Exceptions>();

		ResponseEntity<Object> responseEntity = null;

		if (ex instanceof HttpClientErrorException) {

			String jsonClientError = ((HttpClientErrorException) ex)
					.getResponseBodyAsString();
			HTTP_STATUS_CODE = ((HttpClientErrorException)ex).getStatusCode().value();
			
			try {
				expBundle = (ExceptionsList) jsonMapperUtil.toObject(
						jsonClientError, ExceptionsList.class);
				responseEntity = new ResponseEntity<Object>(expBundle, headers,
						HttpStatus.NOT_FOUND);
			} catch (ApplicationException e) {
				logger.error(e.getErrorDetails());
			}
		} else {
			Exceptions error = new Exceptions();
			error.setExceptionDetails(ex.getMessage());
			exceptionsList.add(error);
			expBundle.setExceptionList(exceptionsList);
			responseEntity = new ResponseEntity<Object>(expBundle, headers,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;

	}

	@ExceptionHandler(value = { SQLException.class })
	protected ResponseEntity<Object> handleAllException(Exception ex,
			WebRequest request) {

		logger.debug("From ApplicationHandler", ex);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ExceptionsList expBundle = new ExceptionsList();
		List<Exceptions> exceptionsList = new ArrayList<Exceptions>();

		ResponseEntity<Object> responseEntity = null;

		Exceptions error = new Exceptions();
		SQLException sqlException = (SQLException) ex;
		error.setExceptionCode(String.valueOf(sqlException.getErrorCode()));
		error.setExceptionMsg(limitErrMsgSize(sqlException.getSQLState()));
		error.setExceptionDetails(sqlException.getMessage());
		error.setType("E");

		expBundle.setExceptionList(exceptionsList);
		responseEntity = new ResponseEntity<Object>(expBundle, headers,
				HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}

	/**
	 * Method limit the error msg size to 200 chars.
	 * 
	 * @param errMsg
	 * @return String
	 */
	private String limitErrMsgSize(String errMsg) {
		if (null != errMsg && errMsg.length() >= 200) {
			errMsg = errMsg.substring(0, 200);
		}
		return errMsg;
	}
}
