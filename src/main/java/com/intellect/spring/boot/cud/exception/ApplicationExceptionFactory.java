package com.intellect.spring.boot.cud.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ApplicationExceptionFactory {
	
	ErrorCodesConfig exceptionInfo = ErrorCodesConfig.getInstance();
	
	@Autowired
	ApplicationExceptionsList list;
	
	public ApplicationException createNewAppexception(String exceptionCode){
		
		String errorCode =  exceptionInfo.getExceptionCode(exceptionCode);
		String errorMsg = exceptionInfo.getExceptionMsg(exceptionCode);
		String errorDetails = exceptionInfo.getExceptionDetails(exceptionCode);
		String type = exceptionInfo.getExceptionType(exceptionCode);
		String httpStatus = exceptionInfo.getHttpStatusCode(exceptionCode);
		
		return new ApplicationException(type,errorCode,errorMsg,errorDetails,httpStatus); 
	}
	
	
	public ApplicationException createNewAppexceptionWithErrorDetails(String exceptionCode, String errorDetails){
		
		String errorCode =  exceptionInfo.getExceptionCode(exceptionCode);
		String errorMsg = exceptionInfo.getExceptionMsg(exceptionCode);
		String type = exceptionInfo.getExceptionType(exceptionCode);
		String httpStatus = exceptionInfo.getHttpStatusCode(exceptionCode);
		
		return new ApplicationException(type,errorCode,errorMsg,errorDetails,httpStatus); 
	}
	
	public ApplicationException createNewAppexception(String exceptionCode,String exceptionDetail){
		
		String errorCode =  exceptionInfo.getExceptionCode(exceptionCode);
		String errorMsg = exceptionInfo.getExceptionMsg(exceptionCode);
		String type = exceptionInfo.getExceptionType(exceptionCode);
		String httpStatus = exceptionInfo.getHttpStatusCode(exceptionCode);
		String errorDetails = (exceptionDetail!=null)?exceptionDetail:exceptionInfo.getExceptionDetails(exceptionCode);
		
		return new ApplicationException(type,errorCode,errorMsg,errorDetails,httpStatus); 
		
	}
	
	public void addException(String exceptionCode){
		
		list.addAppExceptionsList(createNewAppexception(exceptionCode));
		
	}
	
	public void addException(String exceptionCode,String exceptionDetail){
		
		if(exceptionDetail!=null){
			list.addAppExceptionsList(createNewAppexception(exceptionCode,exceptionDetail));
		}else{
			addException(exceptionCode);
		}
		
	}
	
	public void throwApplicationExceptionsList() throws ApplicationExceptionsList{
		throw list;	
	}
	
}
