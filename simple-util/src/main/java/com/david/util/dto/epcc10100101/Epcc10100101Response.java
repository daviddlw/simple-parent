package com.david.util.dto.epcc10100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.david.util.dto.MsgHeader;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root", namespace = "namespace_string")
@XmlType(propOrder = { "msgHeader", "msgBody" })
public class Epcc10100101Response implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "MsgHeader")
	private MsgHeader msgHeader;

	@XmlElement(name = "MsgBody")
	private Epcc10100101RespMsgBody msgBody;

	public MsgHeader getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(MsgHeader msgHeader) {
		this.msgHeader = msgHeader;
	}

	public Epcc10100101RespMsgBody getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(Epcc10100101RespMsgBody msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return "Epcc10100101Response [msgHeader=" + msgHeader + ", msgBody=" + msgBody + "]";
	}
	
	

}
