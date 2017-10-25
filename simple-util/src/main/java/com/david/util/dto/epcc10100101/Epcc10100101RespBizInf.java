package com.david.util.dto.epcc10100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.RespBizInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10100101RespBizInf extends RespBizInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "SgnNo")
	private String sgnNo;

	public String getSgnNo() {
		return sgnNo;
	}

	public void setSgnNo(String sgnNo) {
		this.sgnNo = sgnNo;
	}

	@Override
	public String toString() {
		return "Epcc10100101RespBizInf [sgnNo=" + sgnNo + ", getBizStsCd()=" + getBizStsCd() + ", getBizStsDesc()=" + getBizStsDesc() + "]";
	}

}
