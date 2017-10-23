package org.simple.util.dto.epcc20100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.simple.util.dto.RespSysRtnInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20100101RespMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SysRtnInf")
	private RespSysRtnInf sysRtnInf;

	@XmlElement(name = "BizInf")
	private Epcc20100101RespBizInf bizInf;

	public RespSysRtnInf getSysRtnInf() {
		return sysRtnInf;
	}

	public void setSysRtnInf(RespSysRtnInf sysRtnInf) {
		this.sysRtnInf = sysRtnInf;
	}

	public Epcc20100101RespBizInf getBizInf() {
		return bizInf;
	}

	public void setBizInf(Epcc20100101RespBizInf bizInf) {
		this.bizInf = bizInf;
	}

	@Override
	public String toString() {
		return "Epcc20100101RespMsgBody [sysRtnInf=" + sysRtnInf + ", bizInf=" + bizInf + "]";
	}

}
