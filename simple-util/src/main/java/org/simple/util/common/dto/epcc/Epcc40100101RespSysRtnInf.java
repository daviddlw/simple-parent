package org.simple.util.common.dto.epcc;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "sysRtnCd", "sysRtnDesc", "sysRtnTm" })
public class Epcc40100101RespSysRtnInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SysRtnCd")
	private String sysRtnCd;

	@XmlElement(name = "SysRtnDesc")
	private String sysRtnDesc;

	@XmlElement(name = "SysRtnTm")
	private Date sysRtnTm;

	public String getSysRtnCd() {
		return sysRtnCd;
	}

	public void setSysRtnCd(String sysRtnCd) {
		this.sysRtnCd = sysRtnCd;
	}

	public String getSysRtnDesc() {
		return sysRtnDesc;
	}

	public void setSysRtnDesc(String sysRtnDesc) {
		this.sysRtnDesc = sysRtnDesc;
	}

	public Date getSysRtnTm() {
		return sysRtnTm;
	}

	public void setSysRtnTm(Date sysRtnTm) {
		this.sysRtnTm = sysRtnTm;
	}

	@Override
	public String toString() {
		return "SysRtnInf [sysRtnCd=" + sysRtnCd + ", sysRtnDesc=" + sysRtnDesc + ", sysRtnTm=" + sysRtnTm + "]";
	}

}
