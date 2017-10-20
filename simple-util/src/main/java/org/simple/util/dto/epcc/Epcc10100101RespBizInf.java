package org.simple.util.dto.epcc;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10100101RespBizInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnNo")
	private String sgnNo;

	@XmlElement(name = "BizStsCd")
	private String bizStsCd;

	@XmlElement(name = "BizStsDesc")
	private String bizStsDesc;

	public String getSgnNo() {
		return sgnNo;
	}

	public void setSgnNo(String sgnNo) {
		this.sgnNo = sgnNo;
	}

	public String getBizStsCd() {
		return bizStsCd;
	}

	public void setBizStsCd(String bizStsCd) {
		this.bizStsCd = bizStsCd;
	}

	public String getBizStsDesc() {
		return bizStsDesc;
	}

	public void setBizStsDesc(String bizStsDesc) {
		this.bizStsDesc = bizStsDesc;
	}

	@Override
	public String toString() {
		return "Epcc10100101RespBizInf [sgnNo=" + sgnNo + ", bizStsCd=" + bizStsCd + ", bizStsDesc=" + bizStsDesc + "]";
	}

}
