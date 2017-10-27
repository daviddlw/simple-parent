package com.david.util.dto.epcc20100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.david.util.dto.BasicResfdInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc20100101ReqResfdInf extends BasicResfdInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Epcc20100101ReqResfdInf [getInstgAcctId()=" + getInstgAcctId() + ", getInstgAcctNm()=" + getInstgAcctNm() + ", getResfdAcctIssrId()="
				+ getResfdAcctIssrId() + "]";
	}

}
