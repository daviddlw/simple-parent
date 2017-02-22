package com.david.test;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.david.yeepay.utils.AES;
import com.david.yeepay.utils.EncryUtil;
import com.david.yeepay.utils.RSA;

public class YeePayKeysTest {

	private static final String MERCHANT_NO = "10013044172";
	private static final String QUERY_AUTH_BIND_LIST_URL = "https://ok.yeepay.com/payapi/api/bankcard/authbind/list";

	private static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANHA6dosVBBy09Rq8BG6i61pU4WtQtKfRebQQaXJ6cMDcMx7+cFBVi+gSy4/5H/MsUJeXuiPL4otTEUWaconIcC7qN2Q8ummjZNiWDBtn7RSnUZlN825sbu9H2HuEwsCcIqd99JDGktHFRP3KSjjhGo5lQjAl82YCTXCDyP7BubxAgMBAAECgYEAtOAISK0lKLH8XRaCQqd8v2MuNP39u6bBHpLDqXV+cT5MTOkzrCTtb1BoxE8wecxHpxW9/glLU3pQZNs5CqvH+9z68a9NMNWcWVYQHuiNr2LOOsdrNG0c8AOOx2Cx66x1rcZo7P6y1Q2UpK5ii3Fuw1QEOUctZIcXPVMp5Mh/m+ECQQD19kOBL0o8g1EPjf5QW5LYywS0XMlvFx8CKBwwQVpsKv6zRcKNRVRnNpJDa0zdpbCZl+yfcDphrcw9diLM7w/9AkEA2lBa+k6LJzE2JTHe3ufQC4e+EGPh4+pE4p3bpUwz1Us8J55ug0CakociOe/80bQM6pRK1cG32PrTJvpu0ZwjBQJAKTNhR2iGhPBySaq9aPSN+qa3WQcZ7vPui8vwPN0VqnzhQWJ0Hnn4k3l+srBspcw1cuUSC/serHISflElrzJ0eQJAepAsU2RvGQS2yjDyV6A7G44MtXTt55+pJrTLEET0wKVpUzDOCF/np6W4IGDMRgrYSYYRcWo9RdKjYrmsJ3+J5QJAW+r+FnafFaFnuo4uJwbBZzW6bSVyXIzXW9izEJh+WmxdnTVlHL953yqB5zdv0QIncRjQqnBjrOTb62psqM1g/Q==";
	private static final String errorFormatPrivateKey = "12345678";
	private static final String errorContentPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMrH/C/W9hpD1nifR6xvj9p/7Ydxq1Zr5B8MD+nWOHPXFRKvENyBTafFN5sxBJacWQfpmYPvRvMHPWeiQygxZSXIapxHaTZUR73q7D+S+y9VZqaGp31T/eYPByvW52tAoiuBJgVber4Aq/hC4C+sN0s3pLkF2teQbTGKf1dum/WrAgMBAAECgYEAoKt6RxGEp+TuT16s9JTkn1TTiIjpIC2Wb38oMLg6vJZDrS0nKAAP3oml+IILR6aT6HAP4fi595/Z6H4pxfP1VmRs/tF2npd51SMp/omzOCeUo93gKpgPeOUqf5pknQhFmIHa6OFaAZkMAIJvpU07rIRTJ4ZY3ICgybSgE3Z5qNkCQQDnuLSJeqGtXKcgEYV3/zBxmKUaXIjixx5LEXzDrNavybbuzrBcIhvPStxfmrUMIGERatw4MklR//Y2CJy6mAb9AkEA4AcJBabCm+cRbHRDbBG5DrOeNY4LfSYw7JIymmI3JdjAk0BKXzw45I6hOi57Wwe8ha6o6ua5QXPINmMO5RzTxwJAWWBW0kgooDOCis33Swd06fvUgsx6R+5Bcvb1xoud6wPEuWujfoXxcA0R3xZPCrRafFtZQZuzyBsQHltICHinCQJATVYgZzMKO4epHCeGnuf/WnPnSdWfYbStbv2JOoJtR0NsN2kaQ4YmncAfKFajfulMy1lgNrcvfMjbdOr9WVp6QQJBALRJ8AY93sR6T2y3SXseYykFxhGEspkk7pHCcW96kI+YSuaOGB5UgmtKrgoOetmDcaY91brxmadVZ7Ts3OJix6k=";

	private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDlJZl8EVP9Y6KBgSRHCvfPHDCRgVTboCwwwlLoeYg/I1w4S7+FJ8ToRqOevcVjRBNOAvS5diEFamHU+5NnAwm2TA4BBbKNxpOj03NtWV5ryULrFDKW+yGSLGhtCkLAoW8P0GhJ38YSbQtOo0/U1lXA+nozB+mIGYqpFIgqLjmbBQIDAQAB";
	private static final String errorFormatPublicKey = "12345678";
	private static final String errorContentPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXDZnx40vP6+779Y4Z0Rz7/DhL2it5zkDXDmjdTBD/EsLt2Eh9j07nPZeID2izt6KprfbQ6sBXK3rKhQlurqRj2z9i3iH/LnL6hBT5q3kQ39j5pFIjN47hnzgKWbNUZc4aG2VkE73gV3u12AJI6he4yzKjvx4tPqFINp+rNQUdkQIDAQAB";

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 测试易宝rsa相关密钥
	 */
	@Test
	public void testYeePay() {
		queryAuthbindList();
	}

	public static String getMerchantAESKey() {
		return (RandomUtil.getRandom(16));
	}

	public AccessKeys getAccessKeys() {
		AccessKeys accessKeys = getSourceAccessKeys();
		// accessKeys = getErrorFormatPrivateKey();
		// accessKeys = getErrorContentPrivateKey();
		// accessKeys = getErrorFormatPublicKey();
		// accessKeys = getErrorContenPublicKey();
		return accessKeys;
	}

	/**
	 * 错误格式的rsa私钥
	 * 
	 * responseBody: {"error_code":"601040","error_msg":"生成的RSA签名错误"}
	 * 
	 * @return
	 */
	public AccessKeys getErrorFormatPrivateKey() {
		AccessKeys accessKeys = getSourceAccessKeys();
		accessKeys.setRsaPrivateKey(errorFormatPrivateKey);
		return accessKeys;
	}

	/**
	 * 错误内容的rsa私钥
	 * 
	 * responseBody: {"error_code":"601040","error_msg":"生成的RSA签名错误"}
	 * 
	 * @return
	 */
	public AccessKeys getErrorContentPrivateKey() {
		AccessKeys accessKeys = getSourceAccessKeys();
		accessKeys.setRsaPrivateKey(errorContentPrivateKey);
		return accessKeys;
	}

	/**
	 * 错误格式的rsa公钥
	 * 
	 * {customError=Caught an Exception.
	 * java.security.spec.InvalidKeySpecException:
	 * java.security.InvalidKeyException: IOException: Detect premature EOF}
	 * 
	 * @return
	 */
	public AccessKeys getErrorFormatPublicKey() {
		AccessKeys accessKeys = getSourceAccessKeys();
		accessKeys.setRsaPublicKey(errorFormatPublicKey);
		return accessKeys;
	}

	/**
	 * 错误内容的rsa公钥
	 * 
	 * responseBody: {"error_code":"600000","error_msg":"illegal request"}
	 * 
	 * @return
	 */
	public AccessKeys getErrorContenPublicKey() {
		AccessKeys accessKeys = getSourceAccessKeys();
		accessKeys.setRsaPublicKey(errorContentPublicKey);
		return accessKeys;
	}

	public AccessKeys getSourceAccessKeys() {
		AccessKeys accessKeys = new AccessKeys();
		accessKeys.setRsaPrivateKey(privateKey);
		accessKeys.setRsaPublicKey(publicKey);
		return accessKeys;
	}

	/**
	 * String2Integer
	 */
	public int String2Int(String text) throws NumberFormatException {
		return text == null ? 0 : Integer.valueOf(text);
	}

	public Map<String, String> queryAuthbindList() {

		System.out.println("##### queryAuthbindList() #####");

		Map<String, String> result = new HashMap<String, String>();
		String customError = ""; // 自定义，非接口返回

		String merchantaccount = MERCHANT_NO;
		String merchantPrivateKey = getAccessKeys().getRsaPrivateKey();
		String merchantAESKey = getMerchantAESKey();
		String yeepayPublicKey = getAccessKeys().getRsaPublicKey();
		String queryAuthbindListURL = QUERY_AUTH_BIND_LIST_URL;

		String identitytype = "2";
		String identityid = String.valueOf(System.currentTimeMillis());
		int identitytype2Int = -1;

		try {
			identitytype2Int = String2Int(identitytype);
		} catch (Exception e) {
			e.printStackTrace();
			customError = "String 2 Int Error!!! - identitytype = [" + identitytype + "]";
			result.put("customError", customError);
			return (result);
		}

		TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
		dataMap.put("merchantaccount", merchantaccount);
		dataMap.put("identityid", identityid);
		dataMap.put("identitytype", identitytype2Int);

		String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
		dataMap.put("sign", sign);

		System.out.println("queryAuthbindListURL : " + queryAuthbindListURL);
		System.out.println("dataMap : " + dataMap);

		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			String jsonStr = JSON.toJSONString(dataMap);
			String data = AES.encryptToBase64(jsonStr, merchantAESKey);
			String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

			String url = queryAuthbindListURL + "?merchantaccount=" + URLEncoder.encode(merchantaccount, "UTF-8") + "&data=" + URLEncoder.encode(data, "UTF-8")
					+ "&encryptkey=" + URLEncoder.encode(encryptkey, "UTF-8");

			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();

			HttpEntity httpEntity = httpResponse.getEntity();
			String responseBody = EntityUtils.toString(httpEntity);

			System.out.println("url: " + url);
			System.out.println("responseBody: " + responseBody);

			result = parseHttpResponseBody(statusCode, responseBody);

		} catch (Exception e) {
			customError = "Caught an Exception. " + e.toString();
			result.put("customError", customError);
			e.printStackTrace();
		}

		System.out.println("result : " + result);

		return (result);
	}

	/**
	 * 解析http请求返回
	 */
	public Map<String, String> parseHttpResponseBody(int statusCode, String responseBody) throws Exception {

		String merchantPrivateKey = getAccessKeys().getRsaPrivateKey();
		String yeepayPublicKey = getAccessKeys().getRsaPublicKey();

		Map<String, String> result = new HashMap<String, String>();
		String customError = "";

		if (statusCode != 200) {
			customError = "Request failed, response code : " + statusCode;
			result.put("customError", customError);
			return (result);
		}

		Map<String, String> jsonMap = JSON.parseObject(responseBody, new TypeReference<TreeMap<String, String>>() {
		});

		if (jsonMap.containsKey("error_code")) {
			result = jsonMap;
			return (result);
		}

		String dataFromYeepay = formatString(jsonMap.get("data"));
		String encryptkeyFromYeepay = formatString(jsonMap.get("encryptkey"));

		boolean signMatch = EncryUtil.checkDecryptAndSign(dataFromYeepay, encryptkeyFromYeepay, yeepayPublicKey, merchantPrivateKey);
		if (!signMatch) {
			customError = "Sign not match error";
			result.put("customError", customError);
			return (result);
		}

		String yeepayAESKey = RSA.decrypt(encryptkeyFromYeepay, merchantPrivateKey);
		String decryptData = AES.decryptFromBase64(dataFromYeepay, yeepayAESKey);

		result = JSON.parseObject(decryptData, new TypeReference<TreeMap<String, String>>() {
		});

		return (result);
	}

	public String formatString(String text) {
		return (text == null ? "" : text.trim());
	}
}
