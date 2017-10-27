package com.david.util.dto.epcc10400101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.BasicBizInf;
import com.david.util.dto.BasicInstgInf;
import com.david.util.dto.BasicSgnInf;
import com.david.util.dto.BasicSysRtnInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10400101ReqMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name="SgnInf")
	private BasicSgnInf sgnInf;

	@XmlElement(name="InstgInf")
	private BasicInstgInf instgInf;

	@XmlElement(name="TrxId")
	private String trxId;

	@XmlElement(name="SysRtnInf")
	private BasicSysRtnInf sysRtnInf;

	@XmlElement(name="BizInf")
	private BasicBizInf bizInf;

	public BasicSgnInf getSgnInf() {
		return sgnInf;
	}

	public void setSgnInf(BasicSgnInf sgnInf) {
		this.sgnInf = sgnInf;
	}

	public BasicInstgInf getInstgInf() {
		return instgInf;
	}

	public void setInstgInf(BasicInstgInf instgInf) {
		this.instgInf = instgInf;
	}

	public String getTrxId() {
		return trxId;
	}

	public void setTrxId(String trxId) {
		this.trxId = trxId;
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
		return "Epcc40100101ReqMsgBody [sgnInf=" + sgnInf + ", instgInf=" + instgInf + ", trxId=" + trxId + ", sysRtnInf=" + sysRtnInf + ", bizInf=" + bizInf
				+ "]";
	}

}
