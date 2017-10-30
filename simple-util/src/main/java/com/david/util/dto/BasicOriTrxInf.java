package com.david.util.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BasicOriTrxInf implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "OriTrxId")
	private String oriTrxId;

	@XmlElement(name = "OriDbtrBankId")
	private String oriDbtrBankId;

	@XmlElement(name = "OriTrxAmt")
	private String oriTrxAmt;
	
	public String getOriTrxId() {
		return oriTrxId;
	}

	public void setOriTrxId(String oriTrxId) {
		this.oriTrxId = oriTrxId;
	}

	public String getOriDbtrBankId() {
		return oriDbtrBankId;
	}

	public void setOriDbtrBankId(String oriDbtrBankId) {
		this.oriDbtrBankId = oriDbtrBankId;
	}

	public String getOriTrxAmt() {
		return oriTrxAmt;
	}

	public void setOriTrxAmt(String oriTrxAmt) {
		this.oriTrxAmt = oriTrxAmt;
	}
	
	@Override
	public String toString() {
		return "Epcc20500101ReqOriTrxInf [oriTrxId=" + oriTrxId + ", oriDbtrBankId=" + oriDbtrBankId + ", oriTrxAmt=" + oriTrxAmt + "]";
	}

}
