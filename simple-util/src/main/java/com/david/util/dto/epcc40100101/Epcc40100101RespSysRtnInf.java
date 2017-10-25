package com.david.util.dto.epcc40100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.david.util.dto.RespSysRtnInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc40100101RespSysRtnInf extends RespSysRtnInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Epcc40100101RespSysRtnInf [getSysRtnCd()=" + getSysRtnCd() + ", getSysRtnDesc()=" + getSysRtnDesc() + ", getSysRtnTm()="
				+ getSysRtnTm() + "]";
	}

}
