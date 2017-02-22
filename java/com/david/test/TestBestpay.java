package com.david.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.david.dto.bestpay.BestPayData;
import com.david.dto.bestpay.BestQueryPayData;
import com.david.dto.bestpay.BestQueryPayRsData;
import com.david.dto.bestpay.CertResponse;
import com.david.dto.bestpay.CustInfoPayRequest;
import com.david.dto.bestpay.CustInfoQueryRequest;
import com.david.dto.bestpay.CustInfoRequest;
import com.david.dto.bestpay.SimulationResponse;
import com.david.enums.bestpay.Constants;
import com.david.enums.bestpay.CurrentCodeEnum;
import com.david.utils.AESUtil;
import com.david.utils.HttpUtils;
import com.david.utils.ObjectJsonUtil;
import com.david.utils.RandomUtil;
import com.david.utils.RsaCipher;

public class TestBestpay {

	private static final String TEST_LOGIN_CODE = "zhonglian";
	private static final String TEST_PLAT_CODE = "0020000000093003";
	private Logger logger = Logger.getLogger(TestBestpay.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final String QUICK_VALIDATE_URL = "http://183.62.49.171:9090/fas/quickValidate";
	private static final String SMS_APPLY_URL = "http://183.62.49.171:9090/fas/smsPayApply";
	private static final String SMS_PAY_URL = "http://183.62.49.171:9090/fas/smsPay";
	private static final String QUERY_PAY_URL = "http://183.62.49.171:9090/fas/transIntegratedQuery";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPath() {
		String path = TestBestpay.class.getResource("/").getPath();
		String serverJksPath = path + "server.jks";
		File file = new File(serverJksPath);
		System.err.println(file.exists());
		System.err.println(path);
	}

	/**
	 * 测试绑卡
	 */
	@Test
	public void testBindCard() {
		SimulationResponse response = bindCard();
		System.err.println("message: " + response.getMessage());
		System.err.println("request： " + response.getRequest());
		System.err.println("response: " + response.getResponse());
	}

	@Test
	public void testSendSms() {
		SimulationResponse response = smsApply();
		System.err.println("message: " + response.getMessage());
		System.err.println("request： " + response.getRequest());
		System.err.println("response: " + response.getResponse());
	}

	@Test
	public void testSmsPay() {
		SimulationResponse response = smsPay();
		System.err.println("message: " + response.getMessage());
		System.err.println("request： " + response.getRequest());
		System.err.println("response: " + response.getResponse());
	}

	/**
	 * 翼支付绑卡（四要素快捷验证接口）
	 */
	private SimulationResponse bindCard() {
		SimulationResponse response = new SimulationResponse();

		String reqSn = "REQSN" + System.currentTimeMillis();
		logger.info("reqSn: " + reqSn);

		CustInfoRequest custInfoRequest = new CustInfoRequest();
		InetAddress addr;
		try {
			custInfoRequest.setReqSeq(reqSn);
			addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();// 获得本机IP
			custInfoRequest.setReqIp(ip);
			custInfoRequest.setLoginCode(TEST_LOGIN_CODE);
			custInfoRequest.setPlatCode(TEST_PLAT_CODE);
			custInfoRequest.setBankCode("866900");
			custInfoRequest.setBankCardCode("6230580000077369564");
			custInfoRequest.setBankCardName("戴励维");
			custInfoRequest.setOpenBankName("招商银行");
			custInfoRequest.setAreaCode("000000");
			custInfoRequest.setMobile("13661896734");
			custInfoRequest.setCertNo("310112198801105612");
			custInfoRequest.setCertType("00");

		} catch (UnknownHostException ex) {
			logger.error(ex.getMessage(), ex);
		}

		String requestStr = assembleRequest(custInfoRequest);
		logger.info("requestStr: " + requestStr);
		String responseStr = HttpUtils.httpPost(QUICK_VALIDATE_URL, requestStr);
		if (responseStr.contains("019999")) {
			// 通讯异常，无需验签
			response.setMessage(Constants.RESPONSE_MSG_COMMUNICATE_FAIL);
		} else {
			// 解析response
			try {
				logger.info("responseStr: " + responseStr);
				CertResponse certResponse = ObjectJsonUtil.jsonToObj(responseStr, CertResponse.class);
				BestPayData bestPayData = new BestPayData();
				String plainText = ObjectJsonUtil.objToJson(certResponse.getData());
				boolean verifySuc = RsaCipher.verify(plainText, Constants.PUBLIC_CERT, certResponse.getSign());
				if (verifySuc) {
					response.setMessage(Constants.RESPONSE_MSG_VERIFY_SUCCESS);
					bestPayData = ObjectJsonUtil.jsonToObj(plainText, BestPayData.class);
					logger.info("bestPayData: " + bestPayData);
				} else {
					response.setMessage(Constants.RESPONSE_MSG_VERIFY_FAIL);
				}

				Assert.assertEquals("000000", bestPayData.getCode());
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}

		}
		response.setRequest(requestStr);
		response.setResponse(responseStr);

		return response;
	}

	/**
	 * 短信发送
	 * 
	 * @return
	 */
	private SimulationResponse smsApply() {
		SimulationResponse response = new SimulationResponse();

		String reqSn = "REQSN" + System.currentTimeMillis();
		System.err.println("reqSn: " + reqSn);

		CustInfoRequest custInfoSmsRequest = new CustInfoRequest();
		InetAddress addr;
		try {
			custInfoSmsRequest.setReqSeq(reqSn);
			addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();// 获得本机IP
			custInfoSmsRequest.setReqIp(ip);
			custInfoSmsRequest.setLoginCode(TEST_LOGIN_CODE);
			custInfoSmsRequest.setPlatCode(TEST_PLAT_CODE);
			custInfoSmsRequest.setBankCode("866900");
			custInfoSmsRequest.setBankCardCode("6230580000077369564");
			custInfoSmsRequest.setBankCardName("戴励维1");
			custInfoSmsRequest.setOpenBankName("平安银行");
			custInfoSmsRequest.setAreaCode("000000");
			custInfoSmsRequest.setMobile("13661896734");
			custInfoSmsRequest.setCertNo("310112198801105612");
			custInfoSmsRequest.setCertType("00");

		} catch (UnknownHostException ex) {
			logger.error(ex.getMessage(), ex);
		}

		String requestStr = assembleRequest(custInfoSmsRequest);
		logger.info("requestStr: " + requestStr);
		String responseStr = HttpUtils.httpPost(SMS_APPLY_URL, requestStr);
		logger.info("responseStr: " + responseStr);
		if (responseStr.contains("019999")) {
			// 通讯异常，无需验签
			response.setMessage(Constants.RESPONSE_MSG_COMMUNICATE_FAIL);
		} else {
			// 解析response
			try {
				CertResponse certResponse = ObjectJsonUtil.jsonToObj(responseStr, CertResponse.class);
				BestPayData bestPayData = new BestPayData();
				String plainText = ObjectJsonUtil.objToJson(certResponse.getData());
				logger.info("plainText: " + plainText);
				boolean verifySuc = RsaCipher.verify(plainText, Constants.PUBLIC_CERT, certResponse.getSign());
				if (verifySuc) {
					bestPayData = ObjectJsonUtil.jsonToObj(plainText, BestPayData.class);
					response.setMessage(Constants.RESPONSE_MSG_VERIFY_SUCCESS);
				} else {
					response.setMessage(Constants.RESPONSE_MSG_VERIFY_FAIL);
				}
				Assert.assertEquals("000000", bestPayData.getCode());
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		response.setRequest(requestStr);
		response.setResponse(responseStr);

		return response;
	}

	/**
	 * 短信支付
	 * 
	 * @return
	 */
	private SimulationResponse smsPay() {
		SimulationResponse response = new SimulationResponse();

		String reqSn = "REQSN" + System.currentTimeMillis();
		System.err.println("reqSn: " + reqSn);

		String paySn = "PAYSN" + System.currentTimeMillis();
		System.err.println("paySn: " + paySn);

		CustInfoPayRequest custInfoPayRequest = new CustInfoPayRequest();
		InetAddress addr;
		try {
			custInfoPayRequest.setReqSeq(reqSn);
			addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();// 获得本机IP
			custInfoPayRequest.setReqIp(ip);
			custInfoPayRequest.setLoginCode(TEST_LOGIN_CODE);
			custInfoPayRequest.setPlatCode(TEST_PLAT_CODE);
			custInfoPayRequest.setBankCode("866900");
			custInfoPayRequest.setBankCardCode("6230580000077369564");
			custInfoPayRequest.setBankCardName("戴励维");
			custInfoPayRequest.setOpenBankName("平安银行");
			custInfoPayRequest.setAreaCode("000000");
			custInfoPayRequest.setMobile("13661896734");
			custInfoPayRequest.setCertNo("310112198801105612");
			custInfoPayRequest.setCertType("00");
			custInfoPayRequest.setSmsCode("884237");
			custInfoPayRequest.setTransactionAmount("1");
			custInfoPayRequest.setCurrencyCode(CurrentCodeEnum.RMB.toString());
			custInfoPayRequest.setExternalId(paySn);
			custInfoPayRequest.setRequestTime(sdf.format(new Date()));

		} catch (UnknownHostException ex) {
			logger.error(ex.getMessage(), ex);
		}

		String requestStr = assembleRequest(custInfoPayRequest);
		logger.info("requestStr: " + requestStr);
		String responseStr = HttpUtils.httpPost(SMS_PAY_URL, requestStr);
		logger.info("responseStr: " + responseStr);
		if (responseStr.contains("019999")) {
			// 通讯异常，无需验签
			response.setMessage(Constants.RESPONSE_MSG_COMMUNICATE_FAIL);
		} else {
			// 解析response
			try {
				CertResponse certResponse = ObjectJsonUtil.jsonToObj(responseStr, CertResponse.class);
				BestPayData bestPayData = new BestPayData();

				String plainText = ObjectJsonUtil.objToJson(certResponse.getData());
				logger.info("plainText: " + plainText);

				boolean verifySuc = RsaCipher.verify(plainText, Constants.PUBLIC_CERT, certResponse.getSign());
				if (verifySuc) {
					bestPayData = ObjectJsonUtil.jsonToObj(plainText, BestPayData.class);
					response.setMessage(Constants.RESPONSE_MSG_VERIFY_SUCCESS);
				} else {
					response.setMessage(Constants.RESPONSE_MSG_VERIFY_FAIL);
				}
				Assert.assertEquals("000000", bestPayData.getCode());
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		response.setRequest(requestStr);
		response.setResponse(responseStr);

		return response;
	}

	@Test
	public void testQueryPay() {
		SimulationResponse response = queryPay();
		System.err.println("message: " + response.getMessage());
		System.err.println("request： " + response.getRequest());
		System.err.println("response: " + response.getResponse());
	}

	/**
	 * { "result": { "results": [ { "trsRespCode": "PEC05000000", "bizCode":
	 * "00000035", "trsSeq": "160711300001105394", "platCode":
	 * "0020000000093003", "endTime": 1468227315000, "originAmount": "1",
	 * "custAreaCode": "360501", "trsCode": "00000001", "currencyCode": "RMB",
	 * "channelCode": "00001", "updateTime": 1468227315000, "tradeTime":
	 * 1468227314000, "reqSeq": "20160627000001", "trsDesc": "业务类订单",
	 * "trsRespMes": "交易成功", "bizDesc": "短信支付", "trsType": "TT001", "stat":
	 * "000", "fee": "0", "appName": "FAS", "extOrderSeq": "PAYSN1468227364086",
	 * "reqTime": 1468227364000, "payType": "PT1004", "actualAmount": "1",
	 * "loginCode": "zhonglian" } ], "totalCount": 1, "totalPage": 1 }, "code":
	 * "000000", "msg": "成功" }
	 * 
	 * @return 最终以stat的状态为000来判断
	 */
	private SimulationResponse queryPay() {
		SimulationResponse response = new SimulationResponse();

		String reqSn = "REQSN" + System.currentTimeMillis();
		System.err.println("reqSn: " + reqSn);

		CustInfoQueryRequest custInfoQueryRequest = new CustInfoQueryRequest();
		InetAddress addr;
		try {
			custInfoQueryRequest.setReqSeq(reqSn);
			addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();// 获得本机IP
			custInfoQueryRequest.setReqIp(ip);
			custInfoQueryRequest.setLoginCode(TEST_LOGIN_CODE);
			custInfoQueryRequest.setPlatCode(TEST_PLAT_CODE);
//			custInfoQueryRequest.setTrsSeq("160804300001112692");
			custInfoQueryRequest.setExtOrderSeq("PAYSN1470281452955");

		} catch (UnknownHostException ex) {
			logger.error(ex.getMessage(), ex);
		}

		String requestStr = assembleQueryRequest(custInfoQueryRequest);
		logger.info("requestStr: " + requestStr);
		String responseStr = HttpUtils.httpPost(QUERY_PAY_URL, requestStr);
		logger.info("responseStr: " + responseStr);
		if (responseStr.contains("019999")) {
			// 通讯异常，无需验签
			response.setMessage(Constants.RESPONSE_MSG_COMMUNICATE_FAIL);
		} else {
			// 解析response
			try {
				CertResponse certResponse = ObjectJsonUtil.jsonToObj(responseStr, CertResponse.class);
				String plainText = ObjectJsonUtil.objToJson(certResponse.getData());
				logger.info("plainText: " + plainText);
				boolean verifySuc = RsaCipher.verify(plainText, Constants.PUBLIC_CERT, certResponse.getSign());
				BestQueryPayData bestPayData = new BestQueryPayData();
				if (verifySuc) {
					response.setMessage(Constants.RESPONSE_MSG_VERIFY_SUCCESS);
					bestPayData = ObjectJsonUtil.jsonToObj(plainText, BestQueryPayData.class);
				} else {
					response.setMessage(Constants.RESPONSE_MSG_VERIFY_FAIL);
				}
				BestQueryPayRsData bestQueryPayRsData = bestPayData.getResult().getResults().size() > 0 ? bestPayData.getResult().getResults().get(0)
						: new BestQueryPayRsData();
				logger.info("stat: " + bestQueryPayRsData.getStat() + ", bestPayData: " + bestPayData.toString());
				Assert.assertEquals("000000", bestPayData.getCode());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		}
		response.setRequest(requestStr);
		response.setResponse(responseStr);

		return response;
	}

	@SuppressWarnings("unused")
	private String testAassembleRequest(Object request) {
		try {
			// 随机生成AES密钥
			String aesKey = "W9l84ASEuVlt1CAA";

			// 用支付公司公钥加密AES密钥
			byte[] aesKeyByte = RsaCipher.enDecryptByRsa(aesKey.getBytes(Constants.UTF_8), RsaCipher.getBestPayPublicKey(), Cipher.ENCRYPT_MODE);
			String aesEncodedKey = Base64.encodeBase64String(aesKeyByte);

			// 用随机生成的AES密钥对request对象中需要加密的字段进行AES算法加密
			Cipher cipher = AESUtil.initDecryptCipher(aesKey, AESUtil.AES_CBC_PKCS5, Cipher.ENCRYPT_MODE, AESUtil.IV);
			request = AESUtil.aesEncode(request, cipher);

			// 将request对象转为json
			String aesEncodeData = JSON.toJSONString(request, SerializerFeature.DisableCircularReferenceDetect);

			// 用商户自己的私钥对json格式的aesEncodeData加签得到签名
			byte[] signByte = RsaCipher.sign(aesEncodeData.getBytes(Constants.UTF_8), RsaCipher.getPrivateKey());
			String sign = Base64.encodeBase64String(signByte);

			// 组装成总的请求报文
			String requestStr = "{\"platformCode\":\"" + ((CustInfoRequest) request).getPlatCode() + "\",\"sign\":\"" + sign + "\",\"aesEncodedKey\":\""
					+ aesEncodedKey + "\", \"data\":" + aesEncodeData + "}";
			return requestStr;

		} catch (Exception ex) {
			logger.error("组装json请求报文失败: " + ex.getMessage(), ex);
			return null;
		}
	}

	private String assembleRequest(Object request) {
		try {
			// 随机生成AES密钥
			String aesKey = RandomUtil.getRandom(16);

			// 用支付公司公钥加密AES密钥
			byte[] aesKeyByte = RsaCipher.enDecryptByRsa(aesKey.getBytes(Constants.UTF_8), RsaCipher.getBestPayPublicKey(), Cipher.ENCRYPT_MODE);
			String aesEncodedKey = Base64.encodeBase64String(aesKeyByte);

			// 用随机生成的AES密钥对request对象中需要加密的字段进行AES算法加密
			Cipher cipher = AESUtil.initDecryptCipher(aesKey, AESUtil.AES_CBC_PKCS5, Cipher.ENCRYPT_MODE, AESUtil.IV);
			request = AESUtil.aesEncode(request, cipher);

			// 将request对象转为json
			String requestJson = JSON.toJSONString(request);

			// 用商户自己的私钥对json格式的aesEncodeData加签得到签名
			byte[] signByte = RsaCipher.sign(requestJson.getBytes(Constants.UTF_8), RsaCipher.getPrivateKeyForBase64());
			String sign = Base64.encodeBase64String(signByte);

			// 组装成总的请求报文
			String requestStr = "{\"platformCode\":\"" + ((CustInfoRequest) request).getPlatCode() + "\",\"sign\":\"" + sign + "\",\"aesEncodedKey\":\""
					+ aesEncodedKey + "\", \"data\":" + requestJson + "}";
			return requestStr;

		} catch (Exception ex) {
			logger.error("组装json请求报文失败: " + ex.getMessage(), ex);
			return null;
		}
	}

	private String assembleQueryRequest(Object request) {
		try {
			// 随机生成AES密钥
			String aesKey = RandomUtil.getRandom(16);

			// 用支付公司公钥加密AES密钥
			byte[] aesKeyByte = RsaCipher.enDecryptByRsa(aesKey.getBytes(Constants.UTF_8), RsaCipher.getBestPayPublicKey(), Cipher.ENCRYPT_MODE);
			String aesEncodedKey = Base64.encodeBase64String(aesKeyByte);

			// 用随机生成的AES密钥对request对象中需要加密的字段进行AES算法加密
			Cipher cipher = AESUtil.initDecryptCipher(aesKey, AESUtil.AES_CBC_PKCS5, Cipher.ENCRYPT_MODE, AESUtil.IV);
			request = AESUtil.aesEncode(request, cipher);

			// 将request对象转为json
			String aesEncodeData = JSON.toJSONString(request, SerializerFeature.DisableCircularReferenceDetect);

			// 用商户自己的私钥对json格式的aesEncodeData加签得到签名
			byte[] signByte = RsaCipher.sign(aesEncodeData.getBytes(Constants.UTF_8), RsaCipher.getPrivateKeyForBase64());
			String sign = Base64.encodeBase64String(signByte);

			// 组装成总的请求报文
			String requestStr = "{\"platformCode\":\"" + ((CustInfoQueryRequest) request).getPlatCode() + "\",\"sign\":\"" + sign + "\",\"aesEncodedKey\":\""
					+ aesEncodedKey + "\", \"data\":" + aesEncodeData + "}";
			return requestStr;

		} catch (Exception ex) {
			logger.error("组装json请求报文失败: " + ex.getMessage(), ex);
			return null;
		}
	}

	@Test
	public void testGetKeyStoreForBase64() {
		String path = "Q:" + File.separator + "server.jks";
		File file = new File(path);
		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			logger.info("buffer length: " + buffer.length);
			String base64Str = Base64.encodeBase64String(buffer);
			logger.info("base64Str: " + base64Str);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
