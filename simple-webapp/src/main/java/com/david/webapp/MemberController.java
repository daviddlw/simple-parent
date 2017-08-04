package com.david.webapp;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.david.dto.MemberDTO;
import com.david.dto.request.MemberRequest;
import com.david.dto.response.MemberResponse;

@Controller
@RequestMapping("member")
public class MemberController {

	@ResponseBody
	@RequestMapping(value = "/op_get_member_info.json", method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public MemberResponse getMemberInfo(@RequestBody MemberRequest request) {
		System.out.println(JSON.toJSONString(request, true));
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberNo(RandomStringUtils.randomAlphanumeric(18));
		memberDTO.setRealname(RandomStringUtils.randomAlphabetic(6));
		memberDTO.setUsername(RandomStringUtils.randomAlphabetic(6));
		memberDTO.setMobile("131" + RandomStringUtils.randomNumeric(8));
		memberDTO.setEmail(RandomStringUtils.randomNumeric(9) + "@qq.com");
		memberDTO.setAge(RandomUtils.nextInt(1, 30));
		MemberResponse response = new MemberResponse();
		response.setMemberDTO(memberDTO);
		response.setRespCode("1000");
		response.setMsg(StringUtils.EMPTY);
		return response;
	}

}
