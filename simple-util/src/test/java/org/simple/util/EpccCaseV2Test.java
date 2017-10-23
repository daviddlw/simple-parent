package org.simple.util;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.simple.util.common.EpccUtils;
import org.simple.util.common.JaxbUtils;
import org.simple.util.common.RsaUtils;
import org.simple.util.constants.AcctInTpIdConstants;
import org.simple.util.dto.MsgHeader;
import org.simple.util.dto.epcc20100101.Epcc20100101ReqMsgBody;
import org.simple.util.dto.epcc20100101.Epcc20100101ReqOrderInf;
import org.simple.util.dto.epcc20100101.Epcc20100101ReqPyeeInf;
import org.simple.util.dto.epcc20100101.Epcc20100101ReqPyerInf;
import org.simple.util.dto.epcc20100101.Epcc20100101ReqResfdInf;
import org.simple.util.dto.epcc20100101.Epcc20100101ReqTrxInf;
import org.simple.util.dto.epcc20100101.Epcc20100101Request;
import org.simple.util.dto.epcc20100101.Epcc20100101Response;

/**
 * 网联-协议支付
 * 
 * @author dailiwei
 *
 */
public class EpccCaseV2Test extends BasicEpccCase {
	
	/**
	 * 协议支付
	 * @throws Exception
	 */
	private String doAgreementPayment() throws Exception {
		
		// 产生随机aes256bit-32字节长度秘钥
		String aeskey = RandomStringUtils.randomAlphanumeric(32);
		logger.info("aeskey=" + aeskey + ",length=" + aeskey.length());
		// 使用网联平台公钥对该信封信息进行加密
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(certPath);
		logger.info("publicKey=" + publicKey);
		
		Epcc20100101ReqPyerInf pyerInf = new Epcc20100101ReqPyerInf();
		pyerInf.setPyerAcctIssrId(C1010611003601);
		pyerInf.setPyerAcctTp(ACCT_TP_00);
		pyerInf.setSgnNo("725014142");
//		pyerInf.setPyerTrxTrmTp(TrxTrmTpCdConstants.MOBILE);
//		pyerInf.setPyerTrxTrmNo("F93P72HCFR9M");

		Epcc20100101ReqPyeeInf pyeeInf = new Epcc20100101ReqPyeeInf();
		pyeeInf.setPyeeAcctIssrId(Z2006845000013);
		pyeeInf.setPyeeAcctId("282704424720582705");
		pyeeInf.setPyeeNm("abc@qq.com");
		pyeeInf.setPyeeAcctTp("04");
//		pyeeInf.setPyeeCtryNo("CN");
//		pyeeInf.setPyeeAreaNo("110102");
//		pyeeInf.setPyeeTrxTrmTp(TrxTrmTpCdConstants.BAR_CODE);
//		pyeeInf.setPyeeTrxTrmNo("1D6AP1500");

		Epcc20100101ReqResfdInf resfdInf = new Epcc20100101ReqResfdInf();
		resfdInf.setInstgAcctId("10012786190055100977");
		resfdInf.setInstgAcctNm("客户备付金");
		resfdInf.setResfdAcctIssrId(C1010611003601);

		Epcc20100101ReqTrxInf trxInf = new Epcc20100101ReqTrxInf();
		trxInf.setTrxId(EpccUtils.genTransSerialNo());
		trxInf.setTrxDtTm(new Date());
		trxInf.setTrxAmt("CNY" + new BigDecimal(RandomUtils.nextDouble(0, 1000)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		trxInf.setTrxCtgy("0110");
		trxInf.setBizTp("100002"); // 业务种类A3待补充
		trxInf.setAcctInTp(AcctInTpIdConstants.RECEIVABLES); // 收款扫码

		Epcc20100101ReqOrderInf orderInf = new Epcc20100101ReqOrderInf();
		orderInf.setOrderId(EpccUtils.genOrderId());
		orderInf.setOrderDesc("2|2|chd%07515%01%14%0851902%5945%2#2#brd^CNY201.70^1#fng^CNY181.71^1|shy%79231%01%14%210100X%8912%1#1#gyr^CNY134.76^1|");
		orderInf.setPltfrmNm("batpay");

		Epcc20100101Request request = new Epcc20100101Request();
		MsgHeader msgHeader = getMsgHeader(EPCC_201_001_01, "", "");
		Epcc20100101ReqMsgBody msgBody = new Epcc20100101ReqMsgBody();
		msgBody.setPyerInf(pyerInf);
		msgBody.setRPFlg("");
		msgBody.setPyeeInf(pyeeInf);
		msgBody.setResfdInf(resfdInf);
		msgBody.setTrxInf(trxInf);
		msgBody.setOrderInf(orderInf);
		msgBody.setBatchId(EpccUtils.genBatchId());
		msgBody.setTrxDevInf("127.0.0.1|" + RandomStringUtils.randomAlphanumeric(12).toUpperCase() + "||||||");
		request.setMsgHeader(msgHeader);
		request.setMsgBody(msgBody);

		String result = postToEpccGateway(EPCC_PROT_443_URL, EPCC_201_001_01, request);
		
		String responseStr = EpccUtils.getResponseStr(result);
		String responseSignStr = EpccUtils.getResponseSign(result);
		
		// 验签
		boolean verifySign = RsaUtils.vertify(publicKey, responseStr, responseSignStr);
		logger.info("verifySign=" + verifySign);
		Assert.assertTrue(verifySign);

		// 组装成对应的JavaBean
		Epcc20100101Response response = JaxbUtils.toBean(String.format("%s%s", JaxbUtils.XML_HEADER, responseStr), Epcc20100101Response.class);
		logger.info(response.toString());
		Assert.assertEquals(SUCCESS_CODE, response.getMsgBody().getSysRtnInf().getSysRtnCd());
		logger.info("TrxStatus="+response.getMsgBody().getBizInf().getTrxStatus());
		logger.info("BizStsCd=" + response.getMsgBody().getBizInf().getBizStsCd());
		logger.info("BizStsDesc=" + response.getMsgBody().getBizInf().getBizStsDesc());
		return response.getMsgBody().getBizInf().getBizStsCd();
	}

	/**
	 * case(201001~201003)
	 * @throws Exception
	 */
	@Test
	public void epcc20100101CaseTest() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals(SUCCESS_CODE, respCode);
	}
	
	/**
	 * case-201100
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201100Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520011", respCode);
	}
}
