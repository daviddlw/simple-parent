package com.david.util.debug;

import java.math.BigInteger;
import java.security.PublicKey;
import java.util.Date;

public class CertInfo {
	private BigInteger certNo; //证书编号 10进制
	private String certNoHex; //证书编号16进制
	private String key; //证书密钥串
	private PublicKey publicKey; //证书密钥实体
	private String sigAlgName; //签名算法
	private Date notBefore; //证书有效期
	public BigInteger getCertNo() {
		return certNo;
	}
	public void setCertNo(BigInteger certNo) {
		this.certNo = certNo;
	}
	public String getCertNoHex() {
		return certNoHex;
	}
	public void setCertNoHex(String certNoHex) {
		this.certNoHex = certNoHex;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSigAlgName() {
		return sigAlgName;
	}
	public void setSigAlgName(String sigAlgName) {
		this.sigAlgName = sigAlgName;
	}
	public Date getNotBefore() {
		return notBefore;
	}
	public void setNotBefore(Date notBefore) {
		this.notBefore = notBefore;
	}
	
	public PublicKey getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	
	@Override
	public String toString() {
		return "CertInfo [certNo=" + certNo + ", certNoHex=" + certNoHex
				+ ", key=" + key + ", sigAlgName=" + sigAlgName
				+ ", notBefore=" + notBefore + "]";
	}
	
	
}
