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

}
