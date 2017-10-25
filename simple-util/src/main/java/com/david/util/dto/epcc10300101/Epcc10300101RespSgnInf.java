package com.david.util.dto.epcc10300101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10300101RespSgnInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnNo")
	private String sgnNo;

	@XmlElement(name = "SgnAcctIssrId")
	private String sgnAcctIssrId;

	@XmlElement(name = "SgnAccTp")
	private String sgnAccTp;

	public String getSgnNo() {
		return sgnNo;
	}

	public void setSgnNo(String sgnNo) {
		this.sgnNo = sgnNo;
	}

	public String getSgnAcctIssrId() {
		return sgnAcctIssrId;
	}

	public void setSgnAcctIssrId(String sgnAcctIssrId) {
		this.sgnAcctIssrId = sgnAcctIssrId;
	}

	public String getSgnAccTp() {
		return sgnAccTp;
	}

	public void setSgnAccTp(String sgnAccTp) {
		this.sgnAccTp = sgnAccTp;
	}

	@Override
	public String toString() {
		return "Epcc10300101RespSgnInf [sgnNo=" + sgnNo + ", sgnAcctIssrId=" + sgnAcctIssrId + ", sgnAccTp=" + sgnAccTp + "]";
	}

}
