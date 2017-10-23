package org.simple.util.dto.epcc20100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20100101ReqPyeeInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name="PyeeAcctIssrId")
	private String pyeeAcctIssrId;
	
	@XmlElement(name="PyeeAcctId")
	private String pyeeAcctId;
	
	@XmlElement(name="PyeeNm")
	private String pyeeNm;
	
	@XmlElement(name="PyeeAcctTp")
	private String pyeeAcctTp;
	
	@XmlElement(name="PyeeCtryNo")
	private String pyeeCtryNo;
	
	@XmlElement(name="PyeeAreaNo")
	private String pyeeAreaNo;
	
	@XmlElement(name="PyeeTrxTrmTp")
	private String pyeeTrxTrmTp;
	
	@XmlElement(name="PyeeTrxTrmNo")
	private String pyeeTrxTrmNo;

	public String getPyeeAcctIssrId() {
		return pyeeAcctIssrId;
	}

	public void setPyeeAcctIssrId(String pyeeAcctIssrId) {
		this.pyeeAcctIssrId = pyeeAcctIssrId;
	}

	public String getPyeeAcctId() {
		return pyeeAcctId;
	}

	public void setPyeeAcctId(String pyeeAcctId) {
		this.pyeeAcctId = pyeeAcctId;
	}

	public String getPyeeNm() {
		return pyeeNm;
	}

	public void setPyeeNm(String pyeeNm) {
		this.pyeeNm = pyeeNm;
	}

	public String getPyeeAcctTp() {
		return pyeeAcctTp;
	}

	public void setPyeeAcctTp(String pyeeAcctTp) {
		this.pyeeAcctTp = pyeeAcctTp;
	}

	public String getPyeeCtryNo() {
		return pyeeCtryNo;
	}

	public void setPyeeCtryNo(String pyeeCtryNo) {
		this.pyeeCtryNo = pyeeCtryNo;
	}

	public String getPyeeAreaNo() {
		return pyeeAreaNo;
	}

	public void setPyeeAreaNo(String pyeeAreaNo) {
		this.pyeeAreaNo = pyeeAreaNo;
	}

	public String getPyeeTrxTrmTp() {
		return pyeeTrxTrmTp;
	}

	public void setPyeeTrxTrmTp(String pyeeTrxTrmTp) {
		this.pyeeTrxTrmTp = pyeeTrxTrmTp;
	}

	public String getPyeeTrxTrmNo() {
		return pyeeTrxTrmNo;
	}

	public void setPyeeTrxTrmNo(String pyeeTrxTrmNo) {
		this.pyeeTrxTrmNo = pyeeTrxTrmNo;
	}

	@Override
	public String toString() {
		return "Epcc20100101ReqPyeeInf [pyeeAcctIssrId=" + pyeeAcctIssrId + ", pyeeAcctId=" + pyeeAcctId + ", pyeeNm=" + pyeeNm + ", pyeeAcctTp=" + pyeeAcctTp
				+ ", pyeeCtryNo=" + pyeeCtryNo + ", pyeeAreaNo=" + pyeeAreaNo + ", pyeeTrxTrmTp=" + pyeeTrxTrmTp + ", pyeeTrxTrmNo=" + pyeeTrxTrmNo + "]";
	}
	
	

}
