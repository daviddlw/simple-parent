package com.david.util.dto.epcc20700101;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.BasicBizInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20700101ReqBizInf extends BasicBizInf {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "InstgId")
	private String instgId;

	@XmlElement(name = "TrxCtgy")
	private String trxCtgy;

	@XmlElement(name = "TrxId")
	private String trxId;

	@XmlElement(name = "TrxDtTm")
	private Date trxDtTm;

	@XmlElement(name = "DbtrBankId")
	private String dbtrBankId;

	@XmlElement(name = "TrxAmt")
	private String trxAmt;

	@XmlElement(name = "TrxStatus")
	private String trxStatus;

	@XmlElement(name = "TrxFinishTm")
	private Date trxFinishTm;

	@XmlElement(name = "BatchId")
	private String batchId;

	public String getInstgId() {
		return instgId;
	}

	public void setInstgId(String instgId) {
		this.instgId = instgId;
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

	public Date getTrxDtTm() {
		return trxDtTm;
	}

	public void setTrxDtTm(Date trxDtTm) {
		this.trxDtTm = trxDtTm;
	}

	public String getDbtrBankId() {
		return dbtrBankId;
	}

	public void setDbtrBankId(String dbtrBankId) {
		this.dbtrBankId = dbtrBankId;
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
		return "Epcc20700101ReqBizInf [instgId=" + instgId + ", trxCtgy=" + trxCtgy + ", trxId=" + trxId + ", trxDtTm=" + trxDtTm + ", dbtrBankId=" + dbtrBankId
				+ ", trxAmt=" + trxAmt + ", trxStatus=" + trxStatus + ", trxFinishTm=" + trxFinishTm + ", batchId=" + batchId + "]";
	}

}
