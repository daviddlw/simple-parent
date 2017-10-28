package com.david.util.dto.epcc21100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc21100101ReqPyerAcctInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "PyerBkId")
	private String pyerBkId;

	@XmlElement(name = "PyerBkNo")
	private String pyerBkNo;

	@XmlElement(name = "PyerBkNm")
	private String pyerBkNm;

	public String getPyerBkId() {
		return pyerBkId;
	}

	public void setPyerBkId(String pyerBkId) {
		this.pyerBkId = pyerBkId;
	}

	public String getPyerBkNo() {
		return pyerBkNo;
	}

	public void setPyerBkNo(String pyerBkNo) {
		this.pyerBkNo = pyerBkNo;
	}

	public String getPyerBkNm() {
		return pyerBkNm;
	}

	public void setPyerBkNm(String pyerBkNm) {
		this.pyerBkNm = pyerBkNm;
	}

	@Override
	public String toString() {
		return "Epcc2110101ReqPyerAcctInf [pyerBkId=" + pyerBkId + ", pyerBkNo=" + pyerBkNo + ", pyerBkNm=" + pyerBkNm + "]";
	}

}
