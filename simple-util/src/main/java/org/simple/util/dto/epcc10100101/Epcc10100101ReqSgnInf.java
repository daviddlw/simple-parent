package org.simple.util.dto.epcc10100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10100101ReqSgnInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnAcctIssrId")
	private String sgnAcctIssrId;

	@XmlElement(name = "SgnAcctTp")
	private String sgnAcctTp;

	@XmlElement(name = "SgnAcctId")
	private String sgnAcctId;

	@XmlElement(name = "SgnAcctNm")
	private String sgnAcctNm;

	@XmlElement(name = "IDTp")
	private String idTp;

	@XmlElement(name = "IDNo")
	private String idNo;

	@XmlElement(name = "MobNo")
	private String mobNo;

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

	public String getIdTp() {
		return idTp;
	}

	public void setIdTp(String idTp) {
		this.idTp = idTp;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	@Override
	public String toString() {
		return "Epcc10100101ReqSgnInf [sgnAcctIssrId=" + sgnAcctIssrId + ", sgnAcctTp=" + sgnAcctTp + ", sgnAcctId=" + sgnAcctId + ", sgnAcctNm=" + sgnAcctNm
				+ ", idTp=" + idTp + ", idNo=" + idNo + ", mobNo=" + mobNo + "]";
	}

}
