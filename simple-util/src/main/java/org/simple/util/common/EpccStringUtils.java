package org.simple.util.common;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.simple.util.constants.Constants;
import org.simple.util.exception.SimpleException;

/**
 * 
 * @author dailiwei
 *
 */
public class EpccStringUtils extends StringUtils {

	public static final String EMPTY = "";

	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1])); // NOSONAR

		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static String[] splitStr2Array(String source, String spliter) {
		String regex = spliter;
		if ((regex.equals("?")) || (regex.equals("*")) || (regex.equals(")")) || (regex.equals("(")) || (regex.equals("{")) || (regex.equals("$"))
				|| (regex.equals("+")) || (regex.equals(".")) || (regex.equals("|"))) {
			regex = "[" + regex + "]";
		}
		return source.split(regex);
	}

	public static String byte2Hex(byte[] srcBytes) {
		StringBuilder hexRetSB = new StringBuilder();
		for (byte b : srcBytes) {
			String hexString = Integer.toHexString(0xFF & b);
			hexRetSB.append(hexString.length() == 1 ? Integer.valueOf(0) : "").append(hexString);
		}
		return hexRetSB.toString();
	}

	public static byte[] hex2Bytes(String source) {
		byte[] sourceBytes = new byte[source.length() / 2];
		for (int i = 0; i < sourceBytes.length; i++) {
			sourceBytes[i] = ((byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16));
		}
		return sourceBytes;
	}

	public static String truncateString(String src, int length) {

		if (StringUtils.isNotBlank(src) && src.length() > length && length > 10) {
			src = src.substring(0, length - 4) + "...";
		}
		return src;
	}

	// 将Unicode码转换为中文
	public static String unicodeTozhCN(String unicode) {

		if (StringUtils.isBlank(unicode)) {
			return unicode;
		}

		StringBuffer gbk = new StringBuffer();
		String hex[] = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) { // 注意要从 1 开始，而不是从0开始。第一个是空。
			int data = Integer.parseInt(hex[i], 16); // 将16进制数转换为 10进制的数据。
			gbk.append((char) data); // 强制转换为char类型就是我们的中文字符了。
		}
		return gbk.toString();
	}

	// 将字符串转换为Unicode码
	public static String toUnicode(String zhStr) {

		if (StringUtils.isBlank(zhStr)) {
			return zhStr;
		}

		StringBuilder unicode = new StringBuilder();
		for (int i = 0; i < zhStr.length(); i++) {
			char c = zhStr.charAt(i);
			unicode.append("\\u");
			unicode.append(Integer.toHexString(c));
		}
		return unicode.toString();
	}

	public static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			try {
				return content.getBytes(Constants.UTF_8);
			} catch (UnsupportedEncodingException e) {
				throw new SimpleException("", e);
			}
		}

		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException ex) {
			throw new IllegalArgumentException("Not support:" + charset, ex);
		}
	}

	public static String nvlStr(Object contend) {
		return contend != null ? (contend.toString()) : "";
	}

	public static void append(StringBuilder sb, Object o) {
		if (o == null) {
			return;
		}
		sb.append(o.toString());
	}
}
