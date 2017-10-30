package com.david.util.dto.epcc20800101;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.david.util.common.JaxbDateSerializer;
import com.david.util.dto.BasicBizInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20800101ReqBizInf extends BasicBizInf {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "TrxStatus")
	private String trxStatus;

	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	@XmlElement(name = "TrxFinishTm")
	private Date trxFinishTm;

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

	@Override
	public String toString() {
		return "Epcc20800101ReqBizInf [trxStatus=" + trxStatus + ", trxFinishTm=" + trxFinishTm + ", getBizStsCd()=" + getBizStsCd() + ", getBizStsDesc()="
				+ getBizStsDesc() + "]";
	}

}
