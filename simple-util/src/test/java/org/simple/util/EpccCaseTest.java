package org.simple.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.simple.util.common.AesUtils;
import org.simple.util.common.EpccUtils;
import org.simple.util.common.HttpUtils;
import org.simple.util.common.JaxbUtils;
import org.simple.util.common.RsaUtils;
import org.simple.util.constants.LogLevel;
import org.simple.util.dto.epcc.Epcc10100101ReqInstgInf;
import org.simple.util.dto.epcc.Epcc10100101ReqMsgBody;
import org.simple.util.dto.epcc.Epcc10100101ReqSgnInf;
import org.simple.util.dto.epcc.Epcc10100101ReqTrxInf;
import org.simple.util.dto.epcc.Epcc10100101Request;
import org.simple.util.dto.epcc.Epcc10100101Response;
import org.simple.util.dto.epcc.Epcc40100101Request;
import org.simple.util.dto.epcc.Epcc40100101RespCtrlNbInfLst;
import org.simple.util.dto.epcc.Epcc40100101Response;
import org.simple.util.dto.epcc.MsgBody;
import org.simple.util.dto.epcc.MsgHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网联用例
 * 
 * @author dailiwei
 *
 */
public class EpccCaseTest {

	private static Logger logger = LoggerFactory.getLogger(LogLevel.LOG_TEST);

	private static final String SUCCESS_CODE = "00000000";
	private static final String Z2006845000013 = "Z2006845000013";

	private static final String CONNECTION = "Connection";
	private static final String ORI_ISSR_ID = "OriIssrId";
	private static final String MSG_TP = "MsgTp";
	private static final String EPCC_PROT_443_URL = "https://59.151.65.97:443/preSvr";
	private static final String EPCC_PROT_551_URL = "https://59.151.65.97:551/preSvr";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	// 加密私钥
	private static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDaqMfKwPPb54k7p71a9Ua9NqblrRT9scH0WXcuk8YwdOAVO7tFlvf2KyRiHU3TQiTimYqX9OZGCyaS6kftQcKZBJm2rV2pE27S2URUzNcVTBR1xpxQnMG8Q9CzUGeUWbsYP1FFjqOkq+kIBwFqTG42q7let6ldRbRNE9HT8d88AHQJS6BRLflJ7bzGgNmyh6mYf+vYUYBvCdH7g2bo1Z1f5k44O7D/zZn9al6dkXV/zoti4evle1WlPCFLTS5R/5Izi3DTKySafBJ9LJzoLylI91K1tiSUXGIH3zWR9xxeYZoIF1yIgQpLvoyS4nrPhe+2/m2QZfyFK+m27hagIU8xAgMBAAECggEAJlNVCY2+cHnpzOH+x5WcO4f7wuAOgNUKWOjhgfF22IFz0WTx0yW9+pDfRK88N94tFuawqyfKwNYtgay8xLI1CJsM0j8a3orAbwaT+oUY4eu+3lHcjiibsIL2bqeWMCN2Lq7ScO2qcy+KndSUg+w3mS+KQzbP4cBY9PWXXp3TcfGTcxpj3YmowNUfvFS4dAWZQeE1iE7rJls+Nb9If/hN8K9tZUfBGON+2jtZwLM2nRHgCPgkOvJgKGbVauCrZlWUFOSHiBGZR+bfGvQx4942t8WbzqS3eb9X8jXPCjFFWb1DiUAlpmsGKEc1NemWcGhKu1/d/VuWKE0GkyBNccBOjQKBgQDvcdQPkVCfEVWpTgc8VpRTtNhl4j51SIijeA+FAYDVa8NaUfcMml7pjWmKKnFJI94K7eGEJ7vN7EPG52xOU1iYWFq1T+xhLyh0gUz0DrFT9BkrGDpqSn9wRATbbc9Gqdm32WneLCz9We8MSD+LF6RXescdaCjGO03iLBAUcV4tkwKBgQDpxw0mclHbg4t4xd1IY17hvvYM2ORSXtt44d4jCkezfsrPPtRg/SSG5wGcOIlP2sIwYv2SCcZTOLbKoRLyEZLXqK0w0Cx7Imc6jB/p7Gj7mNU/7HQUtwvob+gkQOXPtcPfGGvGMsWm7z1Q4uvwDXzs6EuyXenUE41Vm22yQ35qqwKBgC7V0gf1gZKLnnjOVWX8/WheIFHVbigctvVan5aBk8SrHnwFOlCRxWzjhzhKUvxecqkqnIjwCLEfvKYkUDAF53dtGNkMOA1OXxhizj2SvibQwTeHtq1hwwmflF+jW/7TbE2kzitx8p7fv31kiGFZj4C4+EeNPyR/Jx3NRpvpDOXXAoGBANsqqvBtYsK6a4pZbeBMkQpw3fojaMK0fWuxzXDqVVg5OWfcTn1zNchnUAImmszLmRyF4ZYFJfKli/Eh20IoKZOXZm8J63mxQjgIYG8NHUsq+FnKkvVMupQ6PdenJAx8Ktq/6WJR/S1IwyJO68UM0B7GlRjupKYXgnxMkCX80sqrAoGBAJPq+jvIAsDMpb6VXdpQynXokKbfds2JY5pT5neUI9mccRczCY585NroZKYb5cpxpF3bdXkpYzPY+RFUA3usZUluZHui/Kw/yMDIhm3EwLjH0aFVNg5pgM+6AjWfHFL39wUSsCKLwLiSnZ00JXQalJcm3ejzTX1lOXqtJIgm0G9X";
	private static String certPath = "Q:" + File.separator + "epcc" + File.separator + "wanglian-rsa.cer";
	// 四要素
	private static final String ID_NO = "421127198509140413";
	private static final String ID_CARD_NAME = "张三丰1";
	private static final String CARD_NO = "6214832130521235";
	private static final String MOBILE = "15800563769";

	// api
	private static final String EPCC_401_001_01 = "epcc.401.001.01";
	private static final String EPCC_101_001_01 = "epcc.101.001.01";

	/**
	 * epcc.401.001.01流水号6位控制位请求
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc40100101CaseTest() throws Exception {
		Epcc40100101Request request = new Epcc40100101Request();
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setSndDt(new Date());
		msgHeader.setMsgTp(EPCC_401_001_01);
		msgHeader.setIssrId(Z2006845000013);
		msgHeader.setDrctn("11");
		msgHeader.setSignSN("4002567531");
		MsgBody msgBody = new MsgBody();
		msgBody.setInstgId(Z2006845000013);
		request.setMsgHeader(msgHeader);
		request.setMsgBody(msgBody);

		String result = postToEpccGateway(EPCC_PROT_443_URL, EPCC_401_001_01, request);
		String responseStr = EpccUtils.getResponseStr(result);
		String responseSignStr = EpccUtils.getResponseSign(result);

		// rsa文本公钥
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(certPath);
		logger.info("publicKey=" + publicKey);

		// 验签
		boolean verifySign = RsaUtils.vertify(publicKey, responseStr, responseSignStr);
		logger.info("verifySign=" + verifySign);
		Assert.assertTrue(verifySign);

		// 组装成对应的JavaBean
		Epcc40100101Response response = JaxbUtils.toBean(String.format("%s%s", JaxbUtils.XML_HEADER, responseStr), Epcc40100101Response.class);
		logger.info(response.toString());
		String code = response.getMsgBody().getSysRtnInf().getSysRtnCd();
		String message = response.getMsgBody().getSysRtnInf().getSysRtnDesc();
		Assert.assertEquals(SUCCESS_CODE, code);
		logger.info("message=" + message);
		List<String> ctrlNbInfs = new ArrayList<>();
		for (Epcc40100101RespCtrlNbInfLst item : response.getMsgBody().getCtrlNbInfList()) {
			ctrlNbInfs.add(item.getCtrlNbF());
		}
		logger.info(ctrlNbInfs.toString());
	}

	/**
	 * epcc.101.001.01 身份认证及签约申请报文(case-101001)
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Test() throws Exception {
		String respCode = identityAuthAndSign("0201", EpccUtils.genTransSerialNo(), RandomStringUtils.randomNumeric(6));
		Assert.assertEquals(SUCCESS_CODE, respCode);
	}

	/**
	 * case-101002
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101SignTest() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals(SUCCESS_CODE, respCode);
	}

	/**
	 * case-101003
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101SignRepeatTest() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals(SUCCESS_CODE, respCode);
	}

	/**
	 * case-101100
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101100Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511001", respCode);
	}

	/**
	 * case-101101
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101101Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511002", respCode);
	}
	
	/**
	 * case-101102
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101102Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511003", respCode);
	}
	
	/**
	 * case-101103
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101103Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511005", respCode);
	}
	
	/**
	 * case-101104
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101104Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511006", respCode);
	}
	
	/**
	 * case-101105
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101105Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511007", respCode);
	}

	private String identityAuthAndSign(String trxCtgy, String tranSerialNo, String authMsg) throws Exception {
		// 产生随机aes256bit-32字节长度秘钥
		String aeskey = RandomStringUtils.randomAlphanumeric(32);
		logger.info("aeskey=" + aeskey + ",length=" + aeskey.length());
		String encryptIdNo = AesUtils.Aes256Encode(ID_NO, aeskey);
		String encryptIdName = AesUtils.Aes256Encode(ID_CARD_NAME, aeskey);
		String encryptCardNo = AesUtils.Aes256Encode(CARD_NO, aeskey);
		String encryptMobile = AesUtils.Aes256Encode(MOBILE, aeskey);

		// 可用该秘钥对敏感信息加密如果没有则不需要加密
		String envlpStr = String.format("01|%s", aeskey);
		logger.info("envlpStr=" + envlpStr);

		// 使用网联平台公钥对该信封信息进行加密
		String certPath = "Q:" + File.separator + "epcc" + File.separator + "wanglian-rsa.cer";
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(certPath);
		logger.info("publicKey=" + publicKey);
		String dgtlEnvlpStr = RsaUtils.encryptByPublicKey(publicKey, envlpStr);
		logger.info("dgtlEnvlpStr=" + dgtlEnvlpStr);

		Epcc10100101Request request = new Epcc10100101Request();
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setSndDt(new Date());
		msgHeader.setMsgTp(EPCC_101_001_01);
		msgHeader.setIssrId(Z2006845000013);
		msgHeader.setDrctn("11");
		msgHeader.setSignSN("4002567531");
		msgHeader.setNcrptnSN("4000068829");
		msgHeader.setDgtlEnvlp(dgtlEnvlpStr);

		Epcc10100101ReqMsgBody msgBody = new Epcc10100101ReqMsgBody();
		Epcc10100101ReqSgnInf reqSgnInf = new Epcc10100101ReqSgnInf();
		reqSgnInf.setSgnAcctIssrId("C1010611003601");
		reqSgnInf.setSgnAcctTp("00");
		reqSgnInf.setSgnAcctId(encryptCardNo);
		reqSgnInf.setSgnAcctNm(encryptIdName);
		reqSgnInf.setIdNo(encryptIdNo);
		reqSgnInf.setIdTp("01");
		reqSgnInf.setMobNo(encryptMobile);
		Epcc10100101ReqTrxInf reqTrxInf = new Epcc10100101ReqTrxInf();
		reqTrxInf.setTrxCtgy(trxCtgy);
		reqTrxInf.setTrxId(tranSerialNo);
		reqTrxInf.setTrxDtTm(new Date());
		reqTrxInf.setAuthMsg(authMsg);
		Epcc10100101ReqInstgInf reqInstgInf = new Epcc10100101ReqInstgInf();
		reqInstgInf.setInstgId(Z2006845000013);
		String paymentAccountNo = RandomStringUtils.randomNumeric(16);
		logger.info("paymentAccountNo=" + paymentAccountNo);
		String encryptPaymentAccountNo = AesUtils.Aes256Encode(paymentAccountNo, aeskey);
		reqInstgInf.setInstgAcct("0202".equals(trxCtgy) ? encryptPaymentAccountNo : "");
		msgBody.setSgnInf(reqSgnInf);
		msgBody.setTrxInf(reqTrxInf);
		msgBody.setInstgInf(reqInstgInf);
		request.setMsgHeader(msgHeader);
		request.setMsgBody(msgBody);

		String result = postToEpccGateway(EPCC_PROT_443_URL, EPCC_101_001_01, request);
		String responseStr = EpccUtils.getResponseStr(result);
		String responseSignStr = EpccUtils.getResponseSign(result);

		// 验签
		boolean verifySign = RsaUtils.vertify(publicKey, responseStr, responseSignStr);
		logger.info("verifySign=" + verifySign);
		Assert.assertTrue(verifySign);

		// 组装成对应的JavaBean
		Epcc10100101Response response = JaxbUtils.toBean(String.format("%s%s", JaxbUtils.XML_HEADER, responseStr), Epcc10100101Response.class);
		logger.info(response.toString());
		Assert.assertEquals(SUCCESS_CODE, response.getMsgBody().getSysRtnInf().getSysRtnCd());
		logger.info("BizStsCd=" + response.getMsgBody().getBizInf().getBizStsCd());
		logger.info("BizStsDesc=" + response.getMsgBody().getBizInf().getBizStsDesc());
		return response.getMsgBody().getBizInf().getBizStsCd();
	}

	/**
	 * 请求网联接口
	 * 
	 * @param url
	 *            网联接口地址
	 * @param api
	 *            调用的API
	 * @param request
	 *            请求参数
	 * @return 响应结果
	 * @throws Exception
	 *             异常
	 */
	private <T> String postToEpccGateway(String url, String api, T request) throws Exception {
		String requestXml = JaxbUtils.toXmlNoHeader(request);
		logger.info("requestXml=" + requestXml);
		String signStr = RsaUtils.sign(privateKey, requestXml);
		logger.info("signStr=" + signStr);
		String requestBody = String.format("%s%s{S:%s}", JaxbUtils.XML_HEADER, requestXml, signStr);
		logger.info("requestBody=" + requestBody);
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put(MSG_TP, api);
		headerMap.put(ORI_ISSR_ID, Z2006845000013);
		headerMap.put(CONNECTION, "keep-alive");
		String result = HttpUtils.httpXmlPost(url, requestBody, headerMap);
		logger.info("result=" + result);
		return result;
	}

}
