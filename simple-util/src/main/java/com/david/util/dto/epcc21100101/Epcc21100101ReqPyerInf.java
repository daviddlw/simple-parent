package com.david.util.dto.epcc21100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.BasicPyerInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc21100101ReqPyerInf extends BasicPyerInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "PyerAcctNo")
	private String pyerAcctNo;

	@XmlElement(name = "PyerAcctNm")
	private String pyerAcctNm;

	@XmlElement(name = "PyerMrchntNo")
	private String pyerMrchntNo;

	@XmlElement(name = "PyerMrchntNm")
	private String pyerMrchntNm;

	@XmlElement(name = "PyerMrchntShrtNm")
	private String pyerMrchntShrtNm;

	public String getPyerAcctNo() {
		return pyerAcctNo;
	}

	public void setPyerAcctNo(String pyerAcctNo) {
		this.pyerAcctNo = pyerAcctNo;
	}

	public String getPyerAcctNm() {
		return pyerAcctNm;
	}

	public void setPyerAcctNm(String pyerAcctNm) {
		this.pyerAcctNm = pyerAcctNm;
	}

	public String getPyerMrchntNo() {
		return pyerMrchntNo;
	}

	public void setPyerMrchntNo(String pyerMrchntNo) {
		this.pyerMrchntNo = pyerMrchntNo;
	}

	public String getPyerMrchntNm() {
		return pyerMrchntNm;
	}

	public void setPyerMrchntNm(String pyerMrchntNm) {
		this.pyerMrchntNm = pyerMrchntNm;
	}

	public String getPyerMrchntShrtNm() {
		return pyerMrchntShrtNm;
	}

	public void setPyerMrchntShrtNm(String pyerMrchntShrtNm) {
		this.pyerMrchntShrtNm = pyerMrchntShrtNm;
	}

	@Override
	public String toString() {
		return "Epcc21100101ReqPyerInf [pyerAcctNo=" + pyerAcctNo + ", pyerAcctNm=" + pyerAcctNm + ", pyerMrchntNo=" + pyerMrchntNo
				+ ", pyerMrchntNm=" + pyerMrchntNm + ", pyerMrchntShrtNm=" + pyerMrchntShrtNm + "]";
	}

}
