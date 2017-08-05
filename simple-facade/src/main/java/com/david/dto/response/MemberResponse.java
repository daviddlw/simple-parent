package com.david.dto.response;

import com.david.dto.MemberDTO;

public class MemberResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MemberDTO memberDTO;

	public MemberResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDTO getMemberDTO() {
		return memberDTO;
	}

	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

}
