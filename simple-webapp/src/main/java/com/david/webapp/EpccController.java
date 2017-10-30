package com.david.webapp;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.david.util.BasicEpccCase;
import com.david.util.common.EpccUtils;
import com.david.util.common.JaxbUtils;
import com.david.util.common.RsaUtils;
import com.david.util.constants.Constants;
import com.david.util.constants.LogLevel;
import com.david.util.dto.BasicSysRtnInf;
import com.david.util.dto.CommonResponse;
import com.david.util.dto.MsgHeader;
import com.david.util.dto.RespCommonMsgBody;
import com.david.util.dto.epcc10400101.Epcc10400101Request;
import com.david.util.dto.epcc20700101.Epcc20700101Request;

/**
 * 通知类
 * 
 * @author dailiwei
 *
 */
@Controller
@RequestMapping("epcc")
public class EpccController {

	private static Logger logger = LoggerFactory.getLogger(LogLevel.LOG_WEB);

	/**
	 * 回调地址
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/epcc_notify.json", method = { RequestMethod.POST, RequestMethod.GET })
	public void epccNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String respStr = getEpccNotifyRequest(request);
		String msgHeaderStr = EpccUtils.getMsgHeaderStr(respStr);
		MsgHeader msgHeader = JaxbUtils.toBean(msgHeaderStr, MsgHeader.class);
		if (BasicEpccCase.EPCC_104_001_01.equals(msgHeader.getMsgTp())) {
			Epcc10400101Request epccRequest = JaxbUtils.toBean(respStr, Epcc10400101Request.class);
			String msgTp = epccRequest.getMsgHeader().getMsgTp();
			String sysRtnCd = epccRequest.getMsgBody().getSysRtnInf().getSysRtnCd();
			String sysRtnDesc = epccRequest.getMsgBody().getSysRtnInf().getSysRtnDesc();
			logger.info("msgTp=" + msgTp);
			logger.info("sysRtnCd=" + sysRtnCd);
			logger.info("sysRtnDesc=" + sysRtnDesc);
			Assert.assertEquals(BasicEpccCase.SUCCESS_CODE, sysRtnCd);

			String bizStsCd = epccRequest.getMsgBody().getBizInf().getBizStsCd();
			String bizStsDesc = epccRequest.getMsgBody().getBizInf().getBizStsDesc();
			logger.info("bizStsCd=" + bizStsCd);
			logger.info("bizStsDesc=" + bizStsDesc);
			Assert.assertEquals(BasicEpccCase.SUCCESS_CODE, sysRtnCd);
		} else if (BasicEpccCase.EPCC_207_001_01.equals(msgHeader.getMsgTp())) {
			Epcc20700101Request epccRequest = JaxbUtils.toBean(respStr, Epcc20700101Request.class);
			String msgTp = epccRequest.getMsgHeader().getMsgTp();
			String sysRtnCd = epccRequest.getMsgBody().getSysRtnInf().getSysRtnCd();
			String sysRtnDesc = epccRequest.getMsgBody().getSysRtnInf().getSysRtnDesc();
			logger.info("msgTp=" + msgTp);
			logger.info("sysRtnCd=" + sysRtnCd);
			logger.info("sysRtnDesc=" + sysRtnDesc);
//			Assert.assertEquals(BasicEpccCase.SUCCESS_CODE, sysRtnCd);

			String bizStsCd = epccRequest.getMsgBody().getBizInf().getBizStsCd();
			String bizStsDesc = epccRequest.getMsgBody().getBizInf().getBizStsDesc();
			logger.info("bizStsCd=" + bizStsCd);
			logger.info("bizStsDesc=" + bizStsDesc);
//			Assert.assertEquals(BasicEpccCase.SUCCESS_CODE, sysRtnCd);
		}

		String requestBody = getCommonResponse();
		sendCommonResponse(response, requestBody);
	}

	/**
	 * 接收网联回调通知响应
	 * 
	 * @param request
	 *            响应request
	 * @param publicKey
	 *            验签公钥wanglian.cer,通过header里面的msgTp获取得知
	 * @throws IOException
	 * @throws Exception
	 */
	private String getEpccNotifyRequest(HttpServletRequest request) throws IOException, Exception {
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(BasicEpccCase.certPath);
		logger.info("publicKey=" + publicKey);

		logger.info("epcc网联返回支付公司回调信息-Start ");
		request.setAttribute("Content-Type", "application/xml;charset=utf-8");
		String result = IOUtils.toString(request.getInputStream(), Charset.forName(Constants.UTF_8));
		logger.info("result=" + result);

		String respXmlData = EpccUtils.getResponseStr(result);
		logger.info("网联返回支付公司的明文respXmlData =====" + respXmlData);
		String respSignData = EpccUtils.getResponseSign(result);
		logger.info("网联返回支付公司的密文respSignData =====" + respSignData);

		// 验签
		boolean verifySign = RsaUtils.vertify(publicKey, respXmlData, respSignData);
		logger.info("verifySign=" + verifySign);
		Assert.assertTrue(verifySign);
		return respXmlData;
	}

	/**
	 * 发送通用应答报文
	 * 
	 * @param response
	 *            响应
	 * @param requestBody
	 *            请求
	 * @throws IOException
	 */
	private void sendCommonResponse(HttpServletResponse response, String requestBody) throws IOException {
		response.setCharacterEncoding(Constants.UTF_8);
		response.setHeader("Content-Type", "application/xml;charset=UTF-8");
		response.setHeader(BasicEpccCase.MSG_TP, BasicEpccCase.EPCC_303_001_01);
		response.setHeader(BasicEpccCase.ORI_ISSR_ID, BasicEpccCase.Z2006845000013);
		response.setHeader(BasicEpccCase.CONNECTION, BasicEpccCase.KEEP_ALIVE);
		OutputStream outStream = response.getOutputStream();
		outStream.write(requestBody.getBytes(Charset.forName(Constants.UTF_8)));
		outStream.flush();
	}

	/**
	 * 构造通用应答报文-成功
	 * 
	 * @return 表示接收响应成功
	 * @throws Exception
	 */
	private String getCommonResponse() throws Exception {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setMsgHeader(BasicEpccCase.getMsgHeader(BasicEpccCase.EPCC_303_001_01, "", ""));
		RespCommonMsgBody msgBody = new RespCommonMsgBody();
		BasicSysRtnInf sysRtnInf = new BasicSysRtnInf();
		sysRtnInf.setSysRtnCd(BasicEpccCase.SUCCESS_CODE);
		sysRtnInf.setSysRtnDesc(BasicEpccCase.SUCCESS_DESC);
		sysRtnInf.setSysRtnTm(new Date());
		msgBody.setSysRtnInf(sysRtnInf);
		commonResponse.setMsgBody(msgBody);
		String respStr = JaxbUtils.toXml(commonResponse);
		logger.info("respStr=" + respStr);
		String signStr = RsaUtils.sign(BasicEpccCase.privateKey, respStr);
		logger.info("signStr=" + signStr);
		String requestBody = String.format("%s%s{S:%s}", JaxbUtils.XML_HEADER, respStr, signStr);
		logger.info("common_response=" + requestBody);
		return requestBody;
	}

}
