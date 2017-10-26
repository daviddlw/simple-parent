package com.david.util.dto.epcc20100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20100101ReqMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "PyerInf")
	private Epcc20100101ReqPyerInf pyerInf;

	@XmlElement(name = "RPFlg")
	private String rpFlg;

	@XmlElement(name = "PyeeInf")
	private Epcc20100101ReqPyeeInf pyeeInf;

	@XmlElement(name = "ResfdInf")
	private Epcc20100101ReqResfdInf resfdInf;

	@XmlElement(name = "TrxInf")
	private Epcc20100101ReqTrxInf trxInf;

	@XmlElement(name = "OrdrInf")
	private Epcc20100101ReqOriTrxInf orderInf;

	@XmlElement(name = "BatchId")
	private String batchId;

	@XmlElement(name = "TrxDevInf")
	private String trxDevInf;

	public Epcc20100101ReqPyerInf getPyerInf() {
		return pyerInf;
	}

	public void setPyerInf(Epcc20100101ReqPyerInf pyerInf) {
		this.pyerInf = pyerInf;
	}

	public String getRPFlg() {
		return rpFlg;
	}

	public void setRPFlg(String rpFlg) {
		this.rpFlg = rpFlg;
	}

	public Epcc20100101ReqPyeeInf getPyeeInf() {
		return pyeeInf;
	}

	public void setPyeeInf(Epcc20100101ReqPyeeInf pyeeInf) {
		this.pyeeInf = pyeeInf;
	}

	public Epcc20100101ReqResfdInf getResfdInf() {
		return resfdInf;
	}

	public void setResfdInf(Epcc20100101ReqResfdInf resfdInf) {
		this.resfdInf = resfdInf;
	}

	public Epcc20100101ReqTrxInf getTrxInf() {
		return trxInf;
	}

	public void setTrxInf(Epcc20100101ReqTrxInf trxInf) {
		this.trxInf = trxInf;
	}

	public Epcc20100101ReqOriTrxInf getOrderInf() {
		return orderInf;
	}

	public void setOrderInf(Epcc20100101ReqOriTrxInf orderInf) {
		this.orderInf = orderInf;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getTrxDevInf() {
		return trxDevInf;
	}

	public void setTrxDevInf(String trxDevInf) {
		this.trxDevInf = trxDevInf;
	}

	@Override
	public String toString() {
		return "Epcc20100101ReqMsgBody [pyerInf=" + pyerInf + ", RPFlg=" + rpFlg + ", pyeeInf=" + pyeeInf + ", resfdInf=" + resfdInf + ", trxInf=" + trxInf
				+ ", orderInf=" + orderInf + ", batchId=" + batchId + ", trxDevInf=" + trxDevInf + "]";
	}

}
