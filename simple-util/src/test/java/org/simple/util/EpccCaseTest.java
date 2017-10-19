package org.simple.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.simple.util.common.AesUtils;
import org.simple.util.common.EpccUtils;
import org.simple.util.common.HttpUtils;
import org.simple.util.common.JaxbUtils;
import org.simple.util.common.RsaUtils;
import org.simple.util.common.dto.epcc.Epcc40100101Request;
import org.simple.util.common.dto.epcc.Epcc40100101Response;
import org.simple.util.common.dto.epcc.MsgBody;
import org.simple.util.common.dto.epcc.MsgHeader;

/**
 * 网联用例
 * 
 * @author dailiwei
 *
 */
public class EpccCaseTest {

	private static final String SUCCESS_CODE = "00000000";
	private static final String Z2006845000013 = "Z2006845000013";
	private static final String EPCC_401_001_01 = "epcc.401.001.01";
	private static final String CONNECTION = "Connection";
	private static final String ORI_ISSR_ID = "OriIssrId";
	private static final String MSG_TP = "MsgTp";
	private static final String EPCC_PROT_443_URL = "https://59.151.65.97:443/preSvr";
	@SuppressWarnings("unused")
	private static final String EPCC_PROT_551_URL = "https://59.151.65.97:551/preSvr";
	// 加密私钥
	private static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDaqMfKwPPb54k7p71a9Ua9NqblrRT9scH0WXcuk8YwdOAVO7tFlvf2KyRiHU3TQiTimYqX9OZGCyaS6kftQcKZBJm2rV2pE27S2URUzNcVTBR1xpxQnMG8Q9CzUGeUWbsYP1FFjqOkq+kIBwFqTG42q7let6ldRbRNE9HT8d88AHQJS6BRLflJ7bzGgNmyh6mYf+vYUYBvCdH7g2bo1Z1f5k44O7D/zZn9al6dkXV/zoti4evle1WlPCFLTS5R/5Izi3DTKySafBJ9LJzoLylI91K1tiSUXGIH3zWR9xxeYZoIF1yIgQpLvoyS4nrPhe+2/m2QZfyFK+m27hagIU8xAgMBAAECggEAJlNVCY2+cHnpzOH+x5WcO4f7wuAOgNUKWOjhgfF22IFz0WTx0yW9+pDfRK88N94tFuawqyfKwNYtgay8xLI1CJsM0j8a3orAbwaT+oUY4eu+3lHcjiibsIL2bqeWMCN2Lq7ScO2qcy+KndSUg+w3mS+KQzbP4cBY9PWXXp3TcfGTcxpj3YmowNUfvFS4dAWZQeE1iE7rJls+Nb9If/hN8K9tZUfBGON+2jtZwLM2nRHgCPgkOvJgKGbVauCrZlWUFOSHiBGZR+bfGvQx4942t8WbzqS3eb9X8jXPCjFFWb1DiUAlpmsGKEc1NemWcGhKu1/d/VuWKE0GkyBNccBOjQKBgQDvcdQPkVCfEVWpTgc8VpRTtNhl4j51SIijeA+FAYDVa8NaUfcMml7pjWmKKnFJI94K7eGEJ7vN7EPG52xOU1iYWFq1T+xhLyh0gUz0DrFT9BkrGDpqSn9wRATbbc9Gqdm32WneLCz9We8MSD+LF6RXescdaCjGO03iLBAUcV4tkwKBgQDpxw0mclHbg4t4xd1IY17hvvYM2ORSXtt44d4jCkezfsrPPtRg/SSG5wGcOIlP2sIwYv2SCcZTOLbKoRLyEZLXqK0w0Cx7Imc6jB/p7Gj7mNU/7HQUtwvob+gkQOXPtcPfGGvGMsWm7z1Q4uvwDXzs6EuyXenUE41Vm22yQ35qqwKBgC7V0gf1gZKLnnjOVWX8/WheIFHVbigctvVan5aBk8SrHnwFOlCRxWzjhzhKUvxecqkqnIjwCLEfvKYkUDAF53dtGNkMOA1OXxhizj2SvibQwTeHtq1hwwmflF+jW/7TbE2kzitx8p7fv31kiGFZj4C4+EeNPyR/Jx3NRpvpDOXXAoGBANsqqvBtYsK6a4pZbeBMkQpw3fojaMK0fWuxzXDqVVg5OWfcTn1zNchnUAImmszLmRyF4ZYFJfKli/Eh20IoKZOXZm8J63mxQjgIYG8NHUsq+FnKkvVMupQ6PdenJAx8Ktq/6WJR/S1IwyJO68UM0B7GlRjupKYXgnxMkCX80sqrAoGBAJPq+jvIAsDMpb6VXdpQynXokKbfds2JY5pT5neUI9mccRczCY585NroZKYb5cpxpF3bdXkpYzPY+RFUA3usZUluZHui/Kw/yMDIhm3EwLjH0aFVNg5pgM+6AjWfHFL39wUSsCKLwLiSnZ00JXQalJcm3ejzTX1lOXqtJIgm0G9X";

	/**
	 * epcc.401.001.01流水号6位控制位请求
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc40100101CaseTest() throws Exception {
		Epcc40100101Request request = new Epcc40100101Request();
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setSndDt(new Date());
		msgHeader.setMsgTp(EPCC_401_001_01);
		msgHeader.setIssrId(Z2006845000013);
		msgHeader.setDrctn("11");
		msgHeader.setSignSN("4002567531");
		MsgBody msgBody = new MsgBody();
		msgBody.setInstgId(Z2006845000013);
		request.setMsgHeader(msgHeader);
		request.setMsgBody(msgBody);

		String requestXml = JaxbUtils.toXmlNoHeader(request);
		System.out.println("requestXml=" + requestXml);
		String signStr = RsaUtils.sign(privateKey, requestXml);
		System.out.println("signStr=" + signStr);
		String requestBody = String.format("%s%s{S:%s}", JaxbUtils.XML_HEADER, requestXml, signStr);
		System.out.println("requestBody=" + requestBody);
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put(MSG_TP, EPCC_401_001_01);
		headerMap.put(ORI_ISSR_ID, Z2006845000013);
		headerMap.put(CONNECTION, "keep-alive");
		String result = HttpUtils.httpXmlPost(EPCC_PROT_443_URL, requestBody, headerMap);
		System.out.println("result=" + result);

		String responseStr = EpccUtils.getResponseStr(result);
		String responseSignStr = EpccUtils.getResponseSign(result);

		String certPath = "Q:" + File.separator + "epcc" + File.separator + "wanglian-rsa.cer";
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(certPath);
		System.out.println("publicKey=" + publicKey);

		// 验签
		boolean verifySign = RsaUtils.vertify(publicKey, responseStr, responseSignStr);
		System.out.println("verifySign=" + verifySign);
		Assert.assertTrue(verifySign);

		// 组装成对应的JavaBean
		Epcc40100101Response response = JaxbUtils.toBean(String.format("%s%s", JaxbUtils.XML_HEADER, responseStr), Epcc40100101Response.class);
		System.out.println(response);
		String code = response.getMsgBody().getSysRtnInf().getSysRtnCd();
		String message = response.getMsgBody().getSysRtnInf().getSysRtnDesc();
		Assert.assertEquals(SUCCESS_CODE, code);
		System.out.println("message=" + message);
		List<String> ctrlNbInfs = new ArrayList<>();
		response.getMsgBody().getCtrlNbInfList().forEach(n -> ctrlNbInfs.add(n.getCtrlNbF()));
		System.out.println(ctrlNbInfs);
	}

	/**
	 * epcc.101.001.01 身份认证及签约申请报文
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc10100101Test() throws Exception {
		identityAuthAndSign("0201", EpccUtils.genTransSerialNo(), RandomStringUtils.randomNumeric(6));
	}
	
	@Test
	public void epcc10100101SignTest() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
	}
	
	@Test
	public void epcc10100101SignRepeatTest() throws Exception {
		String tranSerialNo = EpccUtils.genTransSerialNo();
		identityAuthAndSign("0201", tranSerialNo, "");
		identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
		identityAuthAndSign("0202", tranSerialNo, RandomStringUtils.randomNumeric(6));
	}

	private void identityAuthAndSign(String trxCtgy, String tranSerialNo, String authMsg) throws Exception {
		// 产生随机aes256bit-32字节长度秘钥
		String aeskey = RandomStringUtils.randomAlphanumeric(32);
		System.out.println("aeskey=" + aeskey + ",length=" + aeskey.length());

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
		System.out.println("envlpStr=" + envlpStr);

		// 使用网联平台公钥对该信封信息进行加密
		String certPath = "Q:" + File.separator + "epcc" + File.separator + "wanglian-rsa.cer";
		String publicKey = RsaUtils.convertCertFileToRsaPublicKey(certPath);
		System.out.println("publicKey=" + publicKey);
		String dgtlEnvlpStr = RsaUtils.encryptByPublicKey(publicKey, envlpStr);
		System.out.println("dgtlEnvlpStr=" + dgtlEnvlpStr);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDaqMfKwPPb54k7p71a9Ua9NqblrRT9scH0WXcuk8YwdOAVO7tFlvf2KyRiHU3TQiTimYqX9OZGCyaS6kftQcKZBJm2rV2pE27S2URUzNcVTBR1xpxQnMG8Q9CzUGeUWbsYP1FFjqOkq+kIBwFqTG42q7let6ldRbRNE9HT8d88AHQJS6BRLflJ7bzGgNmyh6mYf+vYUYBvCdH7g2bo1Z1f5k44O7D/zZn9al6dkXV/zoti4evle1WlPCFLTS5R/5Izi3DTKySafBJ9LJzoLylI91K1tiSUXGIH3zWR9xxeYZoIF1yIgQpLvoyS4nrPhe+2/m2QZfyFK+m27hagIU8xAgMBAAECggEAJlNVCY2+cHnpzOH+x5WcO4f7wuAOgNUKWOjhgfF22IFz0WTx0yW9+pDfRK88N94tFuawqyfKwNYtgay8xLI1CJsM0j8a3orAbwaT+oUY4eu+3lHcjiibsIL2bqeWMCN2Lq7ScO2qcy+KndSUg+w3mS+KQzbP4cBY9PWXXp3TcfGTcxpj3YmowNUfvFS4dAWZQeE1iE7rJls+Nb9If/hN8K9tZUfBGON+2jtZwLM2nRHgCPgkOvJgKGbVauCrZlWUFOSHiBGZR+bfGvQx4942t8WbzqS3eb9X8jXPCjFFWb1DiUAlpmsGKEc1NemWcGhKu1/d/VuWKE0GkyBNccBOjQKBgQDvcdQPkVCfEVWpTgc8VpRTtNhl4j51SIijeA+FAYDVa8NaUfcMml7pjWmKKnFJI94K7eGEJ7vN7EPG52xOU1iYWFq1T+xhLyh0gUz0DrFT9BkrGDpqSn9wRATbbc9Gqdm32WneLCz9We8MSD+LF6RXescdaCjGO03iLBAUcV4tkwKBgQDpxw0mclHbg4t4xd1IY17hvvYM2ORSXtt44d4jCkezfsrPPtRg/SSG5wGcOIlP2sIwYv2SCcZTOLbKoRLyEZLXqK0w0Cx7Imc6jB/p7Gj7mNU/7HQUtwvob+gkQOXPtcPfGGvGMsWm7z1Q4uvwDXzs6EuyXenUE41Vm22yQ35qqwKBgC7V0gf1gZKLnnjOVWX8/WheIFHVbigctvVan5aBk8SrHnwFOlCRxWzjhzhKUvxecqkqnIjwCLEfvKYkUDAF53dtGNkMOA1OXxhizj2SvibQwTeHtq1hwwmflF+jW/7TbE2kzitx8p7fv31kiGFZj4C4+EeNPyR/Jx3NRpvpDOXXAoGBANsqqvBtYsK6a4pZbeBMkQpw3fojaMK0fWuxzXDqVVg5OWfcTn1zNchnUAImmszLmRyF4ZYFJfKli/Eh20IoKZOXZm8J63mxQjgIYG8NHUsq+FnKkvVMupQ6PdenJAx8Ktq/6WJR/S1IwyJO68UM0B7GlRjupKYXgnxMkCX80sqrAoGBAJPq+jvIAsDMpb6VXdpQynXokKbfds2JY5pT5neUI9mccRczCY585NroZKYb5cpxpF3bdXkpYzPY+RFUA3usZUluZHui/Kw/yMDIhm3EwLjH0aFVNg5pgM+6AjWfHFL39wUSsCKLwLiSnZ00JXQalJcm3ejzTX1lOXqtJIgm0G9X";
		System.out.println("privateKey=" + privateKey);
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
		System.out.println("paymentAccountNo=" + paymentAccountNo);
		String encryptPaymentAccountNo = AesUtils.Aes256Encode(paymentAccountNo, aeskey);
		if ("0202".equals(trxCtgy)) {
			sb.append("<InstgAcct>" + encryptPaymentAccountNo + "</InstgAcct>");
		}
		sb.append("</InstgInf>");
		sb.append("</MsgBody>");
		sb.append("</root>");

		String requestXml = sb.toString();
		System.out.println("requestXml=" + requestXml);
		System.out.println("requestXml.length=" + requestXml.length());

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
		headerMap.put(MSG_TP, "epcc.101.001.01");
		headerMap.put(ORI_ISSR_ID, "Z2006845000013");
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
}
