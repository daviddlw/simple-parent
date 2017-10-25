package com.david.util.dto.epcc20100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.ReqPyerInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20100101ReqPyerInf extends ReqPyerInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnNo")
	private String sgnNo;

	@XmlElement(name = "PyerTrxTrmTp")
	private String pyerTrxTrmTp;

	@XmlElement(name = "PyerTrxTrmNo")
	private String pyerTrxTrmNo;

	public String getSgnNo() {
		return sgnNo;
	}

	public void setSgnNo(String sgnNo) {
		this.sgnNo = sgnNo;
	}

	public String getPyerTrxTrmTp() {
		return pyerTrxTrmTp;
	}

	public void setPyerTrxTrmTp(String pyerTrxTrmTp) {
		this.pyerTrxTrmTp = pyerTrxTrmTp;
	}

	public String getPyerTrxTrmNo() {
		return pyerTrxTrmNo;
	}

	public void setPyerTrxTrmNo(String pyerTrxTrmNo) {
		this.pyerTrxTrmNo = pyerTrxTrmNo;
	}

	@Override
	public String toString() {
		return "Epcc20100101ReqPyerInf [sgnNo=" + sgnNo + ", pyerTrxTrmTp=" + pyerTrxTrmTp + ", pyerTrxTrmNo=" + pyerTrxTrmNo + ", getPyerAcctIssrId()="
				+ getPyerAcctIssrId() + ", getPyerAcctTp()=" + getPyerAcctTp() + "]";
	}

}
