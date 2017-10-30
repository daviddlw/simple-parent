package com.david.util.dto.epcc20800101;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.BasicPyeeInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20800101ReqPyeeInf extends BasicPyeeInf {

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
		return "Epcc2800101ReqPyeeInf [sgnNo=" + sgnNo + ", getPyeeAcctIssrId()=" + getPyeeAcctIssrId() + ", getPyeeAcctTp()=" + getPyeeAcctTp() + "]";
	}

}
