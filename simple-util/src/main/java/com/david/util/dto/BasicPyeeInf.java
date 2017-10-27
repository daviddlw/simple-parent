package com.david.util.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BasicPyeeInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "PyeeAcctIssrId")
	private String pyeeAcctIssrId;

	@XmlElement(name = "PyeeAcctTp")
	private String pyeeAcctTp;

	public String getPyeeAcctIssrId() {
		return pyeeAcctIssrId;
	}

	public void setPyeeAcctIssrId(String pyeeAcctIssrId) {
		this.pyeeAcctIssrId = pyeeAcctIssrId;
	}

	public String getPyeeAcctTp() {
		return pyeeAcctTp;
	}

	public void setPyeeAcctTp(String pyeeAcctTp) {
		this.pyeeAcctTp = pyeeAcctTp;
	}

	@Override
	public String toString() {
		return "ReqPyeeInf [pyeeAcctIssrId=" + pyeeAcctIssrId + ", pyeeAcctTp=" + pyeeAcctTp + "]";
	}

}
