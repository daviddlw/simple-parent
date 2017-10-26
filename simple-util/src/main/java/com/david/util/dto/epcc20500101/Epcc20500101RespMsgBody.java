package com.david.util.dto.epcc20500101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.RespSysRtnInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20500101RespMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SysRtnInf")
	private RespSysRtnInf sysRtnInf;

	@XmlElement(name = "BizInf")
	private Epcc20500101RespBizInf bizInf;

	public RespSysRtnInf getSysRtnInf() {
		return sysRtnInf;
	}

	public void setSysRtnInf(RespSysRtnInf sysRtnInf) {
		this.sysRtnInf = sysRtnInf;
	}

	public Epcc20500101RespBizInf getBizInf() {
		return bizInf;
	}

	public void setBizInf(Epcc20500101RespBizInf bizInf) {
		this.bizInf = bizInf;
	}

	@Override
	public String toString() {
		return "Epcc20500101RespMsgBody [sysRtnInf=" + sysRtnInf + ", bizInf=" + bizInf + "]";
	}

}
