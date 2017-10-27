package com.intellect.spring.boot.cud.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CUDResponse {
	@JsonProperty("resMsg")
    private String resMsg;
    @JsonProperty("respDesc")
    private String respDesc;
    
    @JsonProperty("resMsg")
	public String getResMsg() {
		return resMsg;
	}
    
    @JsonProperty("resMsg")
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
    
    @JsonProperty("respDesc")
	public String getRespDesc() {
		return respDesc;
	}
    
    @JsonProperty("respDesc")
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
}
