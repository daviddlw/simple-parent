package com.david.util.dto.epcc10400101;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.david.util.dto.MsgHeader;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root", namespace = "namespace_string")
@XmlType(propOrder = { "msgHeader", "msgBody" })
public class Epcc10400101Request {

	@XmlElement(name = "MsgHeader")
	private MsgHeader msgHeader;

	@XmlElement(name = "MsgBody")
	private Epcc10400101ReqMsgBody msgBody;

	public MsgHeader getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(MsgHeader msgHeader) {
		this.msgHeader = msgHeader;
	}

	public Epcc10400101ReqMsgBody getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(Epcc10400101ReqMsgBody msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return "Epcc40100101Request [msgHeader=" + msgHeader + ", msgBody=" + msgBody + "]";
	}

}
