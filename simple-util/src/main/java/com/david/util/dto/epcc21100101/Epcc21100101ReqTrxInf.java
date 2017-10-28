package com.david.util.dto.epcc21100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.david.util.dto.BasicTrxInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc21100101ReqTrxInf extends BasicTrxInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "RPFlg")
	private String rpflg;

	@XmlElement(name = "BizTp")
	private String bizTp;

	@XmlElement(name = "BatchId")
	private String batchId;

	@XmlElement(name = "TrxSmmry")
	private String trxSmmry;

	@XmlElement(name = "TrxPrps")
	private String trxPrps;

	@XmlElement(name = "TrxTrmTp")
	private String trxTrmTp;

	@XmlElement(name = "TrxTrmNo")
	private String trxTrmNo;

	@XmlElement(name = "TrxDevcInf")
	private String trxDevcInf;

	public String getRpflg() {
		return rpflg;
	}

	public void setRpflg(String rpflg) {
		this.rpflg = rpflg;
	}

	public String getBizTp() {
		return bizTp;
	}

	public void setBizTp(String bizTp) {
		this.bizTp = bizTp;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getTrxSmmry() {
		return trxSmmry;
	}

	public void setTrxSmmry(String trxSmmry) {
		this.trxSmmry = trxSmmry;
	}

	public String getTrxPrps() {
		return trxPrps;
	}

	public void setTrxPrps(String trxPrps) {
		this.trxPrps = trxPrps;
	}

	public String getTrxTrmTp() {
		return trxTrmTp;
	}

	public void setTrxTrmTp(String trxTrmTp) {
		this.trxTrmTp = trxTrmTp;
	}

	public String getTrxTrmNo() {
		return trxTrmNo;
	}

	public void setTrxTrmNo(String trxTrmNo) {
		this.trxTrmNo = trxTrmNo;
	}

	public String getTrxDevcInf() {
		return trxDevcInf;
	}

	public void setTrxDevcInf(String trxDevcInf) {
		this.trxDevcInf = trxDevcInf;
	}

	@Override
	public String toString() {
		return "Epcc21100101ReqTrxInf [rpflg=" + rpflg + ", bizTp=" + bizTp + ", batchId=" + batchId + ", trxSmmry=" + trxSmmry + ", trxPrps="
				+ trxPrps + ", trxTrmTp=" + trxTrmTp + ", trxTrmNo=" + trxTrmNo + ", trxDevcInf=" + trxDevcInf + "]";
	}

}
