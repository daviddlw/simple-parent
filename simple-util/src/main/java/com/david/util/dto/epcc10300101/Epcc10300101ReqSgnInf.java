package com.david.util.dto.epcc10300101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10300101ReqSgnInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnAcctIssrId")
	private String sgnAcctIssrId;

	@XmlElement(name = "SgnNo")
	private String sgnNo;

	@XmlElement(name = "SgnAcctShrtId")
	private String sgnAcctShrtId;

	@XmlElement(name = "SgnAcctTp")
	private String sgnAcctTp;

	public String getSgnAcctIssrId() {
		return sgnAcctIssrId;
	}

	public void setSgnAcctIssrId(String sgnAcctIssrId) {
		this.sgnAcctIssrId = sgnAcctIssrId;
	}

	public String getSgnNo() {
		return sgnNo;
	}

	public void setSgnNo(String sgnNo) {
		this.sgnNo = sgnNo;
	}

	public String getSgnAcctShrtId() {
		return sgnAcctShrtId;
	}

	public void setSgnAcctShrtId(String sgnAcctShrtId) {
		this.sgnAcctShrtId = sgnAcctShrtId;
	}

	public String getSgnAcctTp() {
		return sgnAcctTp;
	}

	public void setSgnAcctTp(String sgnAcctTp) {
		this.sgnAcctTp = sgnAcctTp;
	}

	@Override
	public String toString() {
		return "Epcc10300101ReqSgnInf [sgnAcctIssrId=" + sgnAcctIssrId + ", sgnNo=" + sgnNo + ", sgnAcctShrtId=" + sgnAcctShrtId + ", sgnAcctTp="
				+ sgnAcctTp + "]";
	}

}
