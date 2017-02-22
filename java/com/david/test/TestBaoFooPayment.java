package com.david.test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import com.david.dto.Constants;
import com.david.dto.baofoo.BaoFooCommonReqDTO;
import com.david.dto.baofoo.BaoFooPaymentDTO;
import com.david.dto.baofoo.FastUser;
import com.david.enums.baofoo.PayCodeEnum;
import com.david.enums.baofoo.TxnSubTypeEnum;
import com.david.utils.CommonUtil;
import com.david.utils.HttpUtils;
import com.david.utils.RsaCodingUtil;
import com.david.utils.RsaReadUtil;

/**
 * 测试宝付认证（认证绑卡+支付）
 * 
 * @author dailiwei
 *
 */
public class TestBaoFooPayment {

	private static final String CALLBACK_TERMINAL_ID = "100000994";
	private static final String CALLBACK_MEMBER_ID = "100000276";
	private static final String BFKEY_100000276_100000994_CER = "bfkey_100000276@@100000994.cer";
	private static final String BFKEY_100000276_100000994_PFX = "bfkey_100000276@@100000994.pfx";
	private static final String BFKEY_REAL_PFX = "baofoo_new_pri.pfx";
	private static final String BFKEY_REAL_CER = "bfkey_795052@@28821.cer";
	private static final String REQUEST_URL_REAL = "https://public.baofoo.com/cutpayment/api/backTransRequest";
	private static final String TXN_TYPE = "0431";
	private static final String VERSION = "4.0.0.0";
	private static final String TEST_MEMBER_ID = "795052"; // 商户号
															// 100000749-100000276
	private static final String TEST_TERMINAL_ID = "28821"; // 终端号
															// 100000933-100000994

	private static final String ERROR_TERMINAL_ID = "28822";
	private static final String TEST_BIZ_TYPE = "0000";
	private static final String DATA_TYPE = "json";
	private static final String MOBILE = "13661896734";

	private static final String KEY_STORE_PASSWORD = "Hengda888";
	private static final String baofooErrorPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANHA6dosVBBy09Rq8BG6i61pU4WtQtKfRebQQaXJ6cMDcMx7+cFBVi+gSy4/5H/MsUJeXuiPL4otTEUWaconIcC7qN2Q8ummjZNiWDBtn7RSnUZlN825sbu9H2HuEwsCcIqd99JDGktHFRP3KSjjhGo5lQjAl82YCTXCDyP7BubxAgMBAAECgYEAtOAISK0lKLH8XRaCQqd8v2MuNP39u6bBHpLDqXV+cT5MTOkzrCTtb1BoxE8wecxHpxW9/glLU3pQZNs5CqvH+9z68a9NMNWcWVYQHuiNr2LOOsdrNG0c8AOOx2Cx66x1rcZo7P6y1Q2UpK5ii3Fuw1QEOUctZIcXPVMp5Mh/m+ECQQD19kOBL0o8g1EPjf5QW5LYywS0XMlvFx8CKBwwQVpsKv6zRcKNRVRnNpJDa0zdpbCZl+yfcDphrcw9diLM7w/9AkEA2lBa+k6LJzE2JTHe3ufQC4e+EGPh4+pE4p3bpUwz1Us8J55ug0CakociOe/80bQM6pRK1cG32PrTJvpu0ZwjBQJAKTNhR2iGhPBySaq9aPSN+qa3WQcZ7vPui8vwPN0VqnzhQWJ0Hnn4k3l+srBspcw1cuUSC/serHISflElrzJ0eQJAepAsU2RvGQS2yjDyV6A7G44MtXTt55+pJrTLEET0wKVpUzDOCF/np6W4IGDMRgrYSYYRcWo9RdKjYrmsJ3+J5QJAW+r+FnafFaFnuo4uJwbBZzW6bSVyXIzXW9izEJh+WmxdnTVlHL953yqB5zdv0QIncRjQqnBjrOTb62psqM1g/Q==";
	private static final String baofooErrorPrivateKey2 = "IICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMrH/C/W9hpD1nifR6xvj9p/7Ydxq1Zr5B8MD+nWOHPXFRKvENyBTafFN5sxBJacWQfpmYPvRvMHPWeiQygxZSXIapxHaTZUR73q7D+S+y9VZqaGp31T/eYPByvW52tAoiuBJgVber4Aq/hC4C+sN0s3pLkF2teQbTGKf1dum/WrAgMBAAECgYEAoKt6RxGEp+TuT16s9JTkn1TTiIjpIC2Wb38oMLg6vJZDrS0nKAAP3oml+IILR6aT6HAP4fi595/Z6H4pxfP1VmRs/tF2npd51SMp/omzOCeUo93gKpgPeOUqf5pknQhFmIHa6OFaAZkMAIJvpU07rIRTJ4ZY3ICgybSgE3Z5qNkCQQDnuLSJeqGtXKcgEYV3/zBxmKUaXIjixx5LEXzDrNavybbuzrBcIhvPStxfmrUMIGERatw4MklR//Y2CJy6mAb9AkEA4AcJBabCm+cRbHRDbBG5DrOeNY4LfSYw7JIymmI3JdjAk0BKXzw45I6hOi57Wwe8ha6o6ua5QXPINmMO5RzTxwJAWWBW0kgooDOCis33Swd06fvUgsx6R+5Bcvb1xoud6wPEuWujfoXxcA0R3xZPCrRafFtZQZuzyBsQHltICHinCQJATVYgZzMKO4epHCeGnuf/WnPnSdWfYbStbv2JOoJtR0NsN2kaQ4YmncAfKFajfulMy1lgNrcvfMjbdOr9WVp6QQJBALRJ8AY93sR6T2y3SXseYykFxhGEspkk7pHCcW96kI+YSuaOGB5UgmtKrgoOetmDcaY91brxmadVZ7Ts3OJix6k=";

	/**
	 * 测试绑卡交易
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testBindCard() throws UnsupportedEncodingException {
		BaoFooCommonReqDTO request = getAuthRequest(TxnSubTypeEnum.BindCardTrade);

		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		BaoFooPaymentDTO dataContent = new BaoFooPaymentDTO();
		dataContent.setTxn_sub_type(TxnSubTypeEnum.BindCardTrade.getCode());
		dataContent.setBiz_type(TEST_BIZ_TYPE);
		dataContent.setTerminal_id(TEST_TERMINAL_ID);
		dataContent.setMember_id(TEST_MEMBER_ID);

		String transNo = "TISN" + System.currentTimeMillis();
		dataContent.setTrans_serial_no(transNo);
		dataContent.setTrade_date(tradeDate);
		dataContent.setAdditional_info("david绑卡交易通知");
		dataContent.setReq_reserved("david绑卡交易通知");

		String acc_no = "6230580000077369564";
		System.out.println("acc_no: " + acc_no);
		String transId = TestHelper.getNewUUID();
		System.out.println("transId: " + transId);

		dataContent.setAcc_no(acc_no);
		dataContent.setTrans_id(transId);
		dataContent.setId_card_type("01");
		dataContent.setId_card("310112198801105612");
		dataContent.setId_holder("戴励维");
		dataContent.setMobile(MOBILE);
		dataContent.setPay_code(PayCodeEnum.PAB.getCode());
		doWork(dataContent, request);

	}

	/**
	 * 测试解绑卡交易
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testUnBindCard() throws UnsupportedEncodingException {
		BaoFooCommonReqDTO request = getAuthRequest(TxnSubTypeEnum.UnBindCardTrade);

		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		BaoFooPaymentDTO dataContent = new BaoFooPaymentDTO();
		dataContent.setTxn_sub_type(TxnSubTypeEnum.UnBindCardTrade.getCode());
		dataContent.setBiz_type(TEST_BIZ_TYPE);
		dataContent.setTerminal_id(TEST_TERMINAL_ID);
		dataContent.setMember_id(TEST_MEMBER_ID);
		String tranSerialNo = "TISN" + System.currentTimeMillis();
		dataContent.setTrans_serial_no(tranSerialNo);
		dataContent.setTrade_date(tradeDate);
		dataContent.setAdditional_info("david解除绑卡交易通知");
		dataContent.setReq_reserved("david解除绑卡交易通知");
		dataContent.setBind_id("201603261412121000009649074");

		doWork(dataContent, request);
	}

	/**
	 * 测试查询绑卡关系交易
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testQueryBindCard() throws UnsupportedEncodingException {
		BaoFooCommonReqDTO request = getCallbackAuthRequest(TxnSubTypeEnum.QueryBindCard);

		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		BaoFooPaymentDTO dataContent = new BaoFooPaymentDTO();
		dataContent.setTxn_sub_type(TxnSubTypeEnum.QueryBindCard.getCode());
		dataContent.setBiz_type(TEST_BIZ_TYPE);
		dataContent.setTerminal_id(CALLBACK_TERMINAL_ID);
		dataContent.setMember_id(CALLBACK_MEMBER_ID);
		dataContent.setTrans_serial_no("TISN" + System.currentTimeMillis());
		dataContent.setTrade_date(tradeDate);
		dataContent.setAdditional_info("david查询绑卡信息交易通知");
		dataContent.setReq_reserved("david查询绑卡信息交易通知");
		dataContent.setAcc_no("6230580000077369564");

		doWork(dataContent, request);
	}

	/**
	 * 测试支付类交易接口
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testPayment() throws UnsupportedEncodingException {
		BaoFooCommonReqDTO request = getAuthRequest(TxnSubTypeEnum.PaymentTrade);

		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		BaoFooPaymentDTO dataContent = new BaoFooPaymentDTO();
		dataContent.setTxn_sub_type(TxnSubTypeEnum.PaymentTrade.getCode());
		dataContent.setBiz_type(TEST_BIZ_TYPE);
		dataContent.setTerminal_id(TEST_TERMINAL_ID);
		dataContent.setMember_id(TEST_MEMBER_ID);
		dataContent.setTrans_serial_no("TISN" + System.currentTimeMillis());
		dataContent.setTrade_date(tradeDate);
		dataContent.setAdditional_info("david支付类交易通知");
		dataContent.setReq_reserved("david支付类交易通知");
		String transId = TestHelper.getNewUUID();
		System.out.println("transId: " + transId);
		dataContent.setTrans_id(transId);
		dataContent.setBind_id("201603261412121000009649074");
		dataContent.setTxn_amt("1");
		dataContent.setSms_code("123456");

		doWork(dataContent, request);
	}

	private BaoFooCommonReqDTO getAuthRequest(TxnSubTypeEnum type) {
		BaoFooCommonReqDTO request = new BaoFooCommonReqDTO();
		request.setVersion(VERSION);
		request.setMember_id(TEST_MEMBER_ID);
		request.setTerminal_id(TEST_TERMINAL_ID);
		request.setTxn_type(TXN_TYPE);
		request.setTxn_sub_type(type.getCode());
		request.setData_type(DATA_TYPE);

		return request;
	}

	private BaoFooCommonReqDTO getCallbackAuthRequest(TxnSubTypeEnum type) {
		BaoFooCommonReqDTO request = new BaoFooCommonReqDTO();
		request.setVersion(VERSION);
		request.setMember_id(CALLBACK_MEMBER_ID);
		request.setTerminal_id(CALLBACK_TERMINAL_ID);
		request.setTxn_type(TXN_TYPE);
		request.setTxn_sub_type(type.getCode());
		request.setData_type(DATA_TYPE);

		return request;
	}

	/**
	 * 使用错误请求头
	 * 
	 * @param type
	 * @return
	 */
	private BaoFooCommonReqDTO getErrorAuthRequest(TxnSubTypeEnum type) {
		BaoFooCommonReqDTO request = new BaoFooCommonReqDTO();
		request.setVersion(VERSION);
		request.setMember_id(TEST_MEMBER_ID);
		request.setTerminal_id(ERROR_TERMINAL_ID);
		request.setTxn_type(TXN_TYPE);
		request.setTxn_sub_type(type.getCode());
		request.setData_type(DATA_TYPE);

		return request;
	}

	/**
	 * 测试发送短信类交易
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testSendSms() throws UnsupportedEncodingException {

		BaoFooCommonReqDTO request = getAuthRequest(TxnSubTypeEnum.SmsTrade);

		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		BaoFooPaymentDTO dataContent = new BaoFooPaymentDTO();
		dataContent.setTxn_sub_type(TxnSubTypeEnum.SmsTrade.getCode());
		dataContent.setBiz_type(TEST_BIZ_TYPE);
		dataContent.setTerminal_id(TEST_TERMINAL_ID);
		dataContent.setMember_id(TEST_MEMBER_ID);
		dataContent.setTrans_serial_no("TISN" + System.currentTimeMillis());
		dataContent.setTrade_date(tradeDate);
		dataContent.setAdditional_info("david短信交易通知");
		dataContent.setReq_reserved("david保留短信交易通知");

		String transId = TestHelper.getNewUUID();
		System.out.println("transId: " + transId);
		dataContent.setTrans_id(transId);
		dataContent.setMobile(MOBILE);
		String accNo = TestHelper.getICBCDebitCardNo();
		System.out.println(accNo);
		dataContent.setAcc_no(accNo);
		dataContent.setNext_txn_sub_type(TxnSubTypeEnum.BindCardTrade.getCode());

		doWork(dataContent, request);

	}

	/**
	 * 查询支付交易状态
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testCallbackQueryPaymentState() throws UnsupportedEncodingException {
		BaoFooCommonReqDTO request = getCallbackAuthRequest(TxnSubTypeEnum.QueryPaymentStateTrade);

		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		BaoFooPaymentDTO dataContent = new BaoFooPaymentDTO();
		dataContent.setTxn_sub_type(TxnSubTypeEnum.QueryPaymentStateTrade.getCode());
		dataContent.setBiz_type(TEST_BIZ_TYPE);
		dataContent.setTerminal_id(CALLBACK_TERMINAL_ID);
		dataContent.setMember_id(CALLBACK_MEMBER_ID);
		dataContent.setTrans_serial_no("TISN" + System.currentTimeMillis());
		dataContent.setTrade_date(tradeDate);
		dataContent.setAdditional_info("david支付类交易通知");
		dataContent.setReq_reserved("david支付类交易通知");
		dataContent.setBind_id("201604271946051306");
		dataContent.setOrig_trans_id("QAP00000000000023256");

		doWork(dataContent, request);
	}

	@Test
	public void testQueryPaymentState() throws UnsupportedEncodingException {
		BaoFooCommonReqDTO request = getAuthRequest(TxnSubTypeEnum.QueryPaymentStateTrade);

		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		BaoFooPaymentDTO dataContent = new BaoFooPaymentDTO();
		dataContent.setTxn_sub_type(TxnSubTypeEnum.QueryPaymentStateTrade.getCode());
		dataContent.setBiz_type(TEST_BIZ_TYPE);
		dataContent.setTerminal_id(TEST_TERMINAL_ID);
		dataContent.setMember_id(TEST_MEMBER_ID);
		dataContent.setTrans_serial_no("TISN" + System.currentTimeMillis());
		dataContent.setTrade_date(tradeDate);
		dataContent.setAdditional_info("david支付类交易通知");
		dataContent.setReq_reserved("david支付类交易通知");
		dataContent.setBind_id("201604271946051306");
		dataContent.setOrig_trans_id("QAP00000000000023256");

		doWork(dataContent, request);
	}

	/**
	 * 宝付定期
	 */
	@Test
	public void testPubAndPrivateKey795045() {
		System.out.println(getPrivateKeyBy795045());
		System.out.println(getPublicKey795045());
	}

	private String getPrivateKeyBy795045() {
		String keyStorePath = "C:" + File.separator + "baofoo_cert" + File.separator + "dingqi" + File.separator + "baofoo_795045_pri.pfx";
		String keyStorePassword = "Hengda888";

		PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(keyStorePath, keyStorePassword);
		return Base64.encodeBase64String(privateKey.getEncoded());
	}

	private String getPublicKey795045() {
		String pubPath = "C:" + File.separator + "baofoo_cert" + File.separator + "dingqi" + File.separator + "bfkey_795045@@29302.cer";
		PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubPath);
		return Base64.encodeBase64String(publicKey.getEncoded());
	}

	/**
	 * 宝付活期
	 */
	@Test
	public void testPubAndPrivateKey795048() {
		System.out.println(getPrivateKeyBy795048());
		System.out.println(getPublicKey795048());
	}

	private String getPrivateKeyBy795048() {
		String keyStorePath = "C:" + File.separator + "baofoo_cert" + File.separator + "huoqi" + File.separator + "baofoo_795048_pri.pfx";
		String keyStorePassword = "Hengda888";

		PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(keyStorePath, keyStorePassword);
		return Base64.encodeBase64String(privateKey.getEncoded());
	}

	private String getPublicKey795048() {
		String pubPath = "C:" + File.separator + "baofoo_cert" + File.separator + "huoqi" + File.separator + "bfkey_795048@@29306.cer";
		PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubPath);
		return Base64.encodeBase64String(publicKey.getEncoded());
	}

	private String getPrivateKeyBy795052() {
		String keyStorePath = "Q:" + File.separator + "certificate" + File.separator + "baofoo" + File.separator + "baofoo_new_pri.pfx";
		String keyStorePassword = "Hengda123";

		PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(keyStorePath, keyStorePassword);
		return Base64.encodeBase64String(privateKey.getEncoded());
	}

	private String getPublicKey795052() {
		String pubPath = "Q:" + File.separator + "certificate" + File.separator + "baofoo" + File.separator + "bfkey_795052@@28821.cer";
		PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubPath);
		return Base64.encodeBase64String(publicKey.getEncoded());
	}

	@Test
	public void testKeys2() {
		System.err.println("pfx: ");
		System.err.println(getPrivateKeyBy795052());
		System.err.println("cer: ");
		System.err.println(getPublicKey795052());
	}

	/**
	 * 获取参数化map
	 * 
	 * @param paramMap
	 * @return
	 */
	private String getMerchantInfo(Map<String, String> paramMap) {
		paramMap.put("baofooPrivateKey", getPrivateKey());
		paramMap.put("baofooPublicKey", getPublicKey());
		paramMap.put("baofooTerminalKey", "28821");
		return JSON.toJSONString(paramMap, false);
	}

	@Test
	public void testVerifyKey() throws UnsupportedEncodingException {
		BaoFooCommonReqDTO request = getAuthRequest(TxnSubTypeEnum.QueryBindCard);

		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		BaoFooPaymentDTO dataContent = new BaoFooPaymentDTO();
		dataContent.setTxn_sub_type(TxnSubTypeEnum.QueryBindCard.getCode());
		dataContent.setBiz_type(TEST_BIZ_TYPE);
		dataContent.setTerminal_id(TEST_TERMINAL_ID);
		dataContent.setMember_id(TEST_MEMBER_ID);
		dataContent.setTrans_serial_no("TISN" + System.currentTimeMillis());
		dataContent.setTrade_date(tradeDate);
		dataContent.setAdditional_info("david查询绑卡信息交易通知");
		dataContent.setReq_reserved("david查询绑卡信息交易通知");
		dataContent.setAcc_no("6230580000077369564");

		doErrorWork(dataContent, request);
	}

	/**
	 * 获取私钥方法
	 * 
	 * @return
	 * @throws IOException
	 */
	private String getPrivateKey() {
		String keyStorePath = "Q:" + File.separator + "certificate" + File.separator + "baofoo" + File.separator + BFKEY_REAL_PFX;
		String keyStorePassword = "Hengda123";

		PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(keyStorePath, keyStorePassword);
		return Base64.encodeBase64String(privateKey.getEncoded());
	}

	private String getErrorPrivateKey() {
		String keyStorePath = "Q:" + File.separator + "baofu_cert_auth" + File.separator + BFKEY_REAL_CER;
		String keyStorePassword = "Hengda8889";

		// PrivateKey privateKey =
		// RsaReadUtil.getPrivateKeyFromFile(keyStorePath, keyStorePassword);
		PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(BFKEY_REAL_CER, keyStorePassword);
		System.out.println(privateKey);
		if (privateKey != null) {
			return Base64.encodeBase64String(privateKey.getEncoded());
		}

		return "";
	}

	private String getPublicKey() {
		String pubPath = "Q:" + File.separator + "certificate" + File.separator + "baofoo" + File.separator + BFKEY_REAL_CER;
		PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubPath);
		return Base64.encodeBase64String(publicKey.getEncoded());
	}

	private String getErrorPublicKey() {
		return "0123456";
	}

	private String getPrivateKeyNew() {
		String keyStorePath = "Q:" + File.separator + "certificate" + File.separator + "baofoo" + File.separator + BFKEY_100000276_100000994_PFX;
		String keyStorePassword = "123456";

		PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(keyStorePath, keyStorePassword);
		return Base64.encodeBase64String(privateKey.getEncoded());
	}

	private String getPublicKeyNew() {
		String pubPath = "Q:" + File.separator + "certificate" + File.separator + "baofoo" + File.separator + BFKEY_100000276_100000994_CER;
		PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubPath);
		return Base64.encodeBase64String(publicKey.getEncoded());
	}

	private void doErrorWork(BaoFooPaymentDTO dataContent, BaoFooCommonReqDTO request) {
		try {

			String tempReqdata = JSON.toJSONString(dataContent);
			String origData = new String(Base64.encodeBase64(tempReqdata.getBytes(Charset.forName(Constants.UTF_8))));
			String encryptData = RsaCodingUtil.encryptByPrivateKey(origData, baofooErrorPrivateKey);

			System.out.println("----------->私钥加密结果：" + encryptData);

			request.setData_content(encryptData);
			String requestBody = JSON.toJSONString(request);
			Map<String, String> requestParams = CommonUtil.stringToMap(requestBody);

			String encryptPostResult = HttpUtils.httpPostNoBody(REQUEST_URL_REAL, requestParams);

			System.out.println(encryptPostResult);
			String postResult = RsaCodingUtil.decryptByPublicKey(encryptPostResult, getPublicKey());
			String unBase64Data = new String(Base64.decodeBase64(postResult.getBytes(Constants.UTF_8)));
			System.out.println(unBase64Data);

			Map<String, String> resultMap = CommonUtil.stringToMap(unBase64Data);
			System.err.println(resultMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 公钥加解密
	 * 
	 * @param dataContent
	 * @param request
	 * @throws Exception
	 */
	private void doWork(BaoFooPaymentDTO dataContent, BaoFooCommonReqDTO request) {
		try {

			String tempReqdata = JSON.toJSONString(dataContent);
			String origData = new String(Base64.encodeBase64(tempReqdata.getBytes(Charset.forName(Constants.UTF_8))));
			String encryptData = RsaCodingUtil.encryptByPrivateKey(origData, getPrivateKeyNew());

			System.out.println("----------->私钥加密结果：" + encryptData);

			request.setData_content(encryptData);
			String requestBody = JSON.toJSONString(request);
			Map<String, String> requestParams = CommonUtil.stringToMap(requestBody);

			String encryptPostResult = HttpUtils.httpPostNoBody(Constants.UTF_8, requestParams);

			System.out.println(encryptPostResult);
			String postResult = RsaCodingUtil.decryptByPublicKey(encryptPostResult, getPublicKeyNew());

			String unBase64Data = new String(Base64.decodeBase64(postResult.getBytes(Constants.UTF_8)));
			System.out.println(unBase64Data);

			Map<String, String> resultMap = CommonUtil.stringToMap(unBase64Data);
			System.err.println(resultMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void TestFastJson() {
		FastUser fastUser = new FastUser();
		fastUser.setUserId("888");
		fastUser.setUserName("戴维测试");
		String result = JSON.toJSONString(fastUser);
		System.out.println(result);

		FastUser newUser = JSON.parseObject(result, FastUser.class);
		System.out.println(newUser);
	}

	@Test
	public void fastUserTest()
			throws NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Map<String, String> reqMap = new HashMap<>();
		reqMap.put("user_id", "123");
		reqMap.put("user_name", "大卫科波菲尔");

		System.out.println(CommonUtil.getGenericTypeObjectWithMap(FastUser.class, reqMap));
	}

	@Test
	public void testDCPQueryPayment() throws UnsupportedEncodingException {
		BaoFooCommonReqDTO request = getCallbackAuthRequest(TxnSubTypeEnum.QueryPaymentStateTrade);

		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		BaoFooPaymentDTO dataContent = new BaoFooPaymentDTO();
		dataContent.setTxn_sub_type(TxnSubTypeEnum.QueryPaymentStateTrade.getCode());
		dataContent.setBiz_type(TEST_BIZ_TYPE);
		dataContent.setTerminal_id(CALLBACK_TERMINAL_ID);
		dataContent.setMember_id(CALLBACK_MEMBER_ID);
		dataContent.setTrans_serial_no("TISN" + System.currentTimeMillis());
		dataContent.setTrade_date(tradeDate);
		dataContent.setAdditional_info("david支付类交易通知");
		dataContent.setReq_reserved("david支付类交易通知");
		dataContent.setOrig_trans_id("715148ce2199486b9dc14e5272c429fe");

		doWork(dataContent, request);
	}

}
