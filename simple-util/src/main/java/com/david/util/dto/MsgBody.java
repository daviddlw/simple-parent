package com.david.util.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "instgId" })
public class MsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "InstgId")
	private String instgId;

	public String getInstgId() {
		return instgId;
	}

	public void setInstgId(String instgId) {
		this.instgId = instgId;
	}

	@Override
	public String toString() {
		return "MsgBody [instgId=" + instgId + "]";
	}

}
