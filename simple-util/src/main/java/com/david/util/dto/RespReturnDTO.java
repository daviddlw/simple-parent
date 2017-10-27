package com.david.util.dto;

import java.io.Serializable;

/**
 * 单元测试返回值实体
 * 
 * @author Administrator
 *
 */
public class RespReturnDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private BasicSysRtnInf sysRtnInf;

	private BasicBizInf bizInf;

	public BasicSysRtnInf getSysRtnInf() {
		return sysRtnInf;
	}

	public void setSysRtnInf(BasicSysRtnInf sysRtnInf) {
		this.sysRtnInf = sysRtnInf;
	}

	public BasicBizInf getBizInf() {
		return bizInf;
	}

	public void setBizInf(BasicBizInf bizInf) {
		this.bizInf = bizInf;
	}

	@Override
	public String toString() {
		return "RespReturnDTO [sysRtnInf=" + sysRtnInf + ", bizInf=" + bizInf + "]";
	}

}
