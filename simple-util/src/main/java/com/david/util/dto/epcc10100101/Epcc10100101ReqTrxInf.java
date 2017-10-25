package com.david.util.dto.epcc10100101;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.david.util.common.JaxbDateSerializer;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10100101ReqTrxInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "TrxCtgy")
	private String trxCtgy;

	@XmlElement(name = "TrxId")
	private String trxId;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	@XmlElement(name = "TrxDtTm")
	private Date trxDtTm;

	@XmlElement(name = "AuthMsg")
	private String authMsg;

	public String getTrxCtgy() {
		return trxCtgy;
	}

	public void setTrxCtgy(String trxCtgy) {
		this.trxCtgy = trxCtgy;
	}

	public String getTrxId() {
		return trxId;
	}

	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}

	public Date getTrxDtTm() {
		return trxDtTm;
	}

	public void setTrxDtTm(Date trxDtTm) {
		this.trxDtTm = trxDtTm;
	}

	public String getAuthMsg() {
		return authMsg;
	}

	public void setAuthMsg(String authMsg) {
		this.authMsg = authMsg;
	}

	@Override
	public String toString() {
		return "Epcc10100101ReqTrxInf [trxCtgy=" + trxCtgy + ", trxId=" + trxId + ", trxDtTm=" + trxDtTm + ", authMsg=" + authMsg + "]";
	}

}
