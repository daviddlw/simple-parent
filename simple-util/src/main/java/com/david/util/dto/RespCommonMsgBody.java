package com.david.util.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RespCommonMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SystRtnInf")
	private RespSysRtnInf sysRtnInf;

	public RespSysRtnInf getSysRtnInf() {
		return sysRtnInf;
	}

	public void setSysRtnInf(RespSysRtnInf sysRtnInf) {
		this.sysRtnInf = sysRtnInf;
	}

	@Override
	public String toString() {
		return "RespCommonMsgBody [sysRtnInf=" + sysRtnInf + "]";
	}

}
