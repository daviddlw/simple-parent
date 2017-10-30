package com.david.util.dto.epcc20800101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.BasicPyerInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20800101ReqPyerInf extends BasicPyerInf implements Serializable {

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
		return "Epcc20800101ReqPyerInf [pyerAcctId=" + pyerAcctId + ", getPyerAcctIssrId()=" + getPyerAcctIssrId() + ", getPyerAcctTp()=" + getPyerAcctTp()
				+ "]";
	}

}
