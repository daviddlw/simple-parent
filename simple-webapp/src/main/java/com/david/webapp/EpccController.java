package com.david.webapp;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.david.util.BasicEpccCase;
import com.david.util.common.EpccUtils;
import com.david.util.common.JaxbUtils;
import com.david.util.common.RsaUtils;
import com.david.util.constants.Constants;
import com.david.util.dto.CommonResponse;
import com.david.util.dto.RespCommonMsgBody;
import com.david.util.dto.RespSysRtnInf;

@Controller
@RequestMapping("epcc")
public class EpccController {
	
	@RequestMapping(value = "/epcc_notify.json", method = { RequestMethod.POST, RequestMethod.GET })
	public void epccNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(BasicEpccCase.certPath);
		System.out.println("publicKey=" + publicKey);
		
		System.out.println("epcc网联返回支付公司回调信息-Start ");
		System.out.println("receive [流的形式返回的]流的形式request : " + request);
		request.setAttribute("Content-Type", "application/xml;charset=utf-8");
		String result = IOUtils.toString(request.getInputStream(), Charset.forName(Constants.UTF_8));
		System.out.println("result=" + result);

		String respXmlData = EpccUtils.getResponseStr(result);
		System.out.println("网联返回支付公司的明文respXmlData =====" + respXmlData);
		String respSignData = EpccUtils.getResponseSign(result);
		System.out.println("网联返回支付公司的密文respSignData =====" + respSignData);
		
		// 验签
		boolean verifySign = RsaUtils.vertify(publicKey, respXmlData, respSignData);
		System.out.println("verifySign=" + verifySign);
		Assert.assertTrue(verifySign);

		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setMsgHeader(BasicEpccCase.getMsgHeader(BasicEpccCase.EPCC_303_001_01, "", ""));
		RespCommonMsgBody msgBody = new RespCommonMsgBody();
		RespSysRtnInf sysRtnInf = new RespSysRtnInf();
		sysRtnInf.setSysRtnCd(BasicEpccCase.SUCCESS_CODE);
		sysRtnInf.setSysRtnDesc(BasicEpccCase.SUCCESS_DESC);
		sysRtnInf.setSysRtnTm(new Date());
		msgBody.setSysRtnInf(sysRtnInf);
		commonResponse.setMsgBody(msgBody);
		String respStr = JaxbUtils.toXml(commonResponse);
		System.out.println("respStr=" + respStr);
		String signStr = RsaUtils.sign(BasicEpccCase.privateKey, respStr);
		System.out.println("signStr=" + signStr);
		String requestBody = String.format("%s%s{S:%s}", JaxbUtils.XML_HEADER, respStr, signStr);
		System.out.println("requestBody=" + requestBody);

		response.setCharacterEncoding(Constants.UTF_8);
		response.setHeader("Content-Type", "application/xml;charset=UTF-8");
		response.setHeader(BasicEpccCase.MSG_TP, BasicEpccCase.EPCC_303_001_01);
		response.setHeader(BasicEpccCase.ORI_ISSR_ID, BasicEpccCase.Z2006845000013);
		response.setHeader(BasicEpccCase.CONNECTION, BasicEpccCase.KEEP_ALIVE);
		OutputStream outStream = response.getOutputStream();
		outStream.write(requestBody.getBytes(Charset.forName(Constants.UTF_8)));
		outStream.flush();
	}

}
