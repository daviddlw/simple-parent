package com.david.dto.response;

import java.io.Serializable;

import com.david.dto.MemberDTO;

public class MemberResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String respCode;
	private String msg;
	private Boolean success;

	private MemberDTO memberDTO;

	public MemberResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public MemberDTO getMemberDTO() {
		return memberDTO;
	}

	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	public Boolean getSuccess() {
		return this.respCode.equals("1000");
	}

}
