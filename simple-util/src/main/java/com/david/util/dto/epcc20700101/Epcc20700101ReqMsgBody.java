package com.david.util.dto.epcc20700101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.BasicSysRtnInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20700101ReqMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SysRtnInf")
	private BasicSysRtnInf sysRtnInf;

	@XmlElement(name = "BizInf")
	private Epcc20700101ReqBizInf bizInf;

	public BasicSysRtnInf getSysRtnInf() {
		return sysRtnInf;
	}

	public void setSysRtnInf(BasicSysRtnInf sysRtnInf) {
		this.sysRtnInf = sysRtnInf;
	}

	public Epcc20700101ReqBizInf getBizInf() {
		return bizInf;
	}

	public void setBizInf(Epcc20700101ReqBizInf bizInf) {
		this.bizInf = bizInf;
	}

	@Override
	public String toString() {
		return "Epcc20700101ReqMsgBody [sysRtnInf=" + sysRtnInf + ", bizInf=" + bizInf + "]";
	}

}
