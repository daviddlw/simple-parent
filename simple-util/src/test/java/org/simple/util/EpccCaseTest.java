package org.simple.util;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.simple.util.common.AesUtils;
import org.simple.util.common.HttpUtils;
import org.simple.util.common.RsaUtils;

/**
 * 网联用例
 * 
 * @author dailiwei
 *
 */
public class EpccCaseTest {

	private static final String CONNECTION = "Connection";
	private static final String ORI_ISSR_ID = "OriIssrId";
	private static final String MSG_TP = "MsgTp";
	private static final String EPCC_PROT_443_URL = "https://59.151.65.97:443/preSvr";
	@SuppressWarnings("unused")
	private static final String EPCC_PROT_551_URL = "https://59.151.65.97:551/preSvr";

	/**
	 * epcc.401.001.01流水号6位控制位请求
	 * 
	 * @throws Exception
	 */
	@Test
	public void epcc4100101Test() throws Exception {
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

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDaqMfKwPPb54k7p71a9Ua9NqblrRT9scH0WXcuk8YwdOAVO7tFlvf2KyRiHU3TQiTimYqX9OZGCyaS6kftQcKZBJm2rV2pE27S2URUzNcVTBR1xpxQnMG8Q9CzUGeUWbsYP1FFjqOkq+kIBwFqTG42q7let6ldRbRNE9HT8d88AHQJS6BRLflJ7bzGgNmyh6mYf+vYUYBvCdH7g2bo1Z1f5k44O7D/zZn9al6dkXV/zoti4evle1WlPCFLTS5R/5Izi3DTKySafBJ9LJzoLylI91K1tiSUXGIH3zWR9xxeYZoIF1yIgQpLvoyS4nrPhe+2/m2QZfyFK+m27hagIU8xAgMBAAECggEAJlNVCY2+cHnpzOH+x5WcO4f7wuAOgNUKWOjhgfF22IFz0WTx0yW9+pDfRK88N94tFuawqyfKwNYtgay8xLI1CJsM0j8a3orAbwaT+oUY4eu+3lHcjiibsIL2bqeWMCN2Lq7ScO2qcy+KndSUg+w3mS+KQzbP4cBY9PWXXp3TcfGTcxpj3YmowNUfvFS4dAWZQeE1iE7rJls+Nb9If/hN8K9tZUfBGON+2jtZwLM2nRHgCPgkOvJgKGbVauCrZlWUFOSHiBGZR+bfGvQx4942t8WbzqS3eb9X8jXPCjFFWb1DiUAlpmsGKEc1NemWcGhKu1/d/VuWKE0GkyBNccBOjQKBgQDvcdQPkVCfEVWpTgc8VpRTtNhl4j51SIijeA+FAYDVa8NaUfcMml7pjWmKKnFJI94K7eGEJ7vN7EPG52xOU1iYWFq1T+xhLyh0gUz0DrFT9BkrGDpqSn9wRATbbc9Gqdm32WneLCz9We8MSD+LF6RXescdaCjGO03iLBAUcV4tkwKBgQDpxw0mclHbg4t4xd1IY17hvvYM2ORSXtt44d4jCkezfsrPPtRg/SSG5wGcOIlP2sIwYv2SCcZTOLbKoRLyEZLXqK0w0Cx7Imc6jB/p7Gj7mNU/7HQUtwvob+gkQOXPtcPfGGvGMsWm7z1Q4uvwDXzs6EuyXenUE41Vm22yQ35qqwKBgC7V0gf1gZKLnnjOVWX8/WheIFHVbigctvVan5aBk8SrHnwFOlCRxWzjhzhKUvxecqkqnIjwCLEfvKYkUDAF53dtGNkMOA1OXxhizj2SvibQwTeHtq1hwwmflF+jW/7TbE2kzitx8p7fv31kiGFZj4C4+EeNPyR/Jx3NRpvpDOXXAoGBANsqqvBtYsK6a4pZbeBMkQpw3fojaMK0fWuxzXDqVVg5OWfcTn1zNchnUAImmszLmRyF4ZYFJfKli/Eh20IoKZOXZm8J63mxQjgIYG8NHUsq+FnKkvVMupQ6PdenJAx8Ktq/6WJR/S1IwyJO68UM0B7GlRjupKYXgnxMkCX80sqrAoGBAJPq+jvIAsDMpb6VXdpQynXokKbfds2JY5pT5neUI9mccRczCY585NroZKYb5cpxpF3bdXkpYzPY+RFUA3usZUluZHui/Kw/yMDIhm3EwLjH0aFVNg5pgM+6AjWfHFL39wUSsCKLwLiSnZ00JXQalJcm3ejzTX1lOXqtJIgm0G9X";
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
		// sb.append("<DgtlEvnlp>" + dgtlEnvlpStr + "</DgtlEvnlp>");
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
		headerMap.put(MSG_TP, "epcc.401.001.01");
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
	}
}
