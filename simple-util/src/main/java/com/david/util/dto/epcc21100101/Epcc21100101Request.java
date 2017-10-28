package com.david.util.dto.epcc21100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.david.util.dto.MsgHeader;

@XmlType(propOrder = {"msgHeader", "msgBody"})
@XmlRootElement(name = "root", namespace = "namespace_string")
@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc21100101Request implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "MsgHeader")
	private MsgHeader msgHeader;

	@XmlElement(name = "MsgBody")
	private Epcc21100101ReqMsgBody msgBody;

	public MsgHeader getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(MsgHeader msgHeader) {
		this.msgHeader = msgHeader;
	}

	public Epcc21100101ReqMsgBody getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(Epcc21100101ReqMsgBody msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return "Epcc21100101Request [msgHeader=" + msgHeader + ", msgBody=" + msgBody + "]";
	}

}
