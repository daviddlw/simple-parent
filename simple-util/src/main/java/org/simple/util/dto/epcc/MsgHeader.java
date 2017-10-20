package org.simple.util.dto.epcc;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.simple.util.common.JaxbDateSerializer;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "sndDt", "msgTp", "issrId", "drctn", "signSN", "ncrptnSN", "dgtlEnvlp" })
public class MsgHeader implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	@XmlElement(name = "SndDt")
	private Date sndDt;

	@XmlElement(name = "MsgTp")
	private String msgTp;

	@XmlElement(name = "IssrId")
	private String issrId;

	@XmlElement(name = "Drctn")
	private String drctn;

	@XmlElement(name = "SignSN")
	private String signSN;

	@XmlElement(name = "NcrptnSN")
	private String ncrptnSN;

	@XmlElement(name = "DgtlEnvlp")
	private String dgtlEnvlp;

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

	public String getNcrptnSN() {
		return ncrptnSN;
	}

	public void setNcrptnSN(String ncrptnSN) {
		this.ncrptnSN = ncrptnSN;
	}

	public String getDgtlEnvlp() {
		return dgtlEnvlp;
	}

	public void setDgtlEnvlp(String dgtlEnvlp) {
		this.dgtlEnvlp = dgtlEnvlp;
	}

	@Override
	public String toString() {
		return "MsgHeader [sndDt=" + sndDt + ", msgTp=" + msgTp + ", issrId=" + issrId + ", drctn=" + drctn + ", signSN=" + signSN + ", ncrptnSN=" + ncrptnSN
				+ ", dgtlEnvlp=" + dgtlEnvlp + "]";
	}

}
