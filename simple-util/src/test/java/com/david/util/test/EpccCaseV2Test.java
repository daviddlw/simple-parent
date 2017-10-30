package com.david.util.test;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;

import com.david.util.BasicEpccCase;
import com.david.util.common.EpccUtils;
import com.david.util.common.JaxbUtils;
import com.david.util.common.RsaUtils;
import com.david.util.constants.AcctInTpIdConstants;
import com.david.util.constants.TrxTrmTpCd;
import com.david.util.dto.MsgHeader;
import com.david.util.dto.epcc20100101.Epcc20100101ReqMsgBody;
import com.david.util.dto.epcc20100101.Epcc20100101ReqOriTrxInf;
import com.david.util.dto.epcc20100101.Epcc20100101ReqPyeeInf;
import com.david.util.dto.epcc20100101.Epcc20100101ReqPyerInf;
import com.david.util.dto.epcc20100101.Epcc20100101ReqResfdInf;
import com.david.util.dto.epcc20100101.Epcc20100101ReqTrxInf;
import com.david.util.dto.epcc20100101.Epcc20100101Request;
import com.david.util.dto.epcc20100101.Epcc20100101Response;

/**
 * 协议支付201
 * 
 * @author dailiwei
 *
 */
public class EpccCaseV2Test extends BasicEpccCase {

	/**
	 * 协议支付
	 * 
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
		pyerInf.setSgnNo(SGN_NO);
		pyerInf.setPyerTrxTrmTp(TrxTrmTpCd.MOBILE);
		pyerInf.setPyerTrxTrmNo("F93P72HCFR9M");

		Epcc20100101ReqPyeeInf pyeeInf = new Epcc20100101ReqPyeeInf();
		pyeeInf.setPyeeAcctIssrId(Z2006845000013);
		pyeeInf.setPyeeAcctId("282704424720582705");
		pyeeInf.setPyeeNm("abc@qq.com");
		pyeeInf.setPyeeAcctTp("04");
		pyeeInf.setPyeeCtryNo("CN");
		pyeeInf.setPyeeAreaNo("110102");
		pyeeInf.setPyeeTrxTrmTp(TrxTrmTpCd.BAR_CODE);
		pyeeInf.setPyeeTrxTrmNo("1D6AP1500");

		Epcc20100101ReqResfdInf resfdInf = new Epcc20100101ReqResfdInf();
		resfdInf.setInstgAcctId(RESFD_INF_INSTG_ACCTID);
		resfdInf.setInstgAcctNm("客户备付金");
		resfdInf.setResfdAcctIssrId(C1010611003601);

		Epcc20100101ReqTrxInf trxInf = new Epcc20100101ReqTrxInf();
		trxInf.setTrxId(EpccUtils.genTransSerialNo());
		trxInf.setTrxDtTm(new Date());
		trxInf.setTrxAmt("CNY" + new BigDecimal(RandomUtils.nextDouble(0, 1000)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		trxInf.setTrxCtgy("0110");
		trxInf.setBizTp("100002"); // 业务种类A3待补充
		trxInf.setAcctInTp(AcctInTpIdConstants.RECEIVABLES); // 收款扫码

		Epcc20100101ReqOriTrxInf orderInf = new Epcc20100101ReqOriTrxInf();
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
		logger.info("TrxStatus=" + response.getMsgBody().getBizInf().getTrxStatus());
		logger.info("BizStsCd=" + response.getMsgBody().getBizInf().getBizStsCd());
		logger.info("BizStsDesc=" + response.getMsgBody().getBizInf().getBizStsDesc());
		return response.getMsgBody().getBizInf().getBizStsCd();
	}

	/**
	 * case(201001~201003)
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101CaseTest() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals(SUCCESS_CODE, respCode);
	}

	/**
	 * case-201100
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201100Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520011", respCode);
	}

	/**
	 * case-201102
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201102Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB521013", respCode);
	}

	/**
	 * case-201103
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201103Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB521014", respCode);
	}

	/**
	 * case-201104
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201104Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB521015", respCode);
	}

	/**
	 * case-201105
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201105Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB521023", respCode);
	}

	/**
	 * case-201106
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201106Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB521024", respCode);
	}

	/**
	 * case-201107
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201107Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB521025", respCode);
	}

	/**
	 * case-201108
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201108Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520005", respCode);
	}

	/**
	 * case-201109
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201109Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520006", respCode);
	}

	/**
	 * case-201110
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201110Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520007", respCode);
	}

	/**
	 * case-201111
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201111Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520008", respCode);
	}

	/**
	 * case-201112
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201112Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520009", respCode);
	}

	/**
	 * case-201113
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201113Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520010", respCode);
	}

	/**
	 * case-201114
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201114Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB521021", respCode);
	}

	/**
	 * case-201115
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201115Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB521022", respCode);
	}

	/**
	 * case-201116
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201116Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB521026", respCode);
	}

	/**
	 * case-201117
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201117Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB521027", respCode);
	}

	/**
	 * case-201118
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201118Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB500018", respCode);
	}

	/**
	 * case-201119-failed
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201119Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("RB600005", respCode);
	}

	/**
	 * case-201120
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201120Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB500005", respCode);
	}

	/**
	 * case-201122
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201122Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB521096", respCode);
	}

	/**
	 * case-201124
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201124Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520095", respCode);
	}

	/**
	 * case-201125
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201125Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520017", respCode);
	}

	/**
	 * case-201126
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201126Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520097", respCode);
	}

	/**
	 * case-201127
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20100101Case201127Test() throws Exception {
		String respCode = doAgreementPayment();
		Assert.assertEquals("PB520032", respCode);
	}
}
