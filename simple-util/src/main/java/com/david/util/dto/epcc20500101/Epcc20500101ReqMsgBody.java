package com.david.util.dto.epcc20500101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.ReqResfdInf;
import com.david.util.dto.ReqTrxInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20500101ReqMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name="PyerInf")
	private Epcc20500101ReqPyerInf pyerInf;

	@XmlElement(name="RPFlg")
	private String rpflg;

	@XmlElement(name="PyeeInf")
	private Epcc20500101ReqPyeeInf pyeeInf;

	@XmlElement(name="ResfdInf")
	private ReqResfdInf resfdInf;

	@XmlElement(name="TrxInf")
	private ReqTrxInf trxInf;

	@XmlElement(name="OriTrxInf")
	private Epcc20500101ReqOriTrxInf oriTrxInf;

	@XmlElement(name="BatchId")
	private String batchId;

	public Epcc20500101ReqPyerInf getPyerInf() {
		return pyerInf;
	}

	public void setPyerInf(Epcc20500101ReqPyerInf pyerInf) {
		this.pyerInf = pyerInf;
	}

	public String getRpflg() {
		return rpflg;
	}

	public void setRpflg(String rpflg) {
		this.rpflg = rpflg;
	}

	public Epcc20500101ReqPyeeInf getPyeeInf() {
		return pyeeInf;
	}

	public void setPyeeInf(Epcc20500101ReqPyeeInf pyeeInf) {
		this.pyeeInf = pyeeInf;
	}

	public ReqResfdInf getResfdInf() {
		return resfdInf;
	}

	public void setResfdInf(ReqResfdInf resfdInf) {
		this.resfdInf = resfdInf;
	}

	public ReqTrxInf getTrxInf() {
		return trxInf;
	}

	public void setTrxInf(ReqTrxInf trxInf) {
		this.trxInf = trxInf;
	}

	public Epcc20500101ReqOriTrxInf getOriTrxInf() {
		return oriTrxInf;
	}

	public void setOriTrxInf(Epcc20500101ReqOriTrxInf oriTrxInf) {
		this.oriTrxInf = oriTrxInf;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	@Override
	public String toString() {
		return "Epcc2050101ReqMsgBody [pyerInf=" + pyerInf + ", rpflg=" + rpflg + ", pyeeInf=" + pyeeInf + ", resfdInf=" + resfdInf + ", trxInf=" + trxInf
				+ ", oriTrxInf=" + oriTrxInf + "]";
	}

}
