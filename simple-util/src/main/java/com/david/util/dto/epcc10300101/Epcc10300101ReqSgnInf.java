package com.david.util.dto.epcc10300101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.BasicSgnInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10300101ReqSgnInf extends BasicSgnInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnAcctShrtId")
	private String sgnAcctShrtId;

	public String getSgnAcctShrtId() {
		return sgnAcctShrtId;
	}

	public void setSgnAcctShrtId(String sgnAcctShrtId) {
		this.sgnAcctShrtId = sgnAcctShrtId;
	}

	@Override
	public String toString() {
		return "Epcc10300101ReqSgnInf [sgnAcctShrtId=" + sgnAcctShrtId + ", getSgnAcctIssrId()=" + getSgnAcctIssrId() + ", getSgnNo()=" + getSgnNo()
				+ ", getSgnAcctTp()=" + getSgnAcctTp() + "]";
	}

}
