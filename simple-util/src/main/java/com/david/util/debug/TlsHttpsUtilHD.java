package com.david.util.debug;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

//import com.allscore.finance.ebank.web.epcc.quick.util.serialNo.response.util.QuickRepTools;

/**
 * 
 * @Title: TlsHttpsUtil.java
 * @Package com.allscore.finance.epcc.quick.util
 * @Description: TODO(网联平台https)
 * @author Zhym
 * @date 2017年9月12日 下午4:46:03
 */
public class TlsHttpsUtilHD {
	private static final Logger logger = Logger.getLogger(TlsHttpsUtilHD.class);

	public static final String KEY_STORE_TYPE_JKS = "jks";

	public static String execBase64Str(String privateKeyBase64, String priKeyPassWd, HttpPost httpPost) {
		CloseableHttpClient httpclient = null;
		SSLContext sslcontext = null;
		CloseableHttpResponse response = null;
		InputStream tsIn = null;
		HttpClientContext context = HttpClientContext.create();
		String resp = null;
		try {
			//加载jks证书
			KeyStore trustStore = KeyStore.getInstance(KEY_STORE_TYPE_JKS);
			tsIn = new ByteArrayInputStream(Base64.decodeBase64(privateKeyBase64));
			trustStore.load(tsIn, priKeyPassWd.toCharArray());
			// 设置信任自签名证书
			sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();

			httpclient = HttpClients.custom().setSSLContext(sslcontext).build();
			response = httpclient.execute(httpPost, context);
			resp = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			logger.error("TlsHttpsUtil.exec ERR:", e);
		} finally {
			if (tsIn != null) {
				try {
					tsIn.close();
				} catch (IOException e) {
				}
				tsIn = null;
			}

			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
				}
				httpclient = null;
			}

			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
				}
				response = null;
			}
		}

		return resp;
	}

	public static String exec(String priKeyPath, String priKeyPassWd, HttpPost httpPost) {
		CloseableHttpClient httpclient = null;
		SSLContext sslcontext = null;
		CloseableHttpResponse response = null;
		InputStream tsIn = null;
		HttpClientContext context = HttpClientContext.create();
		String resp = null;
		try {
			KeyStore trustStore = KeyStore.getInstance(KEY_STORE_TYPE_JKS);
			tsIn = new FileInputStream(new File(priKeyPath));
			trustStore.load(tsIn, priKeyPassWd.toCharArray());
			sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();

			httpclient = HttpClients.custom().setSSLContext(sslcontext).build();
			response = httpclient.execute(httpPost, context);
			resp = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			logger.error("TlsHttpsUtil.exec ERR:", e);
		} finally {
			if (tsIn != null) {
				try {
					tsIn.close();
				} catch (IOException e) {
				}
				tsIn = null;
			}

			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
				}
				httpclient = null;
			}

			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
				}
				response = null;
			}
		}

		return resp;
	}

	public static void main(String[] a) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {

		String privateKeyBase64 = "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";
		String priKeyPassWd = "123456";
		HttpPost httpPost = new HttpPost("https://59.151.65.97:551/preSvr");

		// 设置HTTPS 请求消息头 header头信息
		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("Content-Type", "application/xml;charset=utf-8");
		httpPost.setHeader("MsgTp", "epcc.401.001.01"); // 流水号申请报文
		httpPost.setHeader("OriIssrId", "Z2006845000013");
//		httpPost.setHeader("PyerAcctTp", "00");
//		httpPost.setHeader("PyeeAcctTp", "04");
//		httpPost.setHeader("ReservedField", "1000F0617T8800");

		String reqHead = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
		String reqXml = String.format("%s%s%s", "<root xmlns=\"namespace_string\"><MsgHeader><SndDt>",
				new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss").format(new Date()),
				"</SndDt><MsgTp>epcc.401.001.01</MsgTp><IssrId>Z2006845000013</IssrId><Drctn>11</Drctn><SignSN>4002567531</SignSN></MsgHeader><MsgBody><InstgId>Z2006845000013</InstgId></MsgBody></root>"); // 2017-09-09T14:16:18
		String priKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDaqMfKwPPb54k7p71a9Ua9NqblrRT9scH0WXcuk8YwdOAVO7tFlvf2KyRiHU3TQiTimYqX9OZGCyaS6kftQcKZBJm2rV2pE27S2URUzNcVTBR1xpxQnMG8Q9CzUGeUWbsYP1FFjqOkq+kIBwFqTG42q7let6ldRbRNE9HT8d88AHQJS6BRLflJ7bzGgNmyh6mYf+vYUYBvCdH7g2bo1Z1f5k44O7D/zZn9al6dkXV/zoti4evle1WlPCFLTS5R/5Izi3DTKySafBJ9LJzoLylI91K1tiSUXGIH3zWR9xxeYZoIF1yIgQpLvoyS4nrPhe+2/m2QZfyFK+m27hagIU8xAgMBAAECggEAJlNVCY2+cHnpzOH+x5WcO4f7wuAOgNUKWOjhgfF22IFz0WTx0yW9+pDfRK88N94tFuawqyfKwNYtgay8xLI1CJsM0j8a3orAbwaT+oUY4eu+3lHcjiibsIL2bqeWMCN2Lq7ScO2qcy+KndSUg+w3mS+KQzbP4cBY9PWXXp3TcfGTcxpj3YmowNUfvFS4dAWZQeE1iE7rJls+Nb9If/hN8K9tZUfBGON+2jtZwLM2nRHgCPgkOvJgKGbVauCrZlWUFOSHiBGZR+bfGvQx4942t8WbzqS3eb9X8jXPCjFFWb1DiUAlpmsGKEc1NemWcGhKu1/d/VuWKE0GkyBNccBOjQKBgQDvcdQPkVCfEVWpTgc8VpRTtNhl4j51SIijeA+FAYDVa8NaUfcMml7pjWmKKnFJI94K7eGEJ7vN7EPG52xOU1iYWFq1T+xhLyh0gUz0DrFT9BkrGDpqSn9wRATbbc9Gqdm32WneLCz9We8MSD+LF6RXescdaCjGO03iLBAUcV4tkwKBgQDpxw0mclHbg4t4xd1IY17hvvYM2ORSXtt44d4jCkezfsrPPtRg/SSG5wGcOIlP2sIwYv2SCcZTOLbKoRLyEZLXqK0w0Cx7Imc6jB/p7Gj7mNU/7HQUtwvob+gkQOXPtcPfGGvGMsWm7z1Q4uvwDXzs6EuyXenUE41Vm22yQ35qqwKBgC7V0gf1gZKLnnjOVWX8/WheIFHVbigctvVan5aBk8SrHnwFOlCRxWzjhzhKUvxecqkqnIjwCLEfvKYkUDAF53dtGNkMOA1OXxhizj2SvibQwTeHtq1hwwmflF+jW/7TbE2kzitx8p7fv31kiGFZj4C4+EeNPyR/Jx3NRpvpDOXXAoGBANsqqvBtYsK6a4pZbeBMkQpw3fojaMK0fWuxzXDqVVg5OWfcTn1zNchnUAImmszLmRyF4ZYFJfKli/Eh20IoKZOXZm8J63mxQjgIYG8NHUsq+FnKkvVMupQ6PdenJAx8Ktq/6WJR/S1IwyJO68UM0B7GlRjupKYXgnxMkCX80sqrAoGBAJPq+jvIAsDMpb6VXdpQynXokKbfds2JY5pT5neUI9mccRczCY585NroZKYb5cpxpF3bdXkpYzPY+RFUA3usZUluZHui/Kw/yMDIhm3EwLjH0aFVNg5pgM+6AjWfHFL39wUSsCKLwLiSnZ00JXQalJcm3ejzTX1lOXqtJIgm0G9X";
		String reqSign = String.format("%s%s%s", "{S:", RSASignatureUtil.sign(reqXml, priKey), "}");

		String xmlStr = reqHead + reqXml + "\r\n" + reqSign;
		System.out.println("请求报文：" + xmlStr);
		StringEntity stringEntity = new StringEntity(xmlStr);
		httpPost.setEntity(stringEntity);

//		String resp = exec(priKeyPath, priKeyPassWd, httpPost);
		String resp = execBase64Str(privateKeyBase64, priKeyPassWd, httpPost);
		System.out.println();
		System.out.println("返回报文：" + resp);

		System.out.println();
	}
}
