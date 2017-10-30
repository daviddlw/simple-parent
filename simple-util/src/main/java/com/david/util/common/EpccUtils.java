package com.david.util.common;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.david.util.constants.Constants;
import com.david.util.constants.ReservationNumber;

public class EpccUtils {
	private static String ctrlNbfNo = "77";
	private static List<String> ctrlNblList = Arrays.asList(new String[] { "0100", "0101", "0102", "0103", "0104", "0105", "0106", "0107", "0108", "0109",
			"0000", "0001", "0002", "0003", "0004", "0005", "0006", "0007", "0008", "0009" });
	private static final SimpleDateFormat sdf = new SimpleDateFormat(Constants.YYYYMMDD);

	/**
	 * 交易流水号生成
	 * 
	 * @return
	 */
	public static String genTransSerialNo() {
		String controlNo = String.format("%s%s", ctrlNbfNo, ctrlNblList.get(RandomUtils.nextInt(0, ctrlNblList.size())));
		System.out.println("controlNo=" + controlNo);
		String transSerialNo = String.format("%s%s%s%s", sdf.format(new Date()), RandomStringUtils.randomNumeric(16), ReservationNumber.QA, controlNo);
		System.out.println(transSerialNo);
		return transSerialNo;
	}

	/**
	 * 生成随机批次号
	 * 
	 * @return 批次号
	 */
	public static String genBatchId() {
		String batchId = String.format("B%s%s", sdf.format(new Date()), RandomStringUtils.randomNumeric(4));
		return batchId;
	}

	/**
	 * 生成随机订单号
	 * 
	 * @return
	 */
	public static String genOrderId() {
		String orderId = String.format("%s%s", sdf.format(new Date()), RandomStringUtils.randomNumeric(12));
		return orderId;
	}

	/**
	 * 生成随机金额
	 * 
	 * @return 随机金额
	 */
	public static String getRandomAmount() {
		return "CNY" + new BigDecimal(RandomUtils.nextDouble(0, 1000)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 获取网联响应中的实际响应内容
	 * 
	 * @param result
	 *            响应字符串
	 * @return 实际响应内容
	 */
	public static String getResponseStr(String result) {
		String responseStr = result.substring(result.indexOf("?>") + 2, result.indexOf("{S:"));
		System.out.println("responseStr=" + responseStr);
		return responseStr;
	}

	/**
	 * 获取网联响应中的签名字符串
	 * 
	 * @param result
	 *            响应字符串
	 * @return 签名字符串
	 */
	public static String getResponseSign(String result) {
		String responseSignStr = result.substring(result.indexOf("{") + 3, result.indexOf("}"));
		System.out.println("responseSignStr=" + responseSignStr);
		return responseSignStr;
	}

	/**
	 * 获取msgheader
	 * 
	 * @param result
	 *            输入参数
	 * @return MsgHeader返回
	 */
	public static String getMsgHeaderStr(String result) {
		if (StringUtils.isBlank("<MsgHeader>")) {
			throw new IllegalArgumentException("no <MsgHeader> string in the input string");
		}

		if (StringUtils.isBlank("</MsgHeader>")) {
			throw new IllegalArgumentException("no </MsgHeader> string in the input string");
		}
		String msgHeaderStr = StringUtils.substring(result, result.indexOf("<MsgHeader>"), result.indexOf("</MsgHeader>") + 12);
		return msgHeaderStr;
	}
}
