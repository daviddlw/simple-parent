package com.david.util.dto.epcc20100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.ReqTrxInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20100101ReqTrxInf extends ReqTrxInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "BizTp")
	private String bizTp;

	@XmlElement(name = "AcctInTp")
	private String acctInTp;

	public String getBizTp() {
		return bizTp;
	}

	public void setBizTp(String bizTp) {
		this.bizTp = bizTp;
	}

	public String getAcctInTp() {
		return acctInTp;
	}

	public void setAcctInTp(String acctInTp) {
		this.acctInTp = acctInTp;
	}

	@Override
	public String toString() {
		return "Epcc20100101ReqTrxInf [bizTp=" + bizTp + ", acctInTp=" + acctInTp + ", getTrxCtgy()=" + getTrxCtgy() + ", getTrxId()=" + getTrxId()
				+ ", getTrxDtTm()=" + getTrxDtTm() + ", getTrxAmt()=" + getTrxAmt() + "]";
	}

}
