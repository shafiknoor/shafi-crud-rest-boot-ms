package com.intellect.spring.boot.cud.exception;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JSONMapperUtil {
	
		@Autowired
		ApplicationExceptionFactory applicationExceptionFactory;

	 	ObjectMapper mapper = new ObjectMapper();  
	 
	 	public String toJSON(Object jsonCompliant) throws ApplicationException
	 	{
		 
	 		String jsonReturnValue = null;
	 	
			 try {  
				 	jsonReturnValue = mapper.writeValueAsString(jsonCompliant); 
			        
			    } catch (JsonProcessingException ejsonProcessingException) { 
			    	
			        throw applicationExceptionFactory.createNewAppexception("1004");  
			    }  	 
		 
			 return jsonReturnValue;
	 	}
	 	
	 	public <T> Object toObject(String jsonString,Class<T> classType) throws ApplicationException
	 	{
		 
	 		Object jsonObject = null;
	 	
			 try {  
				 jsonObject = mapper.readValue(jsonString,classType); 
			        
			    } catch (JsonProcessingException ejsonProcessingException) {
			    	
			        throw applicationExceptionFactory.createNewAppexception("1004");  
			    } catch (IOException e) {
			    	
			    	throw applicationExceptionFactory.createNewAppexception("1004");  
				}  	 
		 
			 return jsonObject;
	 	}
	
	
}
