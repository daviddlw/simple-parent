package com.david.dto.response;

import java.io.Serializable;

public class BaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String respCode;
	private String msg;
	private Boolean success;

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Boolean getSuccess() {
		return this.respCode.equals("1000");
	}

	@Override
	public String toString() {
		return "BaseResponse [respCode=" + respCode + ", msg=" + msg + "]";
	}

}
