package com.david.util.debug;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * 
* @Title: CertUtil.java  
* @Package com.allscore.finance.epcc.quick.util  
* @Description: TODO(获取证书文件信息)  
* @author Zhym    
* @date 2017年9月14日 上午10:43:43
 */
public class CertUtil {
	private static final Logger logger = Logger.getLogger(CertUtil.class);
	
	/**
	 * 
	* @Description: TODO(从X509格式证书文件中获取证书信息)  
	* @param certPath
	* @return 
	* @throws  
	* @author Zhym    
	* @date 2017年9月14日 上午11:11:02
	 */
	public static CertInfo getX509CertInfo(String certPath) {
		CertInfo certInfo = new CertInfo();

		try {
			CertificateFactory certificateFactory = CertificateFactory
					.getInstance("X.509");
			FileInputStream fileInputStream = new FileInputStream(
					certPath);
			X509Certificate x509Certificate = null;
			try {
				x509Certificate = (X509Certificate) certificateFactory
						.generateCertificate(fileInputStream);
			} catch (Exception e) {
				fileInputStream.close();
				logger.error(e);
			}
			
			
			certInfo.setCertNo(x509Certificate.getSerialNumber());
			certInfo.setCertNoHex(x509Certificate.getSerialNumber().toString(16));
			certInfo.setKey(Base64.encodeBase64String(x509Certificate.getPublicKey().getEncoded()));
			certInfo.setNotBefore(x509Certificate.getNotAfter());
			certInfo.setSigAlgName(x509Certificate.getSigAlgName());
			certInfo.setPublicKey(x509Certificate.getPublicKey());
			
//			System.out.println("x509Certificate_SerialNumber_序列号___:"+x509Certificate.getSerialNumber().toString(16));  
//	        System.out.println("x509Certificate_getIssuerDN_发布方标识名___:"+x509Certificate.getIssuerDN());   
//	        System.out.println("x509Certificate_getSubjectDN_主体标识___:"+x509Certificate.getSubjectDN());  
//	        System.out.println("x509Certificate_getSigAlgOID_证书算法OID字符串___:"+x509Certificate.getSigAlgOID());  
//	        System.out.println("x509Certificate_getNotBefore_证书有效期___:"+x509Certificate.getNotAfter());  
//	        System.out.println("x509Certificate_getSigAlgName_签名算法___:"+x509Certificate.getSigAlgName());  
//	        System.out.println("x509Certificate_getVersion_版本号___:"+x509Certificate.getVersion());  
//	        System.out.println("x509Certificate_getPublicKey_公钥___:"+x509Certificate.getPublicKey());
//	        System.out.println("x509Certificate_getPublicKey_公钥_STR:" + Base64.encodeBase64String(x509Certificate.getPublicKey().getEncoded()));
			
			
		} catch (CertificateException e) {
			logger.error(e);
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return certInfo;
	}
	
	
	public static void main(String[] args) {
		String certPath = "C:\\Users\\Zhym\\Desktop\\test\\wanglian-rsa.cer";
		
		CertInfo certInfo = getX509CertInfo(certPath);
		System.out.println(certInfo.toString());
	}
}
