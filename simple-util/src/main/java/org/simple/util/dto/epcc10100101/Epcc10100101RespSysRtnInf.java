package org.simple.util.dto.epcc10100101;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.simple.util.dto.RespSysRtnInf;

@XmlAccessorType(XmlAccessType.FIELD)
public class Epcc10100101RespSysRtnInf extends RespSysRtnInf implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Epcc10100101RespSysRtnInf [getSysRtnCd()=" + getSysRtnCd() + ", getSysRtnDesc()=" + getSysRtnDesc() + ", getSysRtnTm()="
				+ getSysRtnTm() + "]";
	}

}
