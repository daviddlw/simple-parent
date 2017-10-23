package org.simple.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.simple.util.common.AesUtils;
import org.simple.util.common.EpccUtils;
import org.simple.util.common.JaxbUtils;
import org.simple.util.common.RsaUtils;
import org.simple.util.dto.MsgBody;
import org.simple.util.dto.MsgHeader;
import org.simple.util.dto.epcc10100101.Epcc10100101ReqInstgInf;
import org.simple.util.dto.epcc10100101.Epcc10100101ReqMsgBody;
import org.simple.util.dto.epcc10100101.Epcc10100101ReqSgnInf;
import org.simple.util.dto.epcc10100101.Epcc10100101ReqTrxInf;
import org.simple.util.dto.epcc10100101.Epcc10100101Request;
import org.simple.util.dto.epcc10100101.Epcc10100101Response;
import org.simple.util.dto.epcc10300101.Epcc10300101ReqInstgInf;
import org.simple.util.dto.epcc10300101.Epcc10300101ReqMsgBody;
import org.simple.util.dto.epcc10300101.Epcc10300101ReqRescindInf;
import org.simple.util.dto.epcc10300101.Epcc10300101ReqSgnInf;
import org.simple.util.dto.epcc10300101.Epcc10300101Request;
import org.simple.util.dto.epcc10300101.Epcc10300101Response;
import org.simple.util.dto.epcc40100101.Epcc40100101Request;
import org.simple.util.dto.epcc40100101.Epcc40100101RespCtrlNbInfLst;
import org.simple.util.dto.epcc40100101.Epcc40100101Response;

/**
 * 网联用例 签约类
 * 
 * @author dailiwei
 *
 */
public class EpccCaseTest extends BasicEpccCase {

	/**
	 * epcc.401.001.01流水号6位控制位请求
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc40100101CaseTest() throws Exception {
		Epcc40100101Request request = new Epcc40100101Request();
		MsgHeader msgHeader = getMsgHeader(EPCC_401_001_01, "", "");
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
	 * 
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
	 * 
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
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101105Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511007", respCode);
	}

	/**
	 * case-101106
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101106Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511008", respCode);
	}

	/**
	 * case-101107
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101107Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511009", respCode);
	}

	/**
	 * case-101108
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101108Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511010", respCode);
	}

	/**
	 * case-101109
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101109Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511013", respCode);
	}

	/**
	 * case-101110
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101110Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511017", respCode);
	}

	/**
	 * case-101111
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101111Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511018", respCode);
	}

	/**
	 * case-101112
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101112Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511019", respCode);
	}

	/**
	 * case-101113
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101113Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511020", respCode);
	}

	/**
	 * case-101113
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101114Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511021", respCode);
	}

	/**
	 * case-101115
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101115Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511022", respCode);
	}

	/**
	 * case-101116
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101116Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511027", respCode);
	}

	/**
	 * case-101117
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101117Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511028", respCode);
	}

	/**
	 * case-101118
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101118Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511004", respCode);
	}

	/**
	 * case-101119
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101119Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511014", respCode);
	}

	/**
	 * case-101120
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101120Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511016", respCode);
	}

	/**
	 * case-101121
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101121Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511024", respCode);
	}

	/**
	 * case-101122
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101122Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511025", respCode);
	}

	/**
	 * case-101123
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101123Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511026", respCode);
	}

	/**
	 * case-101124
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101124Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511098", respCode);
	}

	/**
	 * case-101125
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case101125Test() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		String respCode = identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		Assert.assertEquals("PB511012", respCode);
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
		String certPath = "D:" + File.separator + "epcc" + File.separator + "wanglian-rsa.cer";
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(certPath);
		logger.info("publicKey=" + publicKey);
		String dgtlEnvlpStr = RsaUtils.encryptByPublicKey(publicKey, envlpStr);
		logger.info("dgtlEnvlpStr=" + dgtlEnvlpStr);

		Epcc10100101Request request = new Epcc10100101Request();
		MsgHeader msgHeader = getMsgHeader(EPCC_101_001_01, "4000068829", dgtlEnvlpStr);

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
	 * case-103001~103003(103002-协议支付未包括)
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case103001Test() throws Exception {
		String respCode = unsign();
		Assert.assertEquals("00000000", respCode);
	}
	
	/**
	 * case-103100
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case103100Test() throws Exception {
		String respCode = unsign();
		Assert.assertEquals("PB512001", respCode);
	}
	
	/**
	 * case-103101
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case103101Test() throws Exception {
		String respCode = unsign();
		Assert.assertEquals("PB512002", respCode);
	}
	
	/**
	 * case-103102
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case103102Test() throws Exception {
		String respCode = unsign();
		Assert.assertEquals("PB512098", respCode);
	}
	
	/**
	 * case-103103
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Case103103Test() throws Exception {
		String respCode = unsign();
		Assert.assertEquals("PB512099", respCode);
	}

	private String unsign() throws Exception {
		// 产生随机aes256bit-32字节长度秘钥
		String aeskey = RandomStringUtils.randomAlphanumeric(32);
		logger.info("aeskey=" + aeskey + ",length=" + aeskey.length());
		// 可用该秘钥对敏感信息加密如果没有则不需要加密
		String envlpStr = String.format("01|%s", aeskey);
		logger.info("envlpStr=" + envlpStr);

		// 使用网联平台公钥对该信封信息进行加密
		String certPath = "D:" + File.separator + "epcc" + File.separator + "wanglian-rsa.cer";
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(certPath);
		logger.info("publicKey=" + publicKey);
		String dgtlEnvlpStr = RsaUtils.encryptByPublicKey(publicKey, envlpStr);
		logger.info("dgtlEnvlpStr=" + dgtlEnvlpStr);

		String paymentAccountNo = RandomStringUtils.randomNumeric(16);
		logger.info("paymentAccountNo=" + paymentAccountNo);
		String encryptPaymentAccountNo = AesUtils.Aes256Encode(paymentAccountNo, aeskey);

		String tranSerialNo = EpccUtils.genTransSerialNo();
		Epcc10300101Request request = new Epcc10300101Request();

		MsgHeader msgHeader = getMsgHeader(EPCC_103_001_01, "4000068829", dgtlEnvlpStr);
		Epcc10300101ReqMsgBody msgBody = new Epcc10300101ReqMsgBody();
		Epcc10300101ReqSgnInf sgnInf = new Epcc10300101ReqSgnInf();
		sgnInf.setSgnAcctIssrId("C1010611003601");
		sgnInf.setSgnAcctShrtId("1235");
		sgnInf.setSgnAcctTp("00");
		sgnInf.setSgnNo("487465669");
		Epcc10300101ReqInstgInf instgInf = new Epcc10300101ReqInstgInf();
		instgInf.setInstgId(Z2006845000013);
		instgInf.setInstgAcct(encryptPaymentAccountNo);
		Epcc10300101ReqRescindInf rescindInf = new Epcc10300101ReqRescindInf();
		rescindInf.setTrxId(tranSerialNo);
		rescindInf.setTrxDtTm(new Date());
		msgBody.setSgnInf(sgnInf);
		msgBody.setInstgInf(instgInf);
		msgBody.setRescindInf(rescindInf);
		request.setMsgHeader(msgHeader);
		request.setMsgBody(msgBody);

		String result = postToEpccGateway(EPCC_PROT_443_URL, EPCC_103_001_01, request);
		String responseStr = EpccUtils.getResponseStr(result);
		String responseSignStr = EpccUtils.getResponseSign(result);

		// 验签
		boolean verifySign = RsaUtils.vertify(publicKey, responseStr, responseSignStr);
		logger.info("verifySign=" + verifySign);
		Assert.assertTrue(verifySign);

		// 组装成对应的JavaBean
		Epcc10300101Response response = JaxbUtils.toBean(String.format("%s%s", JaxbUtils.XML_HEADER, responseStr), Epcc10300101Response.class);
		logger.info(response.toString());
		Assert.assertEquals(SUCCESS_CODE, response.getMsgBody().getSysRtnInf().getSysRtnCd());
		logger.info("BizStsCd=" + response.getMsgBody().getBizInf().getBizStsCd());
		logger.info("BizStsDesc=" + response.getMsgBody().getBizInf().getBizStsDesc());
		return response.getMsgBody().getBizInf().getBizStsCd();
	}

}
