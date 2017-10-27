package com.david.util.dto.epcc10300101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.david.util.dto.BasicSgnInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10300101RespSgnInf extends BasicSgnInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Epcc10300101RespSgnInf [getSgnAcctIssrId()=" + getSgnAcctIssrId() + ", getSgnNo()=" + getSgnNo() + ", getSgnAcctTp()=" + getSgnAcctTp() + "]";
	}

}
