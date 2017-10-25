package com.david.util.dto.epcc10300101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.RespBizInf;
import com.david.util.dto.RespSysRtnInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10300101RespMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnInf")
	private Epcc10300101RespSgnInf sgnInf;

	@XmlElement(name = "InstgInf")
	private Epcc10300101RespInstgInf instgInf;

	@XmlElement(name = "TrxId")
	private String trxId;

	@XmlElement(name = "SysRtnInf")
	private RespSysRtnInf sysRtnInf;

	@XmlElement(name = "BizInf")
	private RespBizInf bizInf;

	public Epcc10300101RespSgnInf getSgnInf() {
		return sgnInf;
	}

	public void setSgnInf(Epcc10300101RespSgnInf sgnInf) {
		this.sgnInf = sgnInf;
	}

	public Epcc10300101RespInstgInf getInstgInf() {
		return instgInf;
	}

	public void setInstgInf(Epcc10300101RespInstgInf instgInf) {
		this.instgInf = instgInf;
	}

	public String getTrxId() {
		return trxId;
	}

	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}

	public RespSysRtnInf getSysRtnInf() {
		return sysRtnInf;
	}

	public void setSysRtnInf(RespSysRtnInf sysRtnInf) {
		this.sysRtnInf = sysRtnInf;
	}

	public RespBizInf getBizInf() {
		return bizInf;
	}

	public void setBizInf(RespBizInf bizInf) {
		this.bizInf = bizInf;
	}

	@Override
	public String toString() {
		return "Epcc10300101RespMsgBody [sgnInf=" + sgnInf + ", instgInf=" + instgInf + ", trxId=" + trxId + ", sysRtnInf=" + sysRtnInf + ", bizInf="
				+ bizInf + "]";
	}

}
