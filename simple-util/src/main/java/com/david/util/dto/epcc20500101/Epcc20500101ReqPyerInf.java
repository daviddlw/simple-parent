package com.david.util.dto.epcc20500101;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.ReqPyerInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20500101ReqPyerInf extends ReqPyerInf {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "PyerAcctId")
	private String pyerAcctId;

	public String getPyerAcctId() {
		return pyerAcctId;
	}

	public void setPyerAcctId(String pyerAcctId) {
		this.pyerAcctId = pyerAcctId;
	}

	@Override
	public String toString() {
		return "Epcc20500101ReqPyerInf [pyerAcctId=" + pyerAcctId + ", getPyerAcctIssrId()=" + getPyerAcctIssrId() + ", getPyerAcctTp()=" + getPyerAcctTp()
				+ "]";
	}

}
