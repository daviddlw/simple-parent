package com.david.util.dto.epcc20500101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.BasicPyeeInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20500101ReqPyeeInf extends BasicPyeeInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnNo")
	private String sgnNo;

	public String getSgnNo() {
		return sgnNo;
	}

	public void setSgnNo(String sgnNo) {
		this.sgnNo = sgnNo;
	}

	@Override
	public String toString() {
		return "Epcc20500101ReqPyeeInf [sgnNo=" + sgnNo + ", getPyeeAcctIssrId()=" + getPyeeAcctIssrId() + ", getPyeeAcctTp()=" + getPyeeAcctTp() + "]";
	}

}
