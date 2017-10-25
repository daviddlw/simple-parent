package com.david.util.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import com.david.util.common.AesUtils;
import com.david.util.common.EpccUtils;
import com.david.util.common.HttpUtils;
import com.david.util.common.JaxbUtils;
import com.david.util.common.RsaCodingUtils;
import com.david.util.common.RsaUtils;
import com.david.util.constants.LogLevel;
import com.david.util.dto.test.ChildPerson;
import com.david.util.dto.test.Person;
import com.david.util.dto.test.Province;
import com.david.util.dto.test.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网联用例调试
 * 
 * @author dailiwei
 *
 */
public class EpccDebugTest {

	private static final Logger logger = LoggerFactory.getLogger(LogLevel.LOG_TEST);

	private static final String Z2006845000013 = "Z2006845000013";
	private static final String CONNECTION = "Connection";
	private static final String ORI_ISSR_ID = "OriIssrId";
	private static final String MSG_TP = "MsgTp";
	private static final String EPCC_PROT_443_URL = "https://59.151.65.97:443/preSvr";
	
	private static final String EPCC_401_001_01 = "epcc.401.001.01";
	private static final String EPCC_101_001_01 = "epcc.101.001.01";

	// 加密私钥
	private static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDaqMfKwPPb54k7p71a9Ua9NqblrRT9scH0WXcuk8YwdOAVO7tFlvf2KyRiHU3TQiTimYqX9OZGCyaS6kftQcKZBJm2rV2pE27S2URUzNcVTBR1xpxQnMG8Q9CzUGeUWbsYP1FFjqOkq+kIBwFqTG42q7let6ldRbRNE9HT8d88AHQJS6BRLflJ7bzGgNmyh6mYf+vYUYBvCdH7g2bo1Z1f5k44O7D/zZn9al6dkXV/zoti4evle1WlPCFLTS5R/5Izi3DTKySafBJ9LJzoLylI91K1tiSUXGIH3zWR9xxeYZoIF1yIgQpLvoyS4nrPhe+2/m2QZfyFK+m27hagIU8xAgMBAAECggEAJlNVCY2+cHnpzOH+x5WcO4f7wuAOgNUKWOjhgfF22IFz0WTx0yW9+pDfRK88N94tFuawqyfKwNYtgay8xLI1CJsM0j8a3orAbwaT+oUY4eu+3lHcjiibsIL2bqeWMCN2Lq7ScO2qcy+KndSUg+w3mS+KQzbP4cBY9PWXXp3TcfGTcxpj3YmowNUfvFS4dAWZQeE1iE7rJls+Nb9If/hN8K9tZUfBGON+2jtZwLM2nRHgCPgkOvJgKGbVauCrZlWUFOSHiBGZR+bfGvQx4942t8WbzqS3eb9X8jXPCjFFWb1DiUAlpmsGKEc1NemWcGhKu1/d/VuWKE0GkyBNccBOjQKBgQDvcdQPkVCfEVWpTgc8VpRTtNhl4j51SIijeA+FAYDVa8NaUfcMml7pjWmKKnFJI94K7eGEJ7vN7EPG52xOU1iYWFq1T+xhLyh0gUz0DrFT9BkrGDpqSn9wRATbbc9Gqdm32WneLCz9We8MSD+LF6RXescdaCjGO03iLBAUcV4tkwKBgQDpxw0mclHbg4t4xd1IY17hvvYM2ORSXtt44d4jCkezfsrPPtRg/SSG5wGcOIlP2sIwYv2SCcZTOLbKoRLyEZLXqK0w0Cx7Imc6jB/p7Gj7mNU/7HQUtwvob+gkQOXPtcPfGGvGMsWm7z1Q4uvwDXzs6EuyXenUE41Vm22yQ35qqwKBgC7V0gf1gZKLnnjOVWX8/WheIFHVbigctvVan5aBk8SrHnwFOlCRxWzjhzhKUvxecqkqnIjwCLEfvKYkUDAF53dtGNkMOA1OXxhizj2SvibQwTeHtq1hwwmflF+jW/7TbE2kzitx8p7fv31kiGFZj4C4+EeNPyR/Jx3NRpvpDOXXAoGBANsqqvBtYsK6a4pZbeBMkQpw3fojaMK0fWuxzXDqVVg5OWfcTn1zNchnUAImmszLmRyF4ZYFJfKli/Eh20IoKZOXZm8J63mxQjgIYG8NHUsq+FnKkvVMupQ6PdenJAx8Ktq/6WJR/S1IwyJO68UM0B7GlRjupKYXgnxMkCX80sqrAoGBAJPq+jvIAsDMpb6VXdpQynXokKbfds2JY5pT5neUI9mccRczCY585NroZKYb5cpxpF3bdXkpYzPY+RFUA3usZUluZHui/Kw/yMDIhm3EwLjH0aFVNg5pgM+6AjWfHFL39wUSsCKLwLiSnZ00JXQalJcm3ejzTX1lOXqtJIgm0G9X";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	/**
	 * jaxbUtils xml-bean工具类测试
	 */
	@Test
	public void jaxbUtilsTest() {
		Person person = new Person("张三丰", RandomUtils.nextInt(20, 60));
		Role role = new Role();
		role.setName("Administrator");
		role.setDesc("管理员");
		person.setRole(role);
		Province provinceShanghai = new Province("SH", "上海");
		Province provinceBeijing = new Province("BJ", "北京");
		List<Province> provinces = Arrays.asList(new Province[] { provinceBeijing, provinceShanghai });
		person.setSalary(RandomUtils.nextDouble(0, 30000));
		person.setProvinceList(provinces);
		person.setCreateTime(new Date());

		String xml = JaxbUtils.toXml(person);
		System.out.println(xml);

		Person result = JaxbUtils.toBean(xml, Person.class);
		System.out.println(result);
		Assert.assertEquals(person.getName(), result.getName());

		System.out.println(JaxbUtils.toXmlNoHeader(person));
	}

	@Test
	public void jaxbUtilsV2Test() {
		ChildPerson person = new ChildPerson("张三丰", RandomUtils.nextInt(20, 60));
		person.setChildName("张三丰孩子");
		Role role = new Role();
		role.setName("Administrator");
		role.setDesc("管理员");
		person.setRole(role);
		Province provinceShanghai = new Province("SH", "上海");
		Province provinceBeijing = new Province("BJ", "北京");
		List<Province> provinces = Arrays.asList(new Province[] { provinceBeijing, provinceShanghai });
		person.setSalary(RandomUtils.nextDouble(0, 30000));
		person.setProvinceList(provinces);
		person.setCreateTime(new Date());

		String xml = JaxbUtils.toXml(person);
		System.out.println(xml);

		Person result = JaxbUtils.toBean(xml, ChildPerson.class);
		System.out.println(result);
		Assert.assertEquals(person.getName(), result.getName());

		System.out.println(JaxbUtils.toXmlNoHeader(person));
	}

	/**
	 * 加解密
	 * 
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	@Test
	public void secruityEncodeAndDecodeTest() throws GeneralSecurityException, IOException {
		String pfxPath = "Q:" + File.separator + "epcc" + File.separator + "epcc_test_pri.pfx";
		System.out.println(pfxPath);
		String message = "你好，张三";
		String encodeMsg = RsaCodingUtils.encryptByPriPfxFile(message, pfxPath, "123456");
		System.out.println(encodeMsg);
		String certPath = "Q:" + File.separator + "epcc" + File.separator + "epcc_test_pub.cer";
		String decodeMsg = RsaCodingUtils.decryptByPubCerFile(encodeMsg, certPath);
		System.out.println(decodeMsg);
		Assert.assertEquals(message, decodeMsg);
	}

	/**
	 * 测试Httpclient工具类
	 */
	@Test
	public void httpUtilsTest() {
		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("state", String.valueOf(System.currentTimeMillis()));
		formParams.put("scope", "all");
		formParams.put("access_type", "offline");
		formParams.put("approval_prompt", "force");
		formParams.put("token", "17080114394920400099");
		formParams.put("response_type", "code");
		formParams.put("app_source", "1");
		formParams.put("client_id", "f238ba5db5a24927b1ab100cb16e2f24");
		formParams.put("redirect_uri", "https://www.baidu.com");
		String url = "https://quimf.hdfax.com/eff-uim-front-web/authorize/get_auth_code.form";
		String result = HttpUtils.httpPost(url, formParams);
		System.out.println(result);
	}

	@Test
	public void jksBase64Test() throws FileNotFoundException, IOException {
		String priKeyPath = "Q:" + File.separator + "epcc" + File.separator + "server.jks";
		try (FileInputStream fis = new FileInputStream(new File(priKeyPath))) {
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer, 0, buffer.length);
			String base64Str = Base64.encodeBase64String(buffer);
			System.out.println(base64Str);
		}
	}

	@Test
	public void testGenTranSerialNo() {
		System.out.println(EpccUtils.genTransSerialNo());
	}

	/**
	 * epcc.401.001.01流水号6位控制位请求
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc40100101Test() throws Exception {
		// 产生随机aes256秘钥
		String aeskey = AesUtils.genAes256Key();
		System.out.println("aeskey=" + aeskey + ",length=" + aeskey.length());

		// 可用该秘钥对敏感信息加密如果没有则不需要加密
		String envlpStr = String.format("01|%s", aeskey);
		System.out.println("envlpStr=" + envlpStr);

		// 使用网联平台公钥对该信封信息进行加密
		String certPath = "Q:" + File.separator + "epcc" + File.separator + "wanglian-rsa.cer";
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(certPath);
		System.out.println("publicKey=" + publicKey);
		String dgtlEnvlpStr = RsaUtils.encryptByPublicKey(publicKey, envlpStr);
		System.out.println("dgtlEnvlpStr=" + dgtlEnvlpStr);

		System.out.println("privateKey=" + privateKey);
		StringBuilder requestBodySb = new StringBuilder();

		requestBodySb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		StringBuilder sb = new StringBuilder();
		sb.append("<root xmlns=\"namespace_string\">");
		sb.append("<MsgHeader>");
		sb.append("<SndDt>" + sdf.format(new Date()) + "</SndDt>");
		sb.append("<MsgTp>epcc.401.001.01</MsgTp>");
		sb.append("<IssrId>Z2006845000013</IssrId>");
		sb.append("<Drctn>11</Drctn>");
		sb.append("<SignSN>4002567531</SignSN>");
		// sb.append("<NcrptnSN>4000068829</NcrptnSN>");
		// sb.append("<DgtlEnvlp>" + dgtlEnvlpStr + "</DgtlEnvlp>");
		sb.append("</MsgHeader>");
		sb.append("<MsgBody>");
		sb.append("<InstgId>Z2006845000013</InstgId>");
		sb.append("</MsgBody>");
		sb.append("</root>");

		String requestXml = sb.toString();
		System.out.println(requestXml.length());

		String signStr = RsaUtils.sign(privateKey, requestXml);
		System.out.println(signStr);
		sb.append("{S:");
		sb.append(signStr);
		sb.append("}");
		String content = sb.toString();
		System.out.println("==================content================");
		System.out.println(content);

		requestBodySb.append(content);
		System.out.println("requestBody=" + requestBodySb.toString());
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put(MSG_TP, EPCC_401_001_01);
		headerMap.put(ORI_ISSR_ID, Z2006845000013);
		headerMap.put(CONNECTION, "keep-alive");
		String result = HttpUtils.httpXmlPost(EPCC_PROT_443_URL, requestBodySb.toString(), headerMap);
		System.out.println("result=" + result);

		// 截取返回报文（改成正则）
		String responseStr = result.substring(result.indexOf("?>") + 2, result.indexOf("{S:"));
		System.out.println("responseStr=" + responseStr);

		// 截取签名串（改成正则）
		String responseSignStr = result.substring(result.indexOf("{") + 3, result.indexOf("}"));
		System.out.println("responseSignStr=" + responseSignStr);

		// 验签
		boolean verifySign = RsaUtils.vertify(publicKey, responseStr, responseSignStr);
		System.out.println("verifySign=" + verifySign);
		Assert.assertTrue(verifySign);
	}

	@SuppressWarnings("unused")
	private void identityAuthAndSign(String trxCtgy, String tranSerialNo, String authMsg) throws Exception {
		// 产生随机aes256bit-32字节长度秘钥
		String aeskey = RandomStringUtils.randomAlphanumeric(32);
		logger.info("aeskey=" + aeskey + ",length=" + aeskey.length());

		String idNo = "421127198509140413";
		String idName = "张三丰1";
		String cardNo = "6214832130521235";
		String phoneNo = "15800563769";

		String encryptIdNo = AesUtils.Aes256Encode(idNo, aeskey);
		String encryptIdName = AesUtils.Aes256Encode(idName, aeskey);
		String encryptCardNo = AesUtils.Aes256Encode(cardNo, aeskey);
		String encryptPhone = AesUtils.Aes256Encode(phoneNo, aeskey);

		// 可用该秘钥对敏感信息加密如果没有则不需要加密
		String envlpStr = String.format("01|%s", aeskey);
		logger.info("envlpStr=" + envlpStr);

		// 使用网联平台公钥对该信封信息进行加密
		String certPath = "Q:" + File.separator + "epcc" + File.separator + "wanglian-rsa.cer";
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(certPath);
		logger.info("publicKey=" + publicKey);
		String dgtlEnvlpStr = RsaUtils.encryptByPublicKey(publicKey, envlpStr);
		logger.info("dgtlEnvlpStr=" + dgtlEnvlpStr);

		logger.info("privateKey=" + privateKey);
		StringBuilder requestBodySb = new StringBuilder();
		requestBodySb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		StringBuilder sb = new StringBuilder();
		sb.append("<root xmlns=\"namespace_string\">");
		sb.append("<MsgHeader>");
		sb.append("<SndDt>" + sdf.format(new Date()) + "</SndDt>");
		sb.append("<MsgTp>epcc.101.001.01</MsgTp>");
		sb.append("<IssrId>Z2006845000013</IssrId>");
		sb.append("<Drctn>11</Drctn>");
		sb.append("<SignSN>4002567531</SignSN>");
		sb.append("<NcrptnSN>4000068829</NcrptnSN>");
		sb.append("<DgtlEnvlp>" + dgtlEnvlpStr + "</DgtlEnvlp>");
		sb.append("</MsgHeader>");
		sb.append("<MsgBody>");
		sb.append("<SgnInf>");
		sb.append("<SgnAcctIssrId>C1010611003601</SgnAcctIssrId>");
		sb.append("<SgnAcctTp>00</SgnAcctTp>");
		sb.append("<SgnAcctId>" + encryptCardNo + "</SgnAcctId>");
		sb.append("<SgnAcctNm>" + encryptIdName + "</SgnAcctNm>");
		sb.append("<IDTp>01</IDTp>");
		sb.append("<IDNo>" + encryptIdNo + "</IDNo>");
		sb.append("<MobNo>" + encryptPhone + "</MobNo>");
		sb.append("</SgnInf>");
		sb.append("<TrxInf>");
		sb.append("<TrxCtgy>" + trxCtgy + "</TrxCtgy>");
		sb.append("<TrxId>" + tranSerialNo + "</TrxId>");
		sb.append("<TrxDtTm>" + sdf.format(new Date()) + "</TrxDtTm>");
		if ("0201".equals(trxCtgy)) {
			sb.append("<AuthMsg></AuthMsg>");
		} else {
			sb.append("<AuthMsg>" + authMsg + "</AuthMsg>");
		}

		sb.append("</TrxInf>");
		sb.append("<InstgInf>");
		sb.append("<InstgId>Z2006845000013</InstgId>");
		String paymentAccountNo = RandomStringUtils.randomNumeric(16);
		logger.info("paymentAccountNo=" + paymentAccountNo);
		String encryptPaymentAccountNo = AesUtils.Aes256Encode(paymentAccountNo, aeskey);
		if ("0202".equals(trxCtgy)) {
			sb.append("<InstgAcct>" + encryptPaymentAccountNo + "</InstgAcct>");
		}
		sb.append("</InstgInf>");
		sb.append("</MsgBody>");
		sb.append("</root>");

		String requestXml = sb.toString();
		logger.info("requestXml=" + requestXml);
		logger.info("requestXml.length=" + requestXml.length());

		String signStr = RsaUtils.sign(privateKey, requestXml);
		logger.info(signStr);
		sb.append("{S:");
		sb.append(signStr);
		sb.append("}");
		String content = sb.toString();
		logger.info("==================content================");
		logger.info(content);

		requestBodySb.append(content);
		logger.info("requestBody=" + requestBodySb.toString());
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put(MSG_TP, EPCC_101_001_01);
		headerMap.put(ORI_ISSR_ID, "Z2006845000013");
		headerMap.put(CONNECTION, "keep-alive");
		String result = HttpUtils.httpXmlPost(EPCC_PROT_443_URL, requestBodySb.toString(), headerMap);
		logger.info("result=" + result);

		// 截取返回报文（改成正则）
		String responseStr = result.substring(result.indexOf("?>") + 2, result.indexOf("{S:"));
		logger.info("responseStr=" + responseStr);

		// 截取签名串（改成正则）
		String responseSignStr = result.substring(result.indexOf("{") + 3, result.indexOf("}"));
		logger.info("responseSignStr=" + responseSignStr);

		// 验签
		boolean verifySign = RsaUtils.vertify(publicKey, responseStr, responseSignStr);
		logger.info("verifySign=" + verifySign);
		Assert.assertTrue(verifySign);
	}

}
