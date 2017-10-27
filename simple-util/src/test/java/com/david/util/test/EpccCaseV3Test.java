package com.david.util.test;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.david.util.BasicEpccCase;
import com.david.util.common.EpccUtils;
import com.david.util.common.JaxbUtils;
import com.david.util.common.RsaUtils;
import com.david.util.dto.MsgHeader;
import com.david.util.dto.BasicResfdInf;
import com.david.util.dto.BasicTrxInf;
import com.david.util.dto.RespReturnDTO;
import com.david.util.dto.epcc20500101.Epcc20500101ReqMsgBody;
import com.david.util.dto.epcc20500101.Epcc20500101ReqOriTrxInf;
import com.david.util.dto.epcc20500101.Epcc20500101ReqPyeeInf;
import com.david.util.dto.epcc20500101.Epcc20500101ReqPyerInf;
import com.david.util.dto.epcc20500101.Epcc20500101Request;
import com.david.util.dto.epcc20500101.Epcc20500101Response;

public class EpccCaseV3Test extends BasicEpccCase {

	private String doRefund() throws Exception {
		RespReturnDTO respReturnDTO = doRefundRespReturnDTO();
		String code = StringUtils.isBlank(respReturnDTO.getBizInf().getBizStsCd())
				? respReturnDTO.getSysRtnInf().getSysRtnCd()
				: respReturnDTO.getBizInf().getBizStsCd();
		return code;
	}

	private RespReturnDTO doRefundRespReturnDTO() throws Exception {
		// 产生随机aes256bit-32字节长度秘钥
		String aeskey = RandomStringUtils.randomAlphanumeric(32);
		logger.info("aeskey=" + aeskey + ",length=" + aeskey.length());
		// 使用网联平台公钥对该信封信息进行加密
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(certPath);
		logger.info("publicKey=" + publicKey);

		Epcc20500101Request request = new Epcc20500101Request();
		MsgHeader msgHeader = getMsgHeader(EPCC_205_001_01, "", "");
		Epcc20500101ReqMsgBody msgBody = new Epcc20500101ReqMsgBody();

		Epcc20500101ReqPyerInf pyerInf = new Epcc20500101ReqPyerInf();
		pyerInf.setPyerAcctIssrId(Z2006845000013);
		pyerInf.setPyerAcctId("282704424720582705");
		pyerInf.setPyerAcctTp(ACCT_TP_04);

		Epcc20500101ReqPyeeInf pyeeInf = new Epcc20500101ReqPyeeInf();
		pyeeInf.setPyeeAcctIssrId(C1010611003601);
		pyeeInf.setPyeeAcctTp(ACCT_TP_00);
		pyeeInf.setSgnNo(SGN_NO);

		BasicResfdInf resfdInf = new BasicResfdInf();
		resfdInf.setResfdAcctIssrId(C1010611003601);
		resfdInf.setInstgAcctNm("备付金账户");
		resfdInf.setInstgAcctId(RESFD_INF_INSTG_ACCTID);

		String amount = "CNY" + new BigDecimal(RandomUtils.nextDouble(0, 1000)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BasicTrxInf trxInf = new BasicTrxInf();
		trxInf.setTrxCtgy("0121");
		trxInf.setTrxId(EpccUtils.genTransSerialNo());
		trxInf.setTrxDtTm(new Date());
		trxInf.setTrxAmt(amount);

		Epcc20500101ReqOriTrxInf oriTrxInf = new Epcc20500101ReqOriTrxInf();
		oriTrxInf.setOriTrxId(EpccUtils.genTransSerialNo());
		oriTrxInf.setOriDbtrBankId("SM" + EpccUtils.genTransSerialNo());
		oriTrxInf.setOriTrxAmt(amount);

		msgBody.setRpflg("");
		msgBody.setBatchId(EpccUtils.genBatchId());
		msgBody.setPyerInf(pyerInf);
		msgBody.setPyeeInf(pyeeInf);
		msgBody.setResfdInf(resfdInf);
		msgBody.setTrxInf(trxInf);
		msgBody.setOriTrxInf(oriTrxInf);
		request.setMsgHeader(msgHeader);
		request.setMsgBody(msgBody);

		String result = postToEpccGateway(EPCC_PROT_443_URL, EPCC_205_001_01, request);

		String responseStr = EpccUtils.getResponseStr(result);
		String responseSignStr = EpccUtils.getResponseSign(result);

		// 验签
		boolean verifySign = RsaUtils.vertify(publicKey, responseStr, responseSignStr);
		logger.info("verifySign=" + verifySign);
		Assert.assertTrue(verifySign);

		// 组装成对应的JavaBean
		Epcc20500101Response response = JaxbUtils.toBean(String.format("%s%s", JaxbUtils.XML_HEADER, responseStr), Epcc20500101Response.class);
		logger.info(response.toString());
		// Assert.assertEquals(SUCCESS_CODE,
		// response.getMsgBody().getSysRtnInf().getSysRtnCd());
		logger.info("TrxStatus=" + response.getMsgBody().getBizInf().getTrxStatus());
		logger.info("BizStsCd=" + response.getMsgBody().getBizInf().getBizStsCd());
		logger.info("BizStsDesc=" + response.getMsgBody().getBizInf().getBizStsDesc());

		RespReturnDTO respReturnDTO = new RespReturnDTO();
		respReturnDTO.setSysRtnInf(response.getMsgBody().getSysRtnInf());
		respReturnDTO.setBizInf(response.getMsgBody().getBizInf());

		return respReturnDTO;
	}

	/**
	 * case 205001~ case 205003
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals(SUCCESS_CODE, respCode);
	}

	/**
	 * case 205100
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205100Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB622021", respCode);
	}

	/**
	 * case 205101
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205101Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB622024", respCode);
	}

	/**
	 * case 205102
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205102Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB622024", respCode);
	}

	/**
	 * case 205103
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205103Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB622025", respCode);
	}

	/**
	 * case 205104
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205104Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB622026", respCode);
	}

	/**
	 * case 205105
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205105Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB622022", respCode);
	}

	/**
	 * case 205106
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205106Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB622023", respCode);
	}

	/**
	 * case 205107
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205107Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB620005", respCode);
	}

	/**
	 * case 205108
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205108Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB620006", respCode);
	}

	/**
	 * case 205109
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205109Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB620007", respCode);
	}

	/**
	 * case 205110
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205110Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB620008", respCode);
	}

	/**
	 * case 205111
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205111Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB620009", respCode);
	}

	/**
	 * case 205112
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205112Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB620010", respCode);
	}

	/**
	 * case 205113
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205113Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520011", respCode);
	}

	/**
	 * case 205114
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205114Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PS500002", respCode);
	}

	/**
	 * case 205115
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205115Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RS600002", respCode);
	}

	/**
	 * case 205116
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205116Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB522099", respCode);
	}

	/**
	 * case 205117
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205117Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB622099", respCode);
	}

	/**
	 * case 205118
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205118Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB620095", respCode);
	}

	/**
	 * case 205119
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205119Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB620097", respCode);
	}

	/**
	 * case 205120
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205120Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520005", respCode);
	}

	/**
	 * case 205121
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205121Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520006", respCode);
	}

	/**
	 * case 205122
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205122Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520007", respCode);
	}

	/**
	 * case 205123
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101CasePB520008Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520008", respCode);
	}

	/**
	 * case 205124
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205124Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520009", respCode);
	}

	/**
	 * case 205125
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205125Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520010", respCode);
	}

	/**
	 * case 205126
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205126Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520095", respCode);
	}

	/**
	 * case 205127
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205127Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520017", respCode);
	}

	/**
	 * case 205128
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205128Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520018", respCode);
	}

	/**
	 * case 205129
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205129Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520097", respCode);
	}

	/**
	 * case 205130
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205130Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PB520032", respCode);
	}

	/**
	 * case 205131
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205131Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RB620032", respCode);
	}

	/**
	 * case 205132
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205132Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PS500001", respCode);
	}

	/**
	 * case 205133
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205133Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PS500003", respCode);
	}

	/**
	 * case 205134
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205134Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("PS500004", respCode);
	}

	/**
	 * case 205135
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205135Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RS600001", respCode);
	}

	/**
	 * case 205136
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205136Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RS600003", respCode);
	}

	/**
	 * case 205137
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205137Test() throws Exception {
		String respCode = doRefund();
		Assert.assertEquals("RS600004", respCode);
	}

	/**
	 * case 205138
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205138Test() throws Exception {
		RespReturnDTO respReturnDTO = doRefundRespReturnDTO();
		Assert.assertEquals("ES000098", respReturnDTO.getSysRtnInf().getSysRtnCd());
		Assert.assertEquals("PS500030", respReturnDTO.getBizInf().getBizStsDesc());
	}

	/**
	 * case 205138
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205139Test() throws Exception {
		RespReturnDTO respReturnDTO = doRefundRespReturnDTO();
		Assert.assertEquals("ES000098", respReturnDTO.getSysRtnInf().getSysRtnCd());
		Assert.assertEquals("PS500030", respReturnDTO.getBizInf().getBizStsDesc());
	}
	
	/**
	 * case 205140
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205140Test() throws Exception {
		RespReturnDTO respReturnDTO = doRefundRespReturnDTO();
		Assert.assertEquals("ES000099", respReturnDTO.getSysRtnInf().getSysRtnCd());
		Assert.assertEquals("PS500031", respReturnDTO.getBizInf().getBizStsDesc());
	}
	
	/**
	 * case 205141
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc20500101Case205141Test() throws Exception {
		RespReturnDTO respReturnDTO = doRefundRespReturnDTO();
		Assert.assertEquals("ES000098", respReturnDTO.getSysRtnInf().getSysRtnCd());
		Assert.assertEquals("RS600030", respReturnDTO.getBizInf().getBizStsDesc());
	}
}
