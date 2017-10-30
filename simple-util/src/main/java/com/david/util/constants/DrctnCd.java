package com.david.util.constants;

/**
 * 报文方向
 * 
 * @author dailiwei
 *
 */
public class DrctnCd {
	/**
	 * 支付机构到平台
	 */
	public static final String PAYMENT_INSTITUTION_TO_EPCC = "11";

	/**
	 * 付款行/退款行/签约行平台
	 */
	public static final String BANK_TO_EPCC = "12";

	/**
	 * 收款行到平台
	 */
	public static final String PAY_BANK_TO_EPCC = "13";

	/**
	 * 平台到支付机构
	 */
	public static final String EPCC_TO_PAYMENT_INSTITUTION = "21";

	/**
	 * 平台到付款行/退款行/签约行
	 */
	public static final String EPCC_TO_BANK = "22";

	/**
	 * 平台到付款行
	 */
	public static final String EPCC_TO_PAY_BANK = "23";
}
