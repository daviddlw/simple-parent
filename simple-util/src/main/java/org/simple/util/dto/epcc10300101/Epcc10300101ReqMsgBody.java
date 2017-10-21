package org.simple.util.dto.epcc10300101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10300101ReqMsgBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnInf")
	private Epcc10300101ReqSgnInf sgnInf;

	@XmlElement(name = "InstgInf")
	private Epcc10300101ReqInstgInf instgInf;

	@XmlElement(name = "RescindInf")
	private Epcc10300101ReqRescindInf rescindInf;

	public Epcc10300101ReqSgnInf getSgnInf() {
		return sgnInf;
	}

	public void setSgnInf(Epcc10300101ReqSgnInf sgnInf) {
		this.sgnInf = sgnInf;
	}

	public Epcc10300101ReqInstgInf getInstgInf() {
		return instgInf;
	}

	public void setInstgInf(Epcc10300101ReqInstgInf instgInf) {
		this.instgInf = instgInf;
	}

	public Epcc10300101ReqRescindInf getRescindInf() {
		return rescindInf;
	}

	public void setRescindInf(Epcc10300101ReqRescindInf rescindInf) {
		this.rescindInf = rescindInf;
	}

	@Override
	public String toString() {
		return "Epcc10300101ReqMsgBody [sgnInf=" + sgnInf + ", instgInf=" + instgInf + ", rescindInf=" + rescindInf + "]";
	}

}
