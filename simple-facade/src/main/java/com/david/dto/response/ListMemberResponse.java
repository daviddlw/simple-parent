package com.david.dto.response;

import java.util.List;

import com.david.dto.MemberDTO;

public class ListMemberResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<MemberDTO> memberDTOs;

	public List<MemberDTO> getMemberDTOs() {
		return memberDTOs;
	}

	public void setMemberDTOs(List<MemberDTO> memberDTOs) {
		this.memberDTOs = memberDTOs;
	}

}
