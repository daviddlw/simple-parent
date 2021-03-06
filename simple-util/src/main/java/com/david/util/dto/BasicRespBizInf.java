package com.david.util.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.david.util.common.JaxbDateSerializer;

@XmlAccessorType(XmlAccessType.FIELD)
public class BasicRespBizInf extends BasicBizInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "RPFlg")
	private String rpFlg;

	@XmlElement(name = "TrxCtgy")
	private String trxCtgy;

	@XmlElement(name = "TrxId")
	private String trxId;

	@XmlElement(name = "DbtrBankId")
	private String dbtrBankId;

	@XmlElement(name = "CdtrBankId")
	private String cdtrBankId;

	@XmlElement(name = "TrxAmt")
	private String trxAmt;

	@XmlElement(name = "TrxStatus")
	private String trxStatus;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	@XmlElement(name = "TrxFinishTm")
	private Date trxFinishTm;

	@XmlElement(name = "BatchId")
	private String batchId;

	public String getRpFlg() {
		return rpFlg;
	}

	public void setRpFlg(String rpFlg) {
		this.rpFlg = rpFlg;
	}

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

	public String getDbtrBankId() {
		return dbtrBankId;
	}

	public void setDbtrBankId(String dbtrBankId) {
		this.dbtrBankId = dbtrBankId;
	}

	public String getCdtrBankId() {
		return cdtrBankId;
	}

	public void setCdtrBankId(String cdtrBankId) {
		this.cdtrBankId = cdtrBankId;
	}

	public String getTrxAmt() {
		return trxAmt;
	}

	public void setTrxAmt(String trxAmt) {
		this.trxAmt = trxAmt;
	}

	public String getTrxStatus() {
		return trxStatus;
	}

	public void setTrxStatus(String trxStatus) {
		this.trxStatus = trxStatus;
	}

	public Date getTrxFinishTm() {
		return trxFinishTm;
	}

	public void setTrxFinishTm(Date trxFinishTm) {
		this.trxFinishTm = trxFinishTm;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	@Override
	public String toString() {
		return "BasicRespBizInf [rpFlg=" + rpFlg + ", trxCtgy=" + trxCtgy + ", trxId=" + trxId + ", dbtrBankId=" + dbtrBankId + ", cdtrBankId=" + cdtrBankId
				+ ", trxAmt=" + trxAmt + ", trxStatus=" + trxStatus + ", trxFinishTm=" + trxFinishTm + ", batchId=" + batchId + "]";
	}

}
