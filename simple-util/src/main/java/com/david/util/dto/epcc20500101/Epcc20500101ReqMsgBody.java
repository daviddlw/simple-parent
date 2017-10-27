package com.david.util.dto.epcc20500101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.BasicResfdInf;
import com.david.util.dto.BasicTrxInf;

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
	private BasicResfdInf resfdInf;

	@XmlElement(name="TrxInf")
	private BasicTrxInf trxInf;

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

	public BasicResfdInf getResfdInf() {
		return resfdInf;
	}

	public void setResfdInf(BasicResfdInf resfdInf) {
		this.resfdInf = resfdInf;
	}

	public BasicTrxInf getTrxInf() {
		return trxInf;
	}

	public void setTrxInf(BasicTrxInf trxInf) {
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
