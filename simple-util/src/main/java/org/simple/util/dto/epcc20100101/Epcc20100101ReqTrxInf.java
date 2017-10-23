package org.simple.util.dto.epcc20100101;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.simple.util.common.JaxbDateSerializer;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20100101ReqTrxInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "TrxId")
	private String trxId;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	@XmlElement(name = "TrxDtTm")
	private Date trxDtTm;

	@XmlElement(name = "TrxAmt")
	private String trxAmt;

	@XmlElement(name = "TrxCtgy")
	private String trxCtgy;

	@XmlElement(name = "BizTp")
	private String bizTp;

	@XmlElement(name = "AcctInTp")
	private String acctInTp;

	public String getTrxId() {
		return trxId;
	}

	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}

	public Date getTrxDtTm() {
		return trxDtTm;
	}

	public void setTrxDtTm(Date trxDtTm) {
		this.trxDtTm = trxDtTm;
	}

	public String getTrxAmt() {
		return trxAmt;
	}

	public void setTrxAmt(String trxAmt) {
		this.trxAmt = trxAmt;
	}

	public String getTrxCtgy() {
		return trxCtgy;
	}

	public void setTrxCtgy(String trxCtgy) {
		this.trxCtgy = trxCtgy;
	}

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
		return "Epcc20100101ReqTrxInf [trxId=" + trxId + ", trxDtTm=" + trxDtTm + ", trxAmt=" + trxAmt + ", trxCtgy=" + trxCtgy + ", bizTp=" + bizTp
				+ ", acctInTp=" + acctInTp + "]";
	}

}
