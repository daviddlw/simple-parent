package com.david.util.dto.epcc10300101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10300101RespInstgInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "InstgId")
	private String instgId;

	@XmlElement(name = "InstgAcct")
	private String instgAcct;

	public String getInstgId() {
		return instgId;
	}

	public void setInstgId(String instgId) {
		this.instgId = instgId;
	}

	public String getInstgAcct() {
		return instgAcct;
	}

	public void setInstgAcct(String instgAcct) {
		this.instgAcct = instgAcct;
	}

	@Override
	public String toString() {
		return "Epcc10300101RespInstgInf [instgId=" + instgId + ", instgAcct=" + instgAcct + "]";
	}

}
