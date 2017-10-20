package org.simple.util.dto.epcc;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10100101RespSgnInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnAcctIssrId")
	private String sgnAcctIssrId;

	@XmlElement(name = "SgnAcctTp")
	private String sgnAcctTp;

	@XmlElement(name = "SgnAcctId")
	private String sgnAcctId;

	@XmlElement(name = "SgnAcctNm")
	private String sgnAcctNm;

	@XmlElement(name = "SgnAcctLvl")
	private String sgnAcctLvl;

	public String getSgnAcctIssrId() {
		return sgnAcctIssrId;
	}

	public void setSgnAcctIssrId(String sgnAcctIssrId) {
		this.sgnAcctIssrId = sgnAcctIssrId;
	}

	public String getSgnAcctTp() {
		return sgnAcctTp;
	}

	public void setSgnAcctTp(String sgnAcctTp) {
		this.sgnAcctTp = sgnAcctTp;
	}

	public String getSgnAcctId() {
		return sgnAcctId;
	}

	public void setSgnAcctId(String sgnAcctId) {
		this.sgnAcctId = sgnAcctId;
	}

	public String getSgnAcctNm() {
		return sgnAcctNm;
	}

	public void setSgnAcctNm(String sgnAcctNm) {
		this.sgnAcctNm = sgnAcctNm;
	}

	public String getSgnAcctLvl() {
		return sgnAcctLvl;
	}

	public void setSgnAcctLvl(String sgnAcctLvl) {
		this.sgnAcctLvl = sgnAcctLvl;
	}

	@Override
	public String toString() {
		return "Epcc10100101RespSgnInf [sgnAcctIssrId=" + sgnAcctIssrId + ", sgnAcctTp=" + sgnAcctTp + ", sgnAcctId=" + sgnAcctId + ", sgnAcctNm=" + sgnAcctNm
				+ ", sgnAcctLvl=" + sgnAcctLvl + "]";
	}

}
