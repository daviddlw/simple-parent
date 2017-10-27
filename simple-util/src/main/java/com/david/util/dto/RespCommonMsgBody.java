package com.david.util.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RespCommonMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SystRtnInf")
	private BasicSysRtnInf sysRtnInf;

	public BasicSysRtnInf getSysRtnInf() {
		return sysRtnInf;
	}

	public void setSysRtnInf(BasicSysRtnInf sysRtnInf) {
		this.sysRtnInf = sysRtnInf;
	}

	@Override
	public String toString() {
		return "RespCommonMsgBody [sysRtnInf=" + sysRtnInf + "]";
	}

}
