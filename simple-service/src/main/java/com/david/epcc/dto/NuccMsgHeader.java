package com.david.epcc.dto;

/**
 * 网连请求报文头
 * 
 * @author dailiwei
 *
 */
public class NuccMsgHeader {

	private String SndDt;

	private String MsgTp;

	private String IssrId;

	private String Drctn;

	private String SignSN;

	private String NcrptnSN;

	private String DgtlEnvlp;

	public String getSndDt() {
		return SndDt;
	}

	public void setSndDt(String sndDt) {
		SndDt = sndDt;
	}

	public String getMsgTp() {
		return MsgTp;
	}

	public void setMsgTp(String msgTp) {
		MsgTp = msgTp;
	}

	public String getIssrId() {
		return IssrId;
	}

	public void setIssrId(String issrId) {
		IssrId = issrId;
	}

	public String getDrctn() {
		return Drctn;
	}

	public void setDrctn(String drctn) {
		Drctn = drctn;
	}

	public String getSignSN() {
		return SignSN;
	}

	public void setSignSN(String signSN) {
		SignSN = signSN;
	}

	public String getNcrptnSN() {
		return NcrptnSN;
	}

	public void setNcrptnSN(String ncrptnSN) {
		NcrptnSN = ncrptnSN;
	}

	public String getDgtlEnvlp() {
		return DgtlEnvlp;
	}

	public void setDgtlEnvlp(String dgtlEnvlp) {
		DgtlEnvlp = dgtlEnvlp;
	}

	@Override
	public String toString() {
		return "NuccMsgHeader [SndDt=" + SndDt + ", MsgTp=" + MsgTp + ", IssrId=" + IssrId + ", Drctn=" + Drctn + ", SignSN=" + SignSN + ", NcrptnSN="
				+ NcrptnSN + ", DgtlEnvlp=" + DgtlEnvlp + "]";
	}

}
