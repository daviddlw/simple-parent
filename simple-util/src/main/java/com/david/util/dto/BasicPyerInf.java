package com.david.util.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReqPyerInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "PyerAcctIssrId")
	private String pyerAcctIssrId;

	@XmlElement(name = "PyerAcctTp")
	private String pyerAcctTp;

	public String getPyerAcctIssrId() {
		return pyerAcctIssrId;
	}

	public void setPyerAcctIssrId(String pyerAcctIssrId) {
		this.pyerAcctIssrId = pyerAcctIssrId;
	}

	public String getPyerAcctTp() {
		return pyerAcctTp;
	}

	public void setPyerAcctTp(String pyerAcctTp) {
		this.pyerAcctTp = pyerAcctTp;
	}

	@Override
	public String toString() {
		return "ReqPyerInf [pyerAcctIssrId=" + pyerAcctIssrId + ", pyerAcctTp=" + pyerAcctTp + "]";
	}

}
