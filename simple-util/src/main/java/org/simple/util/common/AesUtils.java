package org.simple.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * Created by hehuaiyuan on 2017/8/29.
 */
public class AesUtils {

	private static final Logger logger = LoggerFactory.getLogger(AesUtils.class);

	public static final String ALGORITHM = "AES/ECB/PKCS5Padding";

	static {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}

	/**
	 * 生成随机的aesKey
	 * 
	 * @return 生成的随机的AesKey
	 */
	public static String genAes256Key() {
		String aesKey = "";
		try {
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(256);// 要生成多少位，只需要修改这里即可128, 192或256
			SecretKey sk = kg.generateKey();
			byte[] b = sk.getEncoded();
			aesKey = byteToHexString(b);
			System.out.println(aesKey);
			return aesKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("没有此算法。");
		}
		return aesKey;
	}

	private static String byteToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String strHex = Integer.toHexString(bytes[i]);
			if (strHex.length() > 3) {
				sb.append(strHex.substring(6));
			} else {
				if (strHex.length() < 2) {
					sb.append("0" + strHex);
				} else {
					sb.append(strHex);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 网联敏感字段AES加密
	 * 
	 * @param str
	 *            敏感字段原文
	 * @param key
	 *            AES密钥.getByte()
	 * @return
	 */
	public static byte[] Aes256Encode(String str, byte[] key) {
		byte[] result = null;
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); // 生成加密解密需要的Key
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			result = cipher.doFinal(str.getBytes("UTF-8"));
		} catch (Exception e) {
			logger.error("Aes256Encode error:", e);
		}
		return result;
	}

	/**
	 * 网联敏感字段密文解密
	 * 
	 * @param bytes
	 *            要被解密的密文.getByte
	 * @param key
	 *            AES密钥.getByte
	 * @return 解密后的字符串
	 */
	public static String Aes256Decode(byte[] bytes, byte[] key) {
		String result = null;
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); // 生成加密解密需要的Key
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] decoded = cipher.doFinal(bytes);
			result = new String(decoded, "UTF-8");
		} catch (Exception e) {
			logger.error("Aes256Decode error:", e);
		}
		return result;
	}

	/**
	 * 网联敏感字段密文解密--主要用于解密对账明细文件
	 * 
	 * @param bytes
	 *            要被解密的密文.getByte
	 * @param key
	 *            AES密钥.getByte
	 * @return 解密后的byte数组
	 */
	public static byte[] Aes256DecodeToByte(byte[] bytes, byte[] key) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); // 生成加密解密需要的Key
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] decoded = cipher.doFinal(bytes);
			return decoded;
		} catch (Exception e) {
			logger.error("Aes256DecodeToByte error:", e);
			return null;
		}
	}

}
