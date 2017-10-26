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

	private RespSysRtnInf sysRtnInf;

	private RespBizInf bizInf;

	public RespSysRtnInf getSysRtnInf() {
		return sysRtnInf;
	}

	public void setSysRtnInf(RespSysRtnInf sysRtnInf) {
		this.sysRtnInf = sysRtnInf;
	}

	public RespBizInf getBizInf() {
		return bizInf;
	}

	public void setBizInf(RespBizInf bizInf) {
		this.bizInf = bizInf;
	}

	@Override
	public String toString() {
		return "RespReturnDTO [sysRtnInf=" + sysRtnInf + ", bizInf=" + bizInf + "]";
	}

}
