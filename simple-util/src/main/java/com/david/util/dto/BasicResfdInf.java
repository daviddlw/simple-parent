package com.david.util.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BasicResfdInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "InstgAcctId")
	private String instgAcctId;

	@XmlElement(name = "InstgAcctNm")
	private String instgAcctNm;

	@XmlElement(name = "ResfdAcctIssrId")
	private String resfdAcctIssrId;

	public String getInstgAcctId() {
		return instgAcctId;
	}

	public void setInstgAcctId(String instgAcctId) {
		this.instgAcctId = instgAcctId;
	}

	public String getInstgAcctNm() {
		return instgAcctNm;
	}

	public void setInstgAcctNm(String instgAcctNm) {
		this.instgAcctNm = instgAcctNm;
	}

	public String getResfdAcctIssrId() {
		return resfdAcctIssrId;
	}

	public void setResfdAcctIssrId(String resfdAcctIssrId) {
		this.resfdAcctIssrId = resfdAcctIssrId;
	}

	@Override
	public String toString() {
		return "ReqResfdInf [instgAcctId=" + instgAcctId + ", instgAcctNm=" + instgAcctNm + ", resfdAcctIssrId=" + resfdAcctIssrId + "]";
	}

}
