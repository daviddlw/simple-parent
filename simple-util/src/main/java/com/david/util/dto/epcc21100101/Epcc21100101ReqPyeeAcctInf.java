package com.david.util.dto.epcc21100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc21100101ReqPyeeAcctInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "PyeeAcctTp")
	private String pyeeAcctTp;

	@XmlElement(name = "PyeeBkId")
	private String pyeeBkId;

	@XmlElement(name = "PyeeSgnNo")
	private String pyeeSgnNo;

	@XmlElement(name = "PyeeBkNo")
	private String pyeeBkNo;

	@XmlElement(name = "PyeeBkNm")
	private String pyeeBkNm;

	public String getPyeeAcctTp() {
		return pyeeAcctTp;
	}

	public void setPyeeAcctTp(String pyeeAcctTp) {
		this.pyeeAcctTp = pyeeAcctTp;
	}

	public String getPyeeBkId() {
		return pyeeBkId;
	}

	public void setPyeeBkId(String pyeeBkId) {
		this.pyeeBkId = pyeeBkId;
	}

	public String getPyeeSgnNo() {
		return pyeeSgnNo;
	}

	public void setPyeeSgnNo(String pyeeSgnNo) {
		this.pyeeSgnNo = pyeeSgnNo;
	}

	public String getPyeeBkNo() {
		return pyeeBkNo;
	}

	public void setPyeeBkNo(String pyeeBkNo) {
		this.pyeeBkNo = pyeeBkNo;
	}

	public String getPyeeBkNm() {
		return pyeeBkNm;
	}

	public void setPyeeBkNm(String pyeeBkNm) {
		this.pyeeBkNm = pyeeBkNm;
	}

	@Override
	public String toString() {
		return "Epcc21100101ReqPyeeAcctInf [pyeeAcctTp=" + pyeeAcctTp + ", pyeeBkId=" + pyeeBkId + ", pyeeSgnNo=" + pyeeSgnNo + ", pyeeBkNo=" + pyeeBkNo
				+ ", pyeeBkNm=" + pyeeBkNm + "]";
	}

}
