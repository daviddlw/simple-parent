package org.simple.util.dto.epcc40100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "ctrlNbF", "ctrlNbL" })
public class Epcc40100101RespCtrlNbInfLst implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "CtrlNbF")
	private String ctrlNbF;

	@XmlElement(name = "CtrlNbL")
	private String ctrlNbL;

	public String getCtrlNbF() {
		return ctrlNbF;
	}

	public void setCtrlNbF(String ctrlNbF) {
		this.ctrlNbF = ctrlNbF;
	}

	public String getCtrlNbL() {
		return ctrlNbL;
	}

	public void setCtrlNbL(String ctrlNbL) {
		this.ctrlNbL = ctrlNbL;
	}

	@Override
	public String toString() {
		return "CtrlNbInfLst [ctrlNbF=" + ctrlNbF + ", ctrlNbL=" + ctrlNbL + "]";
	}

}
