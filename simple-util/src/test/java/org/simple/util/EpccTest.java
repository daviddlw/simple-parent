package org.simple.util;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.simple.util.common.AesUtils;
import org.simple.util.common.HttpUtils;
import org.simple.util.common.RsaCodingUtils;
import org.simple.util.common.RsaUtils;

public class EpccTest {

	/**
	 * 单元测试加解密
	 * 
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	@Test
	public void testSecruityEncodeAndDecode() throws GeneralSecurityException, IOException {
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

	@Test
	public void testHttpClient() {
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
	public void testEncryptAndDecrpt() throws Exception {

		// 产生随机aes256秘钥
		String aeskey = AesUtils.genAes256Key();
		System.out.println("aeskey=" + aeskey + ",length=" + aeskey.length());

		// 可用该秘钥对敏感信息加密如果没有则不需要加密
		String envlpStr = String.format("01|%s", aeskey);
		System.out.println(envlpStr);

		// 使用网联平台公钥对该信封信息进行加密
		String certPath = "Q:" + File.separator + "epcc" + File.separator + "wanglian-rsa.cer";
		String dgtlEnvlpStr = RsaUtils.encryptByPubCerFile(certPath, envlpStr);
		System.out.println(dgtlEnvlpStr);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDaqMfKwPPb54k7p71a9Ua9NqblrRT9scH0WXcuk8YwdOAVO7tFlvf2KyRiHU3TQiTimYqX9OZGCyaS6kftQcKZBJm2rV2pE27S2URUzNcVTBR1xpxQnMG8Q9CzUGeUWbsYP1FFjqOkq+kIBwFqTG42q7let6ldRbRNE9HT8d88AHQJS6BRLflJ7bzGgNmyh6mYf+vYUYBvCdH7g2bo1Z1f5k44O7D/zZn9al6dkXV/zoti4evle1WlPCFLTS5R/5Izi3DTKySafBJ9LJzoLylI91K1tiSUXGIH3zWR9xxeYZoIF1yIgQpLvoyS4nrPhe+2/m2QZfyFK+m27hagIU8xAgMBAAECggEAJlNVCY2+cHnpzOH+x5WcO4f7wuAOgNUKWOjhgfF22IFz0WTx0yW9+pDfRK88N94tFuawqyfKwNYtgay8xLI1CJsM0j8a3orAbwaT+oUY4eu+3lHcjiibsIL2bqeWMCN2Lq7ScO2qcy+KndSUg+w3mS+KQzbP4cBY9PWXXp3TcfGTcxpj3YmowNUfvFS4dAWZQeE1iE7rJls+Nb9If/hN8K9tZUfBGON+2jtZwLM2nRHgCPgkOvJgKGbVauCrZlWUFOSHiBGZR+bfGvQx4942t8WbzqS3eb9X8jXPCjFFWb1DiUAlpmsGKEc1NemWcGhKu1/d/VuWKE0GkyBNccBOjQKBgQDvcdQPkVCfEVWpTgc8VpRTtNhl4j51SIijeA+FAYDVa8NaUfcMml7pjWmKKnFJI94K7eGEJ7vN7EPG52xOU1iYWFq1T+xhLyh0gUz0DrFT9BkrGDpqSn9wRATbbc9Gqdm32WneLCz9We8MSD+LF6RXescdaCjGO03iLBAUcV4tkwKBgQDpxw0mclHbg4t4xd1IY17hvvYM2ORSXtt44d4jCkezfsrPPtRg/SSG5wGcOIlP2sIwYv2SCcZTOLbKoRLyEZLXqK0w0Cx7Imc6jB/p7Gj7mNU/7HQUtwvob+gkQOXPtcPfGGvGMsWm7z1Q4uvwDXzs6EuyXenUE41Vm22yQ35qqwKBgC7V0gf1gZKLnnjOVWX8/WheIFHVbigctvVan5aBk8SrHnwFOlCRxWzjhzhKUvxecqkqnIjwCLEfvKYkUDAF53dtGNkMOA1OXxhizj2SvibQwTeHtq1hwwmflF+jW/7TbE2kzitx8p7fv31kiGFZj4C4+EeNPyR/Jx3NRpvpDOXXAoGBANsqqvBtYsK6a4pZbeBMkQpw3fojaMK0fWuxzXDqVVg5OWfcTn1zNchnUAImmszLmRyF4ZYFJfKli/Eh20IoKZOXZm8J63mxQjgIYG8NHUsq+FnKkvVMupQ6PdenJAx8Ktq/6WJR/S1IwyJO68UM0B7GlRjupKYXgnxMkCX80sqrAoGBAJPq+jvIAsDMpb6VXdpQynXokKbfds2JY5pT5neUI9mccRczCY585NroZKYb5cpxpF3bdXkpYzPY+RFUA3usZUluZHui/Kw/yMDIhm3EwLjH0aFVNg5pgM+6AjWfHFL39wUSsCKLwLiSnZ00JXQalJcm3ejzTX1lOXqtJIgm0G9X";
		System.out.println(privateKey);
		StringBuilder requestBodySb = new StringBuilder();
		requestBodySb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		StringBuilder sb = new StringBuilder();
		sb.append("<root xmlns=\"namespace_string\">");
		sb.append("<MsgHeader>");
		sb.append("<SndDt>" + sdf.format(new Date()).replace(" ", "T") + "</SndDt>");
		sb.append("<MsgTp>epcc.401.001.01</MsgTp>");
		sb.append("<IssrId>Z2006845000013</IssrId>");
		sb.append("<Drctn>11</Drctn>");
		sb.append("<SignSN>4002567531</SignSN>");
		sb.append("<NcrptnSN>4000068829</NcrptnSN>");
		sb.append("<DgtlEvnlp>" + dgtlEnvlpStr + "</DgtlEvnlp>");
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
		String result = HttpUtils.httpXmlPost("https://59.151.65.97:443/preSvr", requestBodySb.toString());
		System.out.println(result);
	}

	@Test
	public void testDecryptText() {
		String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDaqMfKwPPb54k7p71a9Ua9NqblrRT9scH0WXcuk8YwdOAVO7tFlvf2KyRiHU3TQiTimYqX9OZGCyaS6kftQcKZBJm2rV2pE27S2URUzNcVTBR1xpxQnMG8Q9CzUGeUWbsYP1FFjqOkq+kIBwFqTG42q7let6ldRbRNE9HT8d88AHQJS6BRLflJ7bzGgNmyh6mYf+vYUYBvCdH7g2bo1Z1f5k44O7D/zZn9al6dkXV/zoti4evle1WlPCFLTS5R/5Izi3DTKySafBJ9LJzoLylI91K1tiSUXGIH3zWR9xxeYZoIF1yIgQpLvoyS4nrPhe+2/m2QZfyFK+m27hagIU8xAgMBAAECggEAJlNVCY2+cHnpzOH+x5WcO4f7wuAOgNUKWOjhgfF22IFz0WTx0yW9+pDfRK88N94tFuawqyfKwNYtgay8xLI1CJsM0j8a3orAbwaT+oUY4eu+3lHcjiibsIL2bqeWMCN2Lq7ScO2qcy+KndSUg+w3mS+KQzbP4cBY9PWXXp3TcfGTcxpj3YmowNUfvFS4dAWZQeE1iE7rJls+Nb9If/hN8K9tZUfBGON+2jtZwLM2nRHgCPgkOvJgKGbVauCrZlWUFOSHiBGZR+bfGvQx4942t8WbzqS3eb9X8jXPCjFFWb1DiUAlpmsGKEc1NemWcGhKu1/d/VuWKE0GkyBNccBOjQKBgQDvcdQPkVCfEVWpTgc8VpRTtNhl4j51SIijeA+FAYDVa8NaUfcMml7pjWmKKnFJI94K7eGEJ7vN7EPG52xOU1iYWFq1T+xhLyh0gUz0DrFT9BkrGDpqSn9wRATbbc9Gqdm32WneLCz9We8MSD+LF6RXescdaCjGO03iLBAUcV4tkwKBgQDpxw0mclHbg4t4xd1IY17hvvYM2ORSXtt44d4jCkezfsrPPtRg/SSG5wGcOIlP2sIwYv2SCcZTOLbKoRLyEZLXqK0w0Cx7Imc6jB/p7Gj7mNU/7HQUtwvob+gkQOXPtcPfGGvGMsWm7z1Q4uvwDXzs6EuyXenUE41Vm22yQ35qqwKBgC7V0gf1gZKLnnjOVWX8/WheIFHVbigctvVan5aBk8SrHnwFOlCRxWzjhzhKUvxecqkqnIjwCLEfvKYkUDAF53dtGNkMOA1OXxhizj2SvibQwTeHtq1hwwmflF+jW/7TbE2kzitx8p7fv31kiGFZj4C4+EeNPyR/Jx3NRpvpDOXXAoGBANsqqvBtYsK6a4pZbeBMkQpw3fojaMK0fWuxzXDqVVg5OWfcTn1zNchnUAImmszLmRyF4ZYFJfKli/Eh20IoKZOXZm8J63mxQjgIYG8NHUsq+FnKkvVMupQ6PdenJAx8Ktq/6WJR/S1IwyJO68UM0B7GlRjupKYXgnxMkCX80sqrAoGBAJPq+jvIAsDMpb6VXdpQynXokKbfds2JY5pT5neUI9mccRczCY585NroZKYb5cpxpF3bdXkpYzPY+RFUA3usZUluZHui/Kw/yMDIhm3EwLjH0aFVNg5pgM+6AjWfHFL39wUSsCKLwLiSnZ00JXQalJcm3ejzTX1lOXqtJIgm0G9X";
		String encryptStr = "1apyWqMF/WbRIzhFIYHMn3sKSHBU+2q2rQVEU9wZH9O68Kv+hdjWVUDSZ/bOGnVaT+Dp6i0CeRiCHaXB3DwxsvdzVK4+OUGl67amSkin1J3ozE9IaAZQqxDnLkbWoG5YBRuMDB/cQ6qlk5Y+YogDJrLAvrtRW143LoNc4bp/7gyHeIMEGiFah+YsZePz6unZMp2534dtiwfHiwQjkxLfGRweIxKUfvBNaIdq0Ygi3y4qCe+xvIB2Ll8jxbb19FFzQYLqA3fo4uliofiyEyfOk4kP4YJ+hPi7MOPSUwwmaLjxBoZbEP/SjJIkEFX7hx7onCJ29oQ1/mDbkqJnq2NM3Q==";
		String message = RsaCodingUtils.decryptByPrivateKey(encryptStr, privateKey);
		System.out.println(message);
	}

	public static void main(String[] args) {
		// Security.addProvider(new BouncyCastleProvider());
		//
		// byte[] input = "abc".getBytes();
		// Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
		// SecureRandom random = new SecureRandom();
		// KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA",
		// "BC");
		//
		// generator.initialize(256, random);
		//
		// KeyPair pair = generator.generateKeyPair();
		// Key pubKey = pair.getPublic();
		// Key privKey = pair.getPrivate();
		//
		// cipher.init(Cipher.ENCRYPT_MODE, pubKey, random);
		// byte[] cipherText = cipher.doFinal(input);
		// System.out.println("cipher: " + new String(cipherText));
		//
		// cipher.init(Cipher.DECRYPT_MODE, privKey);
		// byte[] plainText = cipher.doFinal(cipherText);
		// System.out.println("plain : " + new String(plainText));
	}

}
