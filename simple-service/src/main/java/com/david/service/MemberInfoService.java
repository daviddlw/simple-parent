package com.david.service;

import java.util.List;

import com.david.dto.MemberDTO;

public interface MemberInfoService {
	
	int countMemberInfo(MemberDTO memberInfo);
	
	int deleteMemberInfo(MemberDTO memberInfo);
	
	List<MemberDTO> getMemberInfos(MemberDTO memberInfo);
	
	MemberDTO getMemberInfo(MemberDTO memberInfo);
	
	MemberDTO getMemberInfo(String memberNo);

	int updateMemberInfo(MemberDTO memberInfo);
	
	int insertMemberInfo(MemberDTO memberInfo);
}
