package com.david.util.test;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.david.util.BasicEpccCase;
import com.david.util.common.AesUtils;
import com.david.util.common.EpccUtils;
import com.david.util.common.JaxbUtils;
import com.david.util.common.RsaUtils;
import com.david.util.dto.RespReturnDTO;
import com.david.util.dto.epcc21100101.Epcc21100101ReqMsgBody;
import com.david.util.dto.epcc21100101.Epcc21100101ReqPyeeAcctInf;
import com.david.util.dto.epcc21100101.Epcc21100101ReqPyerAcctInf;
import com.david.util.dto.epcc21100101.Epcc21100101ReqPyerInf;
import com.david.util.dto.epcc21100101.Epcc21100101ReqTrxInf;
import com.david.util.dto.epcc21100101.Epcc21100101Request;
import com.david.util.dto.epcc21100101.Epcc21100101Response;

/**
 * 付款申请211
 * 
 * @author dailiwei
 *
 */
public class EpccCasePaymentApplyTest extends BasicEpccCase {
	
	private String doPaymentApply() throws Exception{
		RespReturnDTO respReturnDTO = paymentApplyRespReturnDTO();
		String code = StringUtils.isBlank(respReturnDTO.getBizInf().getBizStsCd()) ? respReturnDTO.getSysRtnInf().getSysRtnCd()
				: respReturnDTO.getBizInf().getBizStsCd();
		return code;
	}

	private RespReturnDTO paymentApplyRespReturnDTO() throws Exception {
		RespReturnDTO respReturnDTO = new RespReturnDTO();
		Epcc21100101Request request = new Epcc21100101Request();

		// 产生随机aes256bit-32字节长度秘钥
		String aeskey = RandomStringUtils.randomAlphanumeric(32);
		logger.info("aeskey=" + aeskey + ",length=" + aeskey.length());
		// 可用该秘钥对敏感信息加密如果没有则不需要加密
		String envlpStr = String.format("01|%s", aeskey);
		logger.info("envlpStr=" + envlpStr);

		// 使用网联平台公钥对该信封信息进行加密
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(certPath);
		logger.info("publicKey=" + publicKey);
		String dgtlEnvlpStr = RsaUtils.encryptByPublicKey(publicKey, envlpStr);
		logger.info("dgtlEnvlpStr=" + dgtlEnvlpStr);

		Epcc21100101ReqPyerAcctInf pyerAcctInf = new Epcc21100101ReqPyerAcctInf();
		pyerAcctInf.setPyerBkId(C1010611003601);
		pyerAcctInf.setPyerBkNo(RandomStringUtils.randomNumeric(19));
		pyerAcctInf.setPyerBkNm("客户备付金");

		String pyerAcctNo = RandomStringUtils.randomNumeric(16);
		logger.info("pyerAcctNo=" + pyerAcctNo);
		String encryptPyerAcctNo = AesUtils.Aes256Encode(pyerAcctNo, aeskey);
		String pyerAcctNm = RandomStringUtils.randomNumeric(9) + "@qq.com";
		logger.info("pyerAcctNm=" + pyerAcctNm);
		String encryptPyerAcctNm = AesUtils.Aes256Encode(pyerAcctNm, aeskey);

		Epcc21100101ReqPyerInf pyerInf = new Epcc21100101ReqPyerInf();
		pyerInf.setPyerAcctIssrId(Z2006845000013);
		pyerInf.setPyerAcctTp(ACCT_TP_04);
		pyerInf.setPyerAcctNo(encryptPyerAcctNo);
		pyerInf.setPyerAcctNm(encryptPyerAcctNm);

		String pyeeAcctNo = RandomStringUtils.randomNumeric(16);
		logger.info("pyeeAcctNo=" + pyeeAcctNo);
		String encryptPyeeAcctNo = AesUtils.Aes256Encode(pyeeAcctNo, aeskey);
		String pyeeAcctNm = RandomStringUtils.randomNumeric(9) + "@qq.com";
		logger.info("pyeeAcctNm=" + pyeeAcctNm);
		String encryptPyeeAcctNm = AesUtils.Aes256Encode(pyeeAcctNm, aeskey);

		Epcc21100101ReqPyeeAcctInf pyeeAcctInf = new Epcc21100101ReqPyeeAcctInf();
		pyeeAcctInf.setPyeeAcctTp(ACCT_TP_00);
		pyeeAcctInf.setPyeeBkId(C1010611003601);
		pyeeAcctInf.setPyeeBkNo(encryptPyeeAcctNo);
		pyeeAcctInf.setPyeeBkNm(encryptPyeeAcctNm);

		Epcc21100101ReqTrxInf trxInf = new Epcc21100101ReqTrxInf();
		trxInf.setTrxCtgy("0120");
		trxInf.setBizTp("130002");
		trxInf.setTrxId(EpccUtils.genTransSerialNo());
		trxInf.setTrxDtTm(new Date());
		trxInf.setTrxAmt(EpccUtils.getRandomAmount());
		trxInf.setTrxSmmry("收入-工资");
		trxInf.setTrxPrps("0001");
		trxInf.setTrxTrmTp("01");
		trxInf.setTrxTrmNo(RandomStringUtils.randomNumeric(12));
		trxInf.setTrxDevcInf("125.39.23.1||" + RandomStringUtils.randomNumeric(16) + "|||" + RandomStringUtils.randomAlphanumeric(12) + "||");
		
		Epcc21100101ReqMsgBody msgBody = new Epcc21100101ReqMsgBody();
		msgBody.setPyerInf(pyerInf);
		msgBody.setPyerAcctInf(pyerAcctInf);
		msgBody.setPyeeAcctInf(pyeeAcctInf);
		msgBody.setTrxInf(trxInf);
		
		request.setMsgHeader(getMsgHeader(EPCC_211_001_01, MSG_HEADER_NCRPTN_SN, dgtlEnvlpStr));
		request.setMsgBody(msgBody);
		
		String result = postToEpccGateway(EPCC_PROT_443_URL, EPCC_211_001_01, request);
		String responseStr = EpccUtils.getResponseStr(result);
		String responseSignStr = EpccUtils.getResponseSign(result);

		// 验签
		boolean verifySign = RsaUtils.vertify(publicKey, responseStr, responseSignStr);
		logger.info("verifySign=" + verifySign);
		Assert.assertTrue(verifySign);

		// 组装成对应的JavaBean
		Epcc21100101Response response = JaxbUtils.toBean(String.format("%s%s", JaxbUtils.XML_HEADER, responseStr), Epcc21100101Response.class);
		logger.info(response.toString());
		Assert.assertEquals(SUCCESS_CODE, response.getMsgBody().getSysRtnInf().getSysRtnCd());
		logger.info("BizStsCd=" + response.getMsgBody().getBizInf().getBizStsCd());
		logger.info("BizStsDesc=" + response.getMsgBody().getBizInf().getBizStsDesc());
		
		respReturnDTO.setSysRtnInf(response.getMsgBody().getSysRtnInf());
		respReturnDTO.setBizInf(response.getMsgBody().getBizInf());
		
		return respReturnDTO;
	}
	
	/**
	 * case 211001~211008
	 * @throws Exception 
	 */
	@Test
	public void epcc21100101Test() throws Exception {
		String respCode = doPaymentApply();
		Assert.assertEquals(SUCCESS_CODE, respCode);
	}
}
