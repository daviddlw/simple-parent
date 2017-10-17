package org.simple.util.test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 
* @Title: AESUtil.java  
* @Package com.allscore.finance.epcc.quick.util  
* @Description: TODO(Aes/ecb 工具类)  
* @author Zhym    
* @date 2017年9月12日 下午2:30:17
 */
public class AesEcbUtil {
	private static final Logger logger = Logger.getLogger(AesEcbUtil.class);

	/**
	 * 
	* @Description: TODO(加密)  
	* @param content
	* @param key
	* @return 
	* @throws  
	* @author Zhym    
	* @date 2017年9月12日 下午2:31:30
	 */
	public static byte[] encrypt(byte[] content, String key) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),
					"AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] byteRresult = cipher.doFinal(content);
			return byteRresult;
		} catch (Exception e) {
			logger.error("AesUtil.encrypt error!", e);
			throw new RuntimeException("AES加密错误");
		}
	}

	/**
	 * 
	* @Description: TODO(加密)  
	* @param content
	* @param key
	* @param charset
	* @return 
	* @throws  
	* @author Zhym    
	* @date 2017年9月12日 下午2:31:50
	 */
	public static String encrypt(String content, String key, String charset) {

		try {
			byte[] bContent = content.getBytes(charset);
			byte[] bResult = encrypt(bContent, key);
			return Base64.encodeBase64String(bResult).replaceAll("[\\s*\t\n\r]", "");
		} catch (Exception e) {
			// logger.error("AesUtil.encrypt error!", e);
			throw new RuntimeException("AES加密错误");
		}
	}

	/**
	 * 
	* @Description: TODO(解密)  
	* @param content
	* @param key
	* @return 
	* @throws  
	* @author Zhym    
	* @date 2017年9月12日 下午2:32:08
	 */
	public static byte[] decrypt(byte[] content, String key) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),
					"AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // "算法/模式/补码方式"
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (Exception e) {
			logger.error("AesUtil.decrypt error!", e);
			throw new RuntimeException("AES解密错误");
		}
	}

	/**
	 * 
	* @Description: TODO(解密)  
	* @param content
	* @param key
	* @param charset
	* @return 
	* @throws  
	* @author Zhym    
	* @date 2017年9月12日 下午2:32:23
	 */
	public static String decrypt(String content, String key, String charset) {
		try {
			byte[] bContent = Base64.decodeBase64(content);
			byte[] bResult = decrypt(bContent, key);
			return new String(bResult, charset);
		} catch (Exception e) {
			// logger.error("AesUtil.encrypt error!", e);
			throw new RuntimeException("AES加密错误");
		}
	}

	public static void main(String[] args) {
		/* 32*8位长密钥 需要 java8支持 且需要更换安全文件  */
		String tmpKey = "11111111111111111111111111111111";
		String str = "商银信支付服务有限责任公司123455";

		String enc = encrypt(str, tmpKey, "UTF-8");
		System.out.println("[" + enc + "]");
		System.out.println("[" + decrypt(enc, tmpKey, "UTF-8") + "]");

	}
}
