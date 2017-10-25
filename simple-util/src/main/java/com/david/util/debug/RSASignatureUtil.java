package com.david.util.debug;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @Title: RSASignatureUtil.java
 * @Package com.allscore.finance.epcc.quick.util
 * @Description: TODO(RSA 签名 验签)
 * @author Zhym
 * @date 2017年9月12日 下午5:47:48
 */
public class RSASignatureUtil {

	public static final String SIGN_ALGORITHMS = "SHA256withRSA";

	/**
	 * 
	 * @Description: TODO(RSA签名)
	 * @param content
	 * @param privateKey
	 * @param encode
	 * @return
	 * @throws
	 * @author Zhym
	 * @date 2017年9月12日 下午5:46:58
	 */
	public static String sign(String content, String privateKey, String encode) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decodeBase64(privateKey));

			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(encode));

			byte[] signed = signature.sign();

			return Base64.encodeBase64String(signed).replaceAll("[\\s*\t\n\r]",
					"");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String sign(String content, String privateKey) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decodeBase64(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			java.security.Signature signature = java.security.Signature
					.getInstance("SHA256withRSA");
			signature.initSign(priKey);
			signature.update(content.getBytes());
			byte[] signed = signature.sign();
			return Base64.encodeBase64String(signed).replaceAll("[\\s*\t\n\r]",
					"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Description: TODO(RSA验签名检查)
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @param encode
	 * @return
	 * @throws
	 * @author Zhym
	 * @date 2017年9月12日 下午5:46:25
	 */
	public static boolean doCheck(String content, String sign,
			String publicKey, String encode) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decodeBase64(publicKey);
			PublicKey pubKey = keyFactory
					.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			//去除换行符
			content = content.replaceAll("\r\n", "");
			signature.update(content.getBytes(encode));

			boolean bverify = signature.verify(Base64.decodeBase64(sign));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean doCheck(String content, String sign, String publicKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decodeBase64(publicKey);
			PublicKey pubKey = keyFactory
					.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes());

			boolean bverify = signature.verify(Base64.decodeBase64(sign));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static void main(String[] args) throws CertificateException,
			IOException {
		String reqXml = "<root xmlns=\"namespace_string\"><MsgHeader><SndDt>2017-09-09T14:16:18</SndDt><MsgTp>epcc.401.001.01</MsgTp><IssrId>Z2017211000011</IssrId><Drctn>11</Drctn><SignSN>4002225129</SignSN></MsgHeader><MsgBody><InstgId>Z2017211000011</InstgId></MsgBody></root>";
		String priKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5zWztV4YgOzTBDq14aN6Ba+S4teKu4x6tw/POav4F4OELNbUgMlf9qbZ+ehc63qornEprlg9qcQCPIiSF5FKP7fgO2ge3CfJE6OwqdRDMQRubwouz4cDhCV82rHBac4xyu2cVeJZm1FdUwO6cATfawdRUh+n/jSJl8Y2eq537oKM93yuBtma90d25ialK0qbLvqrnTXuVVUwtUP9Vbw8kc4LAa66o2qTusjcUZCZpmKT63iTHXu1G1ygtv3FoWu4UCk1ZLXBoDsWcsI7+u6ja2P0GpMO96B35exo5sP2A9DVy7zNTOAVwCJbhH0/qjkV/O5YNsdN+63rTyT+9UZ+vAgMBAAECggEAD8kkOAX3n0Vodf6JPnGqddR02jyZxcIGGT1MuXzdmCJC/HDTsqiUUTjaCGTXFGlWi937dKiLJr/zDDYaZEXBvfi52okrnqF5s+EWL4VCqBfgAJvGwsGrYXMqY7rC0OyAQj8pwuk73gCsasb5fsebtzpAj+38M3oKMh8TqSdrhLU8DMtwdaoVGjXkRk1v6RCrrpwNgoxrMC1HmPi7EKb0kh9PBfesJByrHRdyw8GnK2sSeIITh99hFLxDlPnfgIGD1+W327KY3DEWhLeZJ58GEIXINUnEK+Gycf0YT7FXQaL2RMtpqIZkrFE1hYwQpWPG8QhY1sLQikxBLYsnwRUIAQKBgQDl+Uvp2pN4DiaeNOBJ6JcP7hwugaficrVW/HE8JWc1aO0fPLiHkyHjkcohcckpHrw88RPclrOMwaXhbUTndhyjHGjICpmYCswJ3N96LUBpku6HNxkjhsONfjsckeTczDoHem+gpDQg8kwttXMU6pLTe/tZXNpFVo9oWGSTgMwfkQKBgQDO1Gov8mwAJN1XO8Fc4LwbUAmnIMbOQd4hCpspvqaBxmGN8EP5q2K40n1sqQXjrhF4DrsEDfX/9poW4PanoUbro1RKZCpwsiL6AtiJFYTqtD6oD3Ri5fQW7sPuWtkMtP462v7oklK76CHX8L/Oy4yImJ3xsoNbiCOi83hKkgCrPwKBgCA5SVDQRr+YxAKBCDhy9c/OnnZJxpttVUmF/9vcpdSPCkLJQFYG4MXBZOBA0F4Jp3imkxDisO/1Jmgq5m/EVgjo7ymwiosKYEK1hz7YXmEsrG2X49bdiEMoP+Il8zqZBzyLBF8vAvrm2JPDDAgPQlmN0s9XumHi2h9V22nrry5xAoGBAMW0uoR2oNJKauIWFrHniSg6i9+cLy2+PM0szEFNIvDuZxY80113fHjbjFUwBJctLzmqKw6nIb25RnYUOPLn8VkR2WL6+K6dOV3vFnmDfni0LyiLDV/0HC1HszbG1K+W22X7LKu4IynHrYIzecX9QKrZ6DC/yVzXd+kt/pCZm9YVAoGABx/6NyydqTI2fKaGGvRTvNZqwIrH7EPOQpX0f2pO86PdlE7AXwmWeuLuJnfEoEGJ81jUy+3Xy4e+LQw0/yIvRePFlSbqkRA1ErMfz/7x/X79vwXXhYAXtsoJyKlbcIKoq4mi2/QIcXsBg4Nk9/eMZgdxks+QwWaCMl/pID3vJyg=";
		String signString = sign(reqXml, priKey);
		System.out.println(signString);

		CertInfo certInfo = CertUtil
				.getX509CertInfo("Q:\\epcc\\wanglian-rsa.cer");
		String respXml = "<root xmlns=\"namespace_string\"><MsgHeader><SndDt>2017-09-11T15:08:36</SndDt><MsgTp>epcc.402.001.01</MsgTp><IssrId>G4000311000018</IssrId><Drctn>21</Drctn><SignSN>4000068829</SignSN></MsgHeader><MsgBody><InstgId>Z2017211000011</InstgId><CtrlNbInf></CtrlNbInf><SysRtnInf><SysRtnCd>ES000098</SysRtnCd><SysRtnDesc>系统异常</SysRtnDesc><SysRtnTm>2017-09-11T15:08:36</SysRtnTm></SysRtnInf></MsgBody></root>";
		String respSign = "upo6atXYJpq6hs/THpFKTYTfxthuetW4YCWCrR993fMs10CS3dxYrhWK162RNiS8I6iofbj2bkWigmo2KD0YzszvLHlP9gJtfXsizwe6JFUWKbrpSl94R8cFUyZ4REW78fvoW28l2tL6IaqoiU60K/8QxvzMfkuEgmkUqZvfYs+X3sxEdK2cG/Z/f6Py8MHmyFnhOl4UKN6bjtMz42Rbh5sLN6RXlU0LN8H4ZN5cTyv8seqJOhZFwtR4DjmJoAJwTFuTc62eQNqU7DKhlFeKex1VkKwdX2G4/lyn0rx0IzzYZmD66vAi8b4FzExxZnepPfNDqlVA9852mVrVgDj8Yw==";
		boolean signCheck = doCheck(respXml, respSign, certInfo.getKey());
		System.out.println("signCheck=" + signCheck);
	}
}