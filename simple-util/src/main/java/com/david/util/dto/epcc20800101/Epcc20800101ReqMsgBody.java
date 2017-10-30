package com.david.util.dto.epcc20800101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.BasicBizInf;
import com.david.util.dto.BasicOriTrxInf;
import com.david.util.dto.BasicResfdInf;
import com.david.util.dto.BasicSysRtnInf;
import com.david.util.dto.BasicTrxInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20800101ReqMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "PyerInf")
	private Epcc20800101ReqPyerInf pyerInf;

	@XmlElement(name = "RPFlg")
	private String rpflg;

	@XmlElement(name = "PyeeInf")
	private Epcc20800101ReqPyeeInf pyeeInf;

	@XmlElement(name = "ResfdInf")
	private BasicResfdInf resfdInf;

	@XmlElement(name = "TrxInf")
	private BasicTrxInf trxInf;

	@XmlElement(name = "OriTrxInf")
	private BasicOriTrxInf oriTrxInf;

	@XmlElement(name = "BatchId")
	private String batchId;

	@XmlElement(name = "SysRtnInf")
	private BasicSysRtnInf sysRtnInf;

	@XmlElement(name = "BizInf")
	private BasicBizInf bizInf;

	public Epcc20800101ReqPyerInf getPyerInf() {
		return pyerInf;
	}

	public void setPyerInf(Epcc20800101ReqPyerInf pyerInf) {
		this.pyerInf = pyerInf;
	}

	public String getRpflg() {
		return rpflg;
	}

	public void setRpflg(String rpflg) {
		this.rpflg = rpflg;
	}

	public Epcc20800101ReqPyeeInf getPyeeInf() {
		return pyeeInf;
	}

	public void setPyeeInf(Epcc20800101ReqPyeeInf pyeeInf) {
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

	public BasicOriTrxInf getOriTrxInf() {
		return oriTrxInf;
	}

	public void setOriTrxInf(BasicOriTrxInf oriTrxInf) {
		this.oriTrxInf = oriTrxInf;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public BasicSysRtnInf getSysRtnInf() {
		return sysRtnInf;
	}

	public void setSysRtnInf(BasicSysRtnInf sysRtnInf) {
		this.sysRtnInf = sysRtnInf;
	}

	public BasicBizInf getBizInf() {
		return bizInf;
	}

	public void setBizInf(BasicBizInf bizInf) {
		this.bizInf = bizInf;
	}

	@Override
	public String toString() {
		return "Epcc20800101ReqMsgBody [pyerInf=" + pyerInf + ", rpflg=" + rpflg + ", pyeeInf=" + pyeeInf + ", resfdInf=" + resfdInf + ", trxInf=" + trxInf
				+ ", oriTrxInf=" + oriTrxInf + ", batchId=" + batchId + ", sysRtnInf=" + sysRtnInf + ", bizInf=" + bizInf + "]";
	}

}
