package com.david.util.dto.epcc20100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20100101ReqOriTrxInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "OrdrId")
	private String orderId;

	@XmlElement(name = "OrdrDesc")
	private String orderDesc;

	@XmlElement(name = "PltfrmNm")
	private String pltfrmNm;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getPltfrmNm() {
		return pltfrmNm;
	}

	public void setPltfrmNm(String pltfrmNm) {
		this.pltfrmNm = pltfrmNm;
	}

	@Override
	public String toString() {
		return "Epcc20100101ReqOrderInf [orderId=" + orderId + ", orderDesc=" + orderDesc + ", pltfrmNm=" + pltfrmNm + "]";
	}

}
