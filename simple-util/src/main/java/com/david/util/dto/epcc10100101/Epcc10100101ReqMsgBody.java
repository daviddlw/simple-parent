package com.david.util.dto.epcc10100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10100101ReqMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnInf")
	private Epcc10100101ReqSgnInf sgnInf;

	@XmlElement(name = "TrxInf")
	private Epcc10100101ReqTrxInf trxInf;

	@XmlElement(name = "InstgInf")
	private Epcc10100101ReqInstgInf instgInf;

	public Epcc10100101ReqSgnInf getSgnInf() {
		return sgnInf;
	}

	public void setSgnInf(Epcc10100101ReqSgnInf sgnInf) {
		this.sgnInf = sgnInf;
	}

	public Epcc10100101ReqTrxInf getTrxInf() {
		return trxInf;
	}

	public void setTrxInf(Epcc10100101ReqTrxInf trxInf) {
		this.trxInf = trxInf;
	}

	public Epcc10100101ReqInstgInf getInstgInf() {
		return instgInf;
	}

	public void setInstgInf(Epcc10100101ReqInstgInf instgInf) {
		this.instgInf = instgInf;
	}

	@Override
	public String toString() {
		return "Epcc10100101ReqMsgBody [sgnInf=" + sgnInf + ", trxInf=" + trxInf + ", instgInf=" + instgInf + "]";
	}

}
