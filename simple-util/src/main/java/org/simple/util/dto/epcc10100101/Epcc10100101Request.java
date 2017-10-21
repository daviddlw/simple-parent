package org.simple.util.dto.epcc10100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.simple.util.dto.MsgHeader;

@XmlRootElement(name = "root", namespace = "namespace_string")
@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10100101Request implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "MsgHeader")
	private MsgHeader msgHeader;

	@XmlElement(name = "MsgBody")
	private Epcc10100101ReqMsgBody msgBody;

	public MsgHeader getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(MsgHeader msgHeader) {
		this.msgHeader = msgHeader;
	}

	public Epcc10100101ReqMsgBody getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(Epcc10100101ReqMsgBody msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return "Epcc10100101Request [msgHeader=" + msgHeader + ", msgBody=" + msgBody + "]";
	}

}
