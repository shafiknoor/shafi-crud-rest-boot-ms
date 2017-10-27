package com.intellect.spring.boot.cud.exception;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class ErrorCodesConfig {
	
	private  Map<String,Map<String,Map<String,String>>> codesList;
	private Map<String,Map<String,String>> exceptionCodesMap;

	private ErrorCodesConfig()
	   {
		//Private constructor to restrict new instances
	
	      InputStream input = this.getClass().getClassLoader().getResourceAsStream("errorcodes.yml");
		  Yaml yaml = new Yaml();
	      try { 
	    	  codesList =  (Map<String,Map<String,Map<String,String>>>) yaml.load(input);
	    	  exceptionCodesMap = (Map<String,Map<String,String>>) codesList.get("codesList");
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	   }
	
	   private static class LazyHolder
	   {
	      private static final ErrorCodesConfig INSTANCE = new ErrorCodesConfig();
	   }
	 
	   public static ErrorCodesConfig getInstance()
	   {
	      return LazyHolder.INSTANCE;
	   }
	   
		public String getExceptionType(String exceptionCode){
			
			Map<String,String> exceptionCodeDeatils = exceptionCodesMap.get(exceptionCode);
			return (String)exceptionCodeDeatils.get("type");
		}
		
		public String getExceptionCode(String exceptionCode){
			Map<String,String> exceptionCodeDeatils = exceptionCodesMap.get(exceptionCode);
			
			String clientExceptionCode = "";
			
			if(exceptionCodeDeatils.containsKey("exceptionCode")){
				
				clientExceptionCode = (String)exceptionCodeDeatils.get("exceptionCode");
				
			}
			
			return clientExceptionCode;
		}
		
		public String getExceptionMsg(String exceptionCode){
			
			Map<String,String> exceptionCodeDeatils = exceptionCodesMap.get(exceptionCode);
			return (String)exceptionCodeDeatils.get("exceptionMsg");
		}
		
		public String getExceptionDetails(String exceptionCode){
			
			Map<String,String> exceptionCodeDeatils = exceptionCodesMap.get(exceptionCode);
			return (String)exceptionCodeDeatils.get("exceptionDetails");
		}
		
		public String getHttpStatusCode(String exceptionCode){
			
			Map<String,String> exceptionCodeDeatils = exceptionCodesMap.get(exceptionCode);
			return (String)exceptionCodeDeatils.get("httpStatus");
		}
}
