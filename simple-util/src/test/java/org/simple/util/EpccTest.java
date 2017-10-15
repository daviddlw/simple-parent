package org.simple.util;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Assert;
import org.junit.Test;
import org.simple.util.common.RsaCodingPKCS10Utils;
import org.simple.util.common.RsaCodingUtils;
import org.simple.util.common.RsaReadPKCS10Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EpccTest {

	private static Logger logger = LoggerFactory.getLogger(EpccTest.class);

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
	public void testEncryptAndDecrpt() {
		String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjJPgT6DAH8gsjFp0CmtI3zOkVs/zGu4D/ELnx6AydZOVsXcl2CiTuPI92xihMr1lmrVn6MG/AwA3vLFfEzGkV3+vJnr6/aVZn/QMgRx/cWWJ8nDPlVh5X26zDNSR+LRsJx4KnHiT1UZFjHMcU7jSbrmEwi7wyOko/O8e4SGXqfjKpyfZWUmUgLKKlW9NqpciKBEdk2MOMJRkDpBNfaJ1m8xXfZD3hMxkdllKtmup7oiSyEmoZCjRm9hSbD8XZltRL8nH2VhWqFey86tLJ2HIvBFPMGcVIahiSnx6BLBWX5H998d7lYtU0rOkagELIVTZ5ttREkzOnNzYHJ0ZaRWaTAgMBAAECggEAXmN8OCBE8cPjF6j0rFYgu/INBOZ+oAhJyMjHnCLycXm3luvoLCgQV9mn80MQe9WTtukivrKZZX54UWFpYk4VmndUNvFmUN5EBKQI0/ryfw0kAw5opA4gPhq1qLctZub31r+cYs34+gn4QMycjgqHW28XgfvGpZS98jnHAmK4vGLcWfHWlTmhWUMOKjY/j1hntkBg8fHu0/DsgwTooVFuXXQCf9JSk8UDg2LUK+JoGuxYrbtbQtEKRKMtynwyNzzSOXF1Zni+UwuHspw8RQNxymjoMQup3Y/+W6IRzdRodaT121u6kqSVPhzXm6u+Vy/pWh+lPJsGAUdMj9bgf914iQKBgQDYIIYJu1/Jwru/NuBj4+B18ikDI42OqiZ9DmpWr0W37KBQar1559ajXNvtUkKGyp4/VchslZa7atWa8oq3ACUiwga31+w7h6UOoOSLfBEIlly0Chh1EwNFLU9qwcyjRFJlHWrjxMCNTO5kjiR1mWHfKv/GHAMB6N6JvQlytuVy7QKBgQDBPiAoUpihn9xBjALF1bJ1U1/hSATpGpMWTA4MxhsGtANcBGoJ/oI2g8d6C63rKqfSCXQbU8vbaWX3aK1EB1rLtn83b34KbF8sZmMRAYBd6QjUUbvjJ8jfdOQgSWoVy6pG73YRlmaccabO3cYNGeW7osdz1e+Am1gWMaqB3VuPfwKBgD8DnJER1Kfa4yqpMVtugW9iH5sOrqM98OLdZRvJFqa9kzBfLsdwVSBkXqsfJM5wHRuSQ1GFe+FBEO6QcOlSXYF42MSZbAMfVXyiZIBWuBCs5nKHzwGzfWyZ/WG/B49CyFM69INcRlfsakVfPDodcm/C9bgpSqcv4lJ+j1anBNcxAoGAXHBRhbhtFvOXsys8sFJXOdSWSvfekXuK3OGo3udtSjmWWdAqwXkj5NwI8hLgMVyIRusKxX/CRsUWZ9GFAMPb5RL5rBUDYA0TQfQAc8QaGdI9zQMEM9d2OZ56+g7kVmTEoYYvHp6pmzCqsM5ZzS6UPLvTWU//b6/f5U4A2HqBMBUCgYEAvEq0+8kGQDPg5kRgXUyDxYWTchjsGP+JLKQDY5b6w9sXjOJk0vOp6/5A+EBJxzZ/BKSgyh5CWO48AlPssbP6xVPGJBRdV82EINmsHjW1PptJ/61PNokpN0rnmiQq25AF29sBHIZq4QwVfQHcOY1kfSso5ZDM/kqlKQtKc8IV7KU=";
		System.out.println(privateKey);
		StringBuilder sb = new StringBuilder();
		sb.append("<root xmlns=\"com.hdwtpay.epcc\">");
		sb.append("<MsgHeader>");
		sb.append("<SndDt>2017-10-15T15:35:00</SndDt>");
		sb.append("<MsgTp>epcc.401.001.01</MsgTp>");
		sb.append("<IssrId>Z2006845000013</IssrId>");
		sb.append("<Drctn>11</Drctn>");
		sb.append("<SignSN>4002567021</SignSN>");
		sb.append("<NcrptnSN></NcrptnSN>");
		sb.append("<DgtlEvnlp></DgtlEvnlp>");
		sb.append("</MsgHeader>");
		sb.append("<MsgBody>");
		sb.append("<InstgId>Z2006845000013</InstgId>");
		sb.append("</MsgBody>");
		sb.append("</root>");
		String message = sb.toString();
		System.out.println(message);

		String encodeMsg = RsaCodingUtils.encryptByPrivateKey(message, privateKey);
		System.out.println(encodeMsg);
		//
		// String pfxPath = "Q:" + File.separator + "epcc" + File.separator +
		// "4000397572_paftest.pfx";
		// String encodeMsg2 = RsaCodingUtils.encryptByPriPfxFile(message,
		// pfxPath, "1");
		// System.out.println(encodeMsg2);
		// Assert.assertEquals(encodeMsg, encodeMsg2);
	}

	@Test
	public void testDecryptText() {
		String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjJPgT6DAH8gsjFp0CmtI3zOkVs/zGu4D/ELnx6AydZOVsXcl2CiTuPI92xihMr1lmrVn6MG/AwA3vLFfEzGkV3+vJnr6/aVZn/QMgRx/cWWJ8nDPlVh5X26zDNSR+LRsJx4KnHiT1UZFjHMcU7jSbrmEwi7wyOko/O8e4SGXqfjKpyfZWUmUgLKKlW9NqpciKBEdk2MOMJRkDpBNfaJ1m8xXfZD3hMxkdllKtmup7oiSyEmoZCjRm9hSbD8XZltRL8nH2VhWqFey86tLJ2HIvBFPMGcVIahiSnx6BLBWX5H998d7lYtU0rOkagELIVTZ5ttREkzOnNzYHJ0ZaRWaTAgMBAAECggEAXmN8OCBE8cPjF6j0rFYgu/INBOZ+oAhJyMjHnCLycXm3luvoLCgQV9mn80MQe9WTtukivrKZZX54UWFpYk4VmndUNvFmUN5EBKQI0/ryfw0kAw5opA4gPhq1qLctZub31r+cYs34+gn4QMycjgqHW28XgfvGpZS98jnHAmK4vGLcWfHWlTmhWUMOKjY/j1hntkBg8fHu0/DsgwTooVFuXXQCf9JSk8UDg2LUK+JoGuxYrbtbQtEKRKMtynwyNzzSOXF1Zni+UwuHspw8RQNxymjoMQup3Y/+W6IRzdRodaT121u6kqSVPhzXm6u+Vy/pWh+lPJsGAUdMj9bgf914iQKBgQDYIIYJu1/Jwru/NuBj4+B18ikDI42OqiZ9DmpWr0W37KBQar1559ajXNvtUkKGyp4/VchslZa7atWa8oq3ACUiwga31+w7h6UOoOSLfBEIlly0Chh1EwNFLU9qwcyjRFJlHWrjxMCNTO5kjiR1mWHfKv/GHAMB6N6JvQlytuVy7QKBgQDBPiAoUpihn9xBjALF1bJ1U1/hSATpGpMWTA4MxhsGtANcBGoJ/oI2g8d6C63rKqfSCXQbU8vbaWX3aK1EB1rLtn83b34KbF8sZmMRAYBd6QjUUbvjJ8jfdOQgSWoVy6pG73YRlmaccabO3cYNGeW7osdz1e+Am1gWMaqB3VuPfwKBgD8DnJER1Kfa4yqpMVtugW9iH5sOrqM98OLdZRvJFqa9kzBfLsdwVSBkXqsfJM5wHRuSQ1GFe+FBEO6QcOlSXYF42MSZbAMfVXyiZIBWuBCs5nKHzwGzfWyZ/WG/B49CyFM69INcRlfsakVfPDodcm/C9bgpSqcv4lJ+j1anBNcxAoGAXHBRhbhtFvOXsys8sFJXOdSWSvfekXuK3OGo3udtSjmWWdAqwXkj5NwI8hLgMVyIRusKxX/CRsUWZ9GFAMPb5RL5rBUDYA0TQfQAc8QaGdI9zQMEM9d2OZ56+g7kVmTEoYYvHp6pmzCqsM5ZzS6UPLvTWU//b6/f5U4A2HqBMBUCgYEAvEq0+8kGQDPg5kRgXUyDxYWTchjsGP+JLKQDY5b6w9sXjOJk0vOp6/5A+EBJxzZ/BKSgyh5CWO48AlPssbP6xVPGJBRdV82EINmsHjW1PptJ/61PNokpN0rnmiQq25AF29sBHIZq4QwVfQHcOY1kfSso5ZDM/kqlKQtKc8IV7KU=";
		String encryptStr = "zYqNoqMNNcfNbrJQMQ2MfqsNTcziUk9ZlpLFjB+d1CtZP9weYpzgNWvaOF9VLv2CpJpXeCFvpiwlOf3m6g+96bJHQwin58pe18QHTzPlx0Kt+vLKra/FuVlIg03AyL1fiKAUWBMgrBB0i2iUldQ1Ldlx0GmpQWlkBMpFZrVbOla2BkTPLIVm09O1P7gtbA1uRD4i1c903XmCuAHarTwXkLNEQyF+J0jaWBQfH2sAeUeURvo1CHebfQqS8PcfOpRmcxZ0J7bUOwhutFv4ormhb+vWxrxnluNeotyzMmpFsBDrdkF54OKSw49ZKI1ClFT+HIvE4jSfyDyJjZu+M87zdQ==";
		String message = RsaCodingPKCS10Utils.decryptByPrivateKey(encryptStr, privateKey);
		System.out.println(message);
	}

}
