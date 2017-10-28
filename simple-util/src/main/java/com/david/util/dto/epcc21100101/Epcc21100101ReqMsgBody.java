package com.david.util.dto.epcc21100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc21100101ReqMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "PyerAcctInf")
	private Epcc21100101ReqPyerAcctInf pyerAcctInf;

	@XmlElement(name = "PyerInf")
	private Epcc21100101ReqPyerInf pyerInf;

	@XmlElement(name = "PyeeAcctInf")
	private Epcc21100101ReqPyeeAcctInf pyeeAcctInf;

	@XmlElement(name = "TrxInf")
	private Epcc21100101ReqTrxInf trxInf;

	public Epcc21100101ReqPyerAcctInf getPyerAcctInf() {
		return pyerAcctInf;
	}

	public void setPyerAcctInf(Epcc21100101ReqPyerAcctInf pyerAcctInf) {
		this.pyerAcctInf = pyerAcctInf;
	}

	public Epcc21100101ReqPyerInf getPyerInf() {
		return pyerInf;
	}

	public void setPyerInf(Epcc21100101ReqPyerInf pyerInf) {
		this.pyerInf = pyerInf;
	}

	public Epcc21100101ReqPyeeAcctInf getPyeeAcctInf() {
		return pyeeAcctInf;
	}

	public void setPyeeAcctInf(Epcc21100101ReqPyeeAcctInf pyeeAcctInf) {
		this.pyeeAcctInf = pyeeAcctInf;
	}

	public Epcc21100101ReqTrxInf getTrxInf() {
		return trxInf;
	}

	public void setTrxInf(Epcc21100101ReqTrxInf trxInf) {
		this.trxInf = trxInf;
	}

	@Override
	public String toString() {
		return "Epcc21100101ReqMsgBody [pyerAcctInf=" + pyerAcctInf + ", pyerInf=" + pyerInf + ", pyeeAcctInf=" + pyeeAcctInf + ", trxInf=" + trxInf
				+ "]";
	}

}
