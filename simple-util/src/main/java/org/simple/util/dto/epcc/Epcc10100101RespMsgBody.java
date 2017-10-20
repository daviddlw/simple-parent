package org.simple.util.dto.epcc;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10100101RespMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SysRtnInf")
	private Epcc10100101RespSysRtnInf sysRtnInf;

	@XmlElement(name = "BizInf")
	private Epcc10100101RespBizInf bizInf;

	@XmlElement(name = "SgnInf")
	private Epcc10100101RespSgnInf sgnInf;

	@XmlElement(name = "InstgInf")
	private Epcc10100101RespInstgInf instgInf;

	@XmlElement(name = "OriTrxInf")
	private Epcc10100101RespOriTrxInf oriTrxInf;

	public Epcc10100101RespSysRtnInf getSysRtnInf() {
		return sysRtnInf;
	}

	public void setSysRtnInf(Epcc10100101RespSysRtnInf sysRtnInf) {
		this.sysRtnInf = sysRtnInf;
	}

	public Epcc10100101RespBizInf getBizInf() {
		return bizInf;
	}

	public void setBizInf(Epcc10100101RespBizInf bizInf) {
		this.bizInf = bizInf;
	}

	public Epcc10100101RespSgnInf getSgnInf() {
		return sgnInf;
	}

	public void setSgnInf(Epcc10100101RespSgnInf sgnInf) {
		this.sgnInf = sgnInf;
	}

	public Epcc10100101RespInstgInf getInstgInf() {
		return instgInf;
	}

	public void setInstgInf(Epcc10100101RespInstgInf instgInf) {
		this.instgInf = instgInf;
	}

	public Epcc10100101RespOriTrxInf getOriTrxInf() {
		return oriTrxInf;
	}

	public void setOriTrxInf(Epcc10100101RespOriTrxInf oriTrxInf) {
		this.oriTrxInf = oriTrxInf;
	}

	@Override
	public String toString() {
		return "Epcc10100101RespMsgBody [sysRtnInf=" + sysRtnInf + ", bizInf=" + bizInf + ", sgnInf=" + sgnInf + ", instgInf=" + instgInf + ", oriTrxInf="
				+ oriTrxInf + "]";
	}

}
