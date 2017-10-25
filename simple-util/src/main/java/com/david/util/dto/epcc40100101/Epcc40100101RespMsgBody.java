package com.david.util.dto.epcc40100101;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.david.util.dto.MsgBody;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "ctrlNbInfList", "sysRtnInf" })
public class Epcc40100101RespMsgBody extends MsgBody {

	private static final long serialVersionUID = 1L;

	@XmlElementWrapper(name = "CtrlNbInf")
	@XmlElement(name = "CtrlNbInfLst")
	private List<Epcc40100101RespCtrlNbInfLst> ctrlNbInfList;

	@XmlElement(name = "SysRtnInf")
	private Epcc40100101RespSysRtnInf sysRtnInf;

	public List<Epcc40100101RespCtrlNbInfLst> getCtrlNbInfList() {
		return ctrlNbInfList;
	}

	public void setCtrlNbInfList(List<Epcc40100101RespCtrlNbInfLst> ctrlNbInfList) {
		this.ctrlNbInfList = ctrlNbInfList;
	}

	public Epcc40100101RespSysRtnInf getSysRtnInf() {
		return sysRtnInf;
	}

	public void setSysRtnInf(Epcc40100101RespSysRtnInf sysRtnInf) {
		this.sysRtnInf = sysRtnInf;
	}

	@Override
	public String toString() {
		return "Epcc40100101MsgBody [ctrlNbInfList=" + ctrlNbInfList + ", sysRtnInf=" + sysRtnInf + ", getInstgId()=" + getInstgId() + "]";
	}

}
