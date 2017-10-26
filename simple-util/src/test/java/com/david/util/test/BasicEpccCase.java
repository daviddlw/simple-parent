package com.david.util.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.david.util.common.HttpUtils;
import com.david.util.common.JaxbUtils;
import com.david.util.common.RsaUtils;
import com.david.util.constants.LogLevel;
import com.david.util.dto.MsgHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicEpccCase {

	protected static Logger logger = LoggerFactory.getLogger(LogLevel.LOG_TEST);

	protected static final String SUCCESS_CODE = "00000000";
	protected static final String Z2006845000013 = "Z2006845000013";
	protected static final String C1010611003601 = "C1010611003601";
	protected static final String SGN_NO = "725014142";
	protected static final String RESFD_INF_INSTG_ACCTID = "10012786190055100977";

	protected static final String CONNECTION = "Connection";
	protected static final String ORI_ISSR_ID = "OriIssrId";
	protected static final String MSG_TP = "MsgTp";
	protected static final String EPCC_PROT_443_URL = "https://59.151.65.97:443/preSvr";
	protected static final String EPCC_PROT_551_URL = "https://59.151.65.97:551/preSvr";
	protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	protected static final String ACCT_TP_00 = "00";
	protected static final String ACCT_TP_04 = "04";
	// 加密私钥
	protected static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDaqMfKwPPb54k7p71a9Ua9NqblrRT9scH0WXcuk8YwdOAVO7tFlvf2KyRiHU3TQiTimYqX9OZGCyaS6kftQcKZBJm2rV2pE27S2URUzNcVTBR1xpxQnMG8Q9CzUGeUWbsYP1FFjqOkq+kIBwFqTG42q7let6ldRbRNE9HT8d88AHQJS6BRLflJ7bzGgNmyh6mYf+vYUYBvCdH7g2bo1Z1f5k44O7D/zZn9al6dkXV/zoti4evle1WlPCFLTS5R/5Izi3DTKySafBJ9LJzoLylI91K1tiSUXGIH3zWR9xxeYZoIF1yIgQpLvoyS4nrPhe+2/m2QZfyFK+m27hagIU8xAgMBAAECggEAJlNVCY2+cHnpzOH+x5WcO4f7wuAOgNUKWOjhgfF22IFz0WTx0yW9+pDfRK88N94tFuawqyfKwNYtgay8xLI1CJsM0j8a3orAbwaT+oUY4eu+3lHcjiibsIL2bqeWMCN2Lq7ScO2qcy+KndSUg+w3mS+KQzbP4cBY9PWXXp3TcfGTcxpj3YmowNUfvFS4dAWZQeE1iE7rJls+Nb9If/hN8K9tZUfBGON+2jtZwLM2nRHgCPgkOvJgKGbVauCrZlWUFOSHiBGZR+bfGvQx4942t8WbzqS3eb9X8jXPCjFFWb1DiUAlpmsGKEc1NemWcGhKu1/d/VuWKE0GkyBNccBOjQKBgQDvcdQPkVCfEVWpTgc8VpRTtNhl4j51SIijeA+FAYDVa8NaUfcMml7pjWmKKnFJI94K7eGEJ7vN7EPG52xOU1iYWFq1T+xhLyh0gUz0DrFT9BkrGDpqSn9wRATbbc9Gqdm32WneLCz9We8MSD+LF6RXescdaCjGO03iLBAUcV4tkwKBgQDpxw0mclHbg4t4xd1IY17hvvYM2ORSXtt44d4jCkezfsrPPtRg/SSG5wGcOIlP2sIwYv2SCcZTOLbKoRLyEZLXqK0w0Cx7Imc6jB/p7Gj7mNU/7HQUtwvob+gkQOXPtcPfGGvGMsWm7z1Q4uvwDXzs6EuyXenUE41Vm22yQ35qqwKBgC7V0gf1gZKLnnjOVWX8/WheIFHVbigctvVan5aBk8SrHnwFOlCRxWzjhzhKUvxecqkqnIjwCLEfvKYkUDAF53dtGNkMOA1OXxhizj2SvibQwTeHtq1hwwmflF+jW/7TbE2kzitx8p7fv31kiGFZj4C4+EeNPyR/Jx3NRpvpDOXXAoGBANsqqvBtYsK6a4pZbeBMkQpw3fojaMK0fWuxzXDqVVg5OWfcTn1zNchnUAImmszLmRyF4ZYFJfKli/Eh20IoKZOXZm8J63mxQjgIYG8NHUsq+FnKkvVMupQ6PdenJAx8Ktq/6WJR/S1IwyJO68UM0B7GlRjupKYXgnxMkCX80sqrAoGBAJPq+jvIAsDMpb6VXdpQynXokKbfds2JY5pT5neUI9mccRczCY585NroZKYb5cpxpF3bdXkpYzPY+RFUA3usZUluZHui/Kw/yMDIhm3EwLjH0aFVNg5pgM+6AjWfHFL39wUSsCKLwLiSnZ00JXQalJcm3ejzTX1lOXqtJIgm0G9X";
	protected static String certPath = "C:" + File.separator + "epcc" + File.separator + "wanglian-rsa.cer";
	// 四要素
	protected static final String ID_NO = "421127198509140413";
	protected static final String ID_CARD_NAME = "张三丰1";
	protected static final String CARD_NO = "6214832130521235";
	protected static final String MOBILE = "15800563769";

	// api
	protected static final String EPCC_401_001_01 = "epcc.401.001.01";
	protected static final String EPCC_101_001_01 = "epcc.101.001.01";
	protected static final String EPCC_103_001_01 = "epcc.103.001.01";
	protected static final String EPCC_201_001_01 = "epcc.201.001.01";
	protected static final String EPCC_205_001_01 = "epcc.205.001.01";

	protected MsgHeader getMsgHeader(String msgTp, String ncrptnSN, String dgtlEnvlpStr) {
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setSndDt(new Date());
		msgHeader.setMsgTp(msgTp);
		msgHeader.setIssrId(Z2006845000013);
		msgHeader.setDrctn("11");
		msgHeader.setSignSN("4002567531");
		if (StringUtils.isNotBlank(ncrptnSN)) {
			msgHeader.setNcrptnSN(ncrptnSN);
		}
		if (StringUtils.isNotBlank(dgtlEnvlpStr)) {
			msgHeader.setDgtlEnvlp(dgtlEnvlpStr);
		}
		return msgHeader;
	}

	/**
	 * 请求网联接口
	 * 
	 * @param url
	 *            网联接口地址
	 * @param api
	 *            调用的API
	 * @param request
	 *            请求参数
	 * @return 响应结果
	 * @throws Exception
	 *             异常
	 */
	protected <T> String postToEpccGateway(String url, String api, T request) throws Exception {
		String requestXml = JaxbUtils.toXmlNoHeader(request);
		logger.info("requestXml=" + requestXml);
		String signStr = RsaUtils.sign(privateKey, requestXml);
		logger.info("signStr=" + signStr);
		String requestBody = String.format("%s%s{S:%s}", JaxbUtils.XML_HEADER, requestXml, signStr);
		logger.info("requestBody=" + requestBody);
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put(MSG_TP, api);
		headerMap.put(ORI_ISSR_ID, Z2006845000013);
		headerMap.put(CONNECTION, "keep-alive");
		// headerMap.put("PyerAcctTp", "00");
		// headerMap.put("PyeeAcctTp", "04");
		String result = HttpUtils.httpXmlPost(url, requestBody, headerMap);
		logger.info("result=" + result);
		return result;
	}
}
