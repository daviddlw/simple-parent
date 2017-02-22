package com.david.test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.david.dto.Constants;
import com.david.dto.baofoo.BaoFooCommonReqDTO;
import com.david.dto.baofoo.BaoFooPaymentDTO;
import com.david.enums.baofoo.PayCodeEnum;
import com.david.enums.baofoo.TxnSubTypeEnum;
import com.david.utils.CommonUtil;
import com.david.utils.HttpUtils;
import com.david.utils.RsaCodingUtil;
import com.david.utils.RsaReadUtil;

public class TestBaoFooV4 {

	private static final String BFKEY_100000276_100000991_CER = "bfkey_100000276@@100000991.cer";
	private static final String BFKEY_100000276_100000991_PFX = "bfkey_100000276@@100000991.pfx";
	private static final String TXN_TYPE = "0431";
	private static final String VERSION = "4.0.0.0";
	private static final String DCP_MEMBER_ID = "100000276";
	private static final String DCP_TERMINAL_ID = "100000991";
	private static final String DATA_TYPE = "json";
	private static final String BIZ_TYPE = "0000";

	private BaoFooCommonReqDTO getAuthRequestDCP(TxnSubTypeEnum type) {
		BaoFooCommonReqDTO request = new BaoFooCommonReqDTO();
		request.setVersion(VERSION);
		request.setMember_id(DCP_MEMBER_ID);
		request.setTerminal_id(DCP_TERMINAL_ID);
		request.setTxn_type(TXN_TYPE);
		request.setTxn_sub_type(type.getCode());
		request.setData_type(DATA_TYPE);

		return request;
	}

	/**
	 * 测试新版代付接口
	 */
	@Test
	public void testDCPPayment() {

		BaoFooCommonReqDTO request = getAuthRequestDCP(TxnSubTypeEnum.DCPPaymentTrade);

		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		BaoFooPaymentDTO dataContent = new BaoFooPaymentDTO();
		dataContent.setTxn_sub_type(TxnSubTypeEnum.DCPPaymentTrade.getCode());
		dataContent.setBiz_type(BIZ_TYPE);
		dataContent.setMember_id(DCP_MEMBER_ID);
		dataContent.setTerminal_id(DCP_TERMINAL_ID);
		dataContent.setPay_code(PayCodeEnum.PAB.getCode());
		dataContent.setPay_cm("1");
		String acc_no = "6228480444455553333";
		dataContent.setAcc_no(acc_no);
		dataContent.setId_card_type("01");
		dataContent.setId_card("320301198502169142");
		dataContent.setId_holder("王宝");
		dataContent.setMobile("13661896734");
		String transId = String.valueOf(System.currentTimeMillis());
		System.out.println("transId: " + transId);
		dataContent.setTrans_id(transId);
		dataContent.setTxn_amt("1");
		dataContent.setTrade_date(tradeDate);
		dataContent.setTrans_serial_no("TISN" + System.currentTimeMillis());
		dataContent.setAdditional_info("david支付类交易通知");
		dataContent.setReq_reserved("david支付类交易通知");

		doWorkDCP(dataContent, request);
	}

	@Test
	public void testDCPQueryPayment() throws UnsupportedEncodingException {
		BaoFooCommonReqDTO request = getAuthRequestDCP(TxnSubTypeEnum.QueryPaymentStateTrade);

		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		BaoFooPaymentDTO dataContent = new BaoFooPaymentDTO();
		dataContent.setTxn_sub_type(TxnSubTypeEnum.QueryPaymentStateTrade.getCode());
		dataContent.setBiz_type(BIZ_TYPE);
		dataContent.setTerminal_id(DCP_TERMINAL_ID);
		dataContent.setMember_id(DCP_MEMBER_ID);
		dataContent.setTrans_serial_no("TISN" + System.currentTimeMillis());
		dataContent.setTrade_date(tradeDate);
		dataContent.setAdditional_info("david支付类交易通知");
		dataContent.setReq_reserved("david支付类交易通知");
		dataContent.setOrig_trans_id("1470724714559");

		doWorkDCP(dataContent, request);
	}

	private void doWorkDCP(BaoFooPaymentDTO dataContent, BaoFooCommonReqDTO request) {
		try {

			String tempReqdata = JSON.toJSONString(dataContent);
			String origData = new String(Base64.encodeBase64(tempReqdata.getBytes(Charset.forName(Constants.UTF_8))));
			String encryptData = RsaCodingUtil.encryptByPrivateKey(origData, getPrivateKeyDCP());

			System.out.println("----------->私钥加密结果：" + encryptData);

			request.setData_content(encryptData);
			String requestBody = JSON.toJSONString(request);
			Map<String, String> requestParams = CommonUtil.stringToMap(requestBody);

			String encryptPostResult = HttpUtils.httpPostNoBody(Constants.REQUEST_URL, requestParams);

			System.out.println(encryptPostResult);
			String postResult = RsaCodingUtil.decryptByPublicKey(encryptPostResult, getPublicKeyDCP());

			String unBase64Data = new String(Base64.decodeBase64(postResult.getBytes(Constants.UTF_8)));
			System.out.println(unBase64Data);

			Map<String, String> resultMap = CommonUtil.stringToMap(unBase64Data);
			System.err.println(resultMap);
			System.err.println("resp_code: " + resultMap.get("resp_code") + ", resp_msg: " + resultMap.get("resp_msg"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private String getPrivateKeyDCP() {
		String keyStorePath = "Q:" + File.separator + "certificate" + File.separator + "baofoo_dcp" + File.separator + BFKEY_100000276_100000991_PFX;
		String keyStorePassword = "123456";

		PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(keyStorePath, keyStorePassword);
		return Base64.encodeBase64String(privateKey.getEncoded());
	}

	private String getPublicKeyDCP() {
		String pubPath = "Q:" + File.separator + "certificate" + File.separator + "baofoo_dcp" + File.separator + BFKEY_100000276_100000991_CER;
		PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubPath);
		return Base64.encodeBase64String(publicKey.getEncoded());
	}

}
