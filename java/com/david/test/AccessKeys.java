package com.david.test;

public class AccessKeys {
	private String rsaPrivateKey;
	private String rsaPublicKey;
	private String md5Key;

	public AccessKeys() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccessKeys(String rsaPrivateKey, String rsaPublicKey, String md5Key) {
		super();
		this.rsaPrivateKey = rsaPrivateKey;
		this.rsaPublicKey = rsaPublicKey;
		this.md5Key = md5Key;
	}

	public String getRsaPrivateKey() {
		return rsaPrivateKey;
	}

	public void setRsaPrivateKey(String rsaPrivateKey) {
		this.rsaPrivateKey = rsaPrivateKey;
	}

	public String getRsaPublicKey() {
		return rsaPublicKey;
	}

	public void setRsaPublicKey(String rsaPublicKey) {
		this.rsaPublicKey = rsaPublicKey;
	}

	public String getMd5Key() {
		return md5Key;
	}

	public void setMd5Key(String md5Key) {
		this.md5Key = md5Key;
	}

}
