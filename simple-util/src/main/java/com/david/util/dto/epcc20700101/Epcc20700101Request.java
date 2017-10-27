package com.david.util.dto.epcc20700101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.david.util.dto.MsgHeader;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root", namespace = "namespace_string")
public class Epcc20700101Request implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "MsgHeader")
	private MsgHeader msgHeader;

	@XmlElement(name = "MsgBody")
	private Epcc20700101ReqMsgBody msgBody;

	public MsgHeader getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(MsgHeader msgHeader) {
		this.msgHeader = msgHeader;
	}

	public Epcc20700101ReqMsgBody getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(Epcc20700101ReqMsgBody msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return "Epcc20700101Request [msgHeader=" + msgHeader + ", msgBody=" + msgBody + "]";
	}

}
