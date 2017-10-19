package org.simple.util.common.dto.epcc;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.simple.util.common.JaxbDateSerializer;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "sndDt", "msgTp", "issrId", "drctn", "signSN" })
public class MsgHeader implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	@XmlElement(name="SndDt")
	private Date sndDt;

	@XmlElement(name="MsgTp")
	private String msgTp;

	@XmlElement(name="IssrId")
	private String issrId;

	@XmlElement(name="Drctn")
	private String drctn;

	@XmlElement(name="SignSN")
	private String signSN;

	public Date getSndDt() {
		return sndDt;
	}

	public void setSndDt(Date sndDt) {
		this.sndDt = sndDt;
	}

	public String getMsgTp() {
		return msgTp;
	}

	public void setMsgTp(String msgTp) {
		this.msgTp = msgTp;
	}

	public String getIssrId() {
		return issrId;
	}

	public void setIssrId(String issrId) {
		this.issrId = issrId;
	}

	public String getDrctn() {
		return drctn;
	}

	public void setDrctn(String drctn) {
		this.drctn = drctn;
	}

	public String getSignSN() {
		return signSN;
	}

	public void setSignSN(String signSN) {
		this.signSN = signSN;
	}

	@Override
	public String toString() {
		return "MsgHeader [sndDt=" + sndDt + ", msgTp=" + msgTp + ", issrId=" + issrId + ", drctn=" + drctn + ", signSN=" + signSN + "]";
	}

}
