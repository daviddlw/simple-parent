package org.simple.util.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
  
/**
 * 
* @Title: RSAEncryptUtil.java  
* @Package com.allscore.finance.epcc.quick.util  
* @Description: TODO(RSA 加解密)  
* @author Zhym    
* @date 2017年9月12日 下午5:45:49
 */
public class RSAEncryptUtil {  
    private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6',  
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
  
  
    /**
     *  
    * @Description: TODO(从文件中输入流中加载公钥)  
    * @param path
    * @return
    * @throws Exception 
    * @throws  
    * @author Zhym    
    * @date 2017年9月12日 下午4:58:52
     */
    public static String loadPublicKeyByFile(String path) throws Exception {  
        try {  
            BufferedReader br = new BufferedReader(new FileReader(path));  
            String readLine = null;  
            StringBuilder sb = new StringBuilder();  
            while ((readLine = br.readLine()) != null) {  
                sb.append(readLine);  
            }  
            br.close();  
            return sb.toString();  
        } catch (IOException e) {  
            throw new Exception("公钥数据流读取错误");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥输入流为空");  
        }  
    }  
  
    /**
     * 
    * @Description: TODO(从字符串中加载公钥)  
    * @param publicKeyStr
    * @return
    * @throws Exception 
    * @throws  
    * @author Zhym    
    * @date 2017年9月12日 下午4:59:44
     */
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)  
            throws Exception {  
        try {  
            byte[] buffer = Base64.decodeBase64(publicKeyStr);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);  
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("公钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥数据为空");  
        }  
    }  
  
    /**
     *  
    * @Description: TODO(从文件中加载私钥)  
    * @param path
    * @return
    * @throws Exception 
    * @throws  
    * @author Zhym    
    * @date 2017年9月12日 下午5:00:02
     */
    public static String loadPrivateKeyByFile(String path) throws Exception {  
        try {  
            BufferedReader br = new BufferedReader(new FileReader(path));  
            String readLine = null;  
            StringBuilder sb = new StringBuilder();  
            while ((readLine = br.readLine()) != null) {  
                sb.append(readLine);  
            }  
            br.close();  
            return sb.toString();  
        } catch (IOException e) {  
            throw new Exception("私钥数据读取错误");  
        } catch (NullPointerException e) {  
            throw new Exception("私钥输入流为空");  
        }  
    }  
  
    /**
     * 
    * @Description: TODO(从字符串加载私钥)  
    * @param privateKeyStr
    * @return
    * @throws Exception 
    * @throws  
    * @author Zhym    
    * @date 2017年9月12日 下午5:00:31
     */
    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)  
            throws Exception {  
        try {  
            byte[] buffer = Base64.decodeBase64(privateKeyStr);  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("私钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("私钥数据为空");  
        }  
    }  
  
    /**
     * 
    * @Description: TODO(公钥加密)  
    * @param publicKey
    * @param plainTextData
    * @return
    * @throws Exception 
    * @throws  
    * @author Zhym    
    * @date 2017年9月12日 下午5:01:16
     */
    public static String encrypt(RSAPublicKey publicKey, String content)  
            throws Exception {  
        if (publicKey == null) {  
            throw new Exception("加密公钥为空, 请设置");  
        }  
        byte[] plainTextData = content.getBytes();
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
            byte[] output = cipher.doFinal(plainTextData);  
            return Base64.encodeBase64String(output).replaceAll("[\\s*\t\n\r]", "");
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此加密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("加密公钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("明文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("明文数据已损坏");  
        }  
    }  
  
  
    /**
     *   
    * @Description: TODO(私钥解密)  
    * @param privateKey
    * @param cipherData
    * @return
    * @throws Exception 
    * @throws  
    * @author Zhym    
    * @date 2017年9月12日 下午5:02:00
     */
    public static String decrypt(RSAPrivateKey privateKey, String base64Countent)  
            throws Exception {  
        if (privateKey == null) {  
            throw new Exception("解密私钥为空, 请设置");  
        }  
        byte[] cipherData = Base64.decodeBase64(base64Countent);
        Cipher cipher = null;  
        try {  
            // 使用默认RSA  
            cipher = Cipher.getInstance("RSA");  
            cipher.init(Cipher.DECRYPT_MODE, privateKey);  
            byte[] output = cipher.doFinal(cipherData);  
            return new String(output);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此解密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        } catch (InvalidKeyException e) {  
            throw new Exception("解密私钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("密文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("密文数据已损坏");  
        }  
    }  
  
  
    /** 
     * 字节数据转十六进制字符串 
     *  
     * @param data 
     *            输入数据 
     * @return 十六进制内容 
     */  
    public static String byteArrayToString(byte[] data) {  
        StringBuilder stringBuilder = new StringBuilder();  
        for (int i = 0; i < data.length; i++) {  
            // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移  
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);  
            // 取出字节的低四位 作为索引得到相应的十六进制标识符  
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);  
            if (i < data.length - 1) {  
                stringBuilder.append(' ');  
            }  
        }  
        return stringBuilder.toString();  
    }  
    
    
    
    public static void main(String[] args) throws Exception {
    	String reqXml = "01|01234567890123456789012345678912";
        String priKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5zWztV4YgOzTBDq14aN6Ba+S4teKu4x6tw/POav4F4OELNbUgMlf9qbZ+ehc63qornEprlg9qcQCPIiSF5FKP7fgO2ge3CfJE6OwqdRDMQRubwouz4cDhCV82rHBac4xyu2cVeJZm1FdUwO6cATfawdRUh+n/jSJl8Y2eq537oKM93yuBtma90d25ialK0qbLvqrnTXuVVUwtUP9Vbw8kc4LAa66o2qTusjcUZCZpmKT63iTHXu1G1ygtv3FoWu4UCk1ZLXBoDsWcsI7+u6ja2P0GpMO96B35exo5sP2A9DVy7zNTOAVwCJbhH0/qjkV/O5YNsdN+63rTyT+9UZ+vAgMBAAECggEAD8kkOAX3n0Vodf6JPnGqddR02jyZxcIGGT1MuXzdmCJC/HDTsqiUUTjaCGTXFGlWi937dKiLJr/zDDYaZEXBvfi52okrnqF5s+EWL4VCqBfgAJvGwsGrYXMqY7rC0OyAQj8pwuk73gCsasb5fsebtzpAj+38M3oKMh8TqSdrhLU8DMtwdaoVGjXkRk1v6RCrrpwNgoxrMC1HmPi7EKb0kh9PBfesJByrHRdyw8GnK2sSeIITh99hFLxDlPnfgIGD1+W327KY3DEWhLeZJ58GEIXINUnEK+Gycf0YT7FXQaL2RMtpqIZkrFE1hYwQpWPG8QhY1sLQikxBLYsnwRUIAQKBgQDl+Uvp2pN4DiaeNOBJ6JcP7hwugaficrVW/HE8JWc1aO0fPLiHkyHjkcohcckpHrw88RPclrOMwaXhbUTndhyjHGjICpmYCswJ3N96LUBpku6HNxkjhsONfjsckeTczDoHem+gpDQg8kwttXMU6pLTe/tZXNpFVo9oWGSTgMwfkQKBgQDO1Gov8mwAJN1XO8Fc4LwbUAmnIMbOQd4hCpspvqaBxmGN8EP5q2K40n1sqQXjrhF4DrsEDfX/9poW4PanoUbro1RKZCpwsiL6AtiJFYTqtD6oD3Ri5fQW7sPuWtkMtP462v7oklK76CHX8L/Oy4yImJ3xsoNbiCOi83hKkgCrPwKBgCA5SVDQRr+YxAKBCDhy9c/OnnZJxpttVUmF/9vcpdSPCkLJQFYG4MXBZOBA0F4Jp3imkxDisO/1Jmgq5m/EVgjo7ymwiosKYEK1hz7YXmEsrG2X49bdiEMoP+Il8zqZBzyLBF8vAvrm2JPDDAgPQlmN0s9XumHi2h9V22nrry5xAoGBAMW0uoR2oNJKauIWFrHniSg6i9+cLy2+PM0szEFNIvDuZxY80113fHjbjFUwBJctLzmqKw6nIb25RnYUOPLn8VkR2WL6+K6dOV3vFnmDfni0LyiLDV/0HC1HszbG1K+W22X7LKu4IynHrYIzecX9QKrZ6DC/yVzXd+kt/pCZm9YVAoGABx/6NyydqTI2fKaGGvRTvNZqwIrH7EPOQpX0f2pO86PdlE7AXwmWeuLuJnfEoEGJ81jUy+3Xy4e+LQw0/yIvRePFlSbqkRA1ErMfz/7x/X79vwXXhYAXtsoJyKlbcIKoq4mi2/QIcXsBg4Nk9/eMZgdxks+QwWaCMl/pID3vJyg=";
		String enc = "rE4D42TJupDJp7b9CqvjGWE6JAacSkaUvan3Dkq0b7oOTRw3o8te0hNcrQad2nfoj/vbYsYoGKPDf+14fRr1SJrK6w1uie17PS6gd6HN/29K99YgRx5yV+U+AXNXwdiWTW0KnpYZ+COoW/e+jSq9kKs6+FtgFkxT7FJ8ZxCLTzPi1cKKauQhxW5G3bZkxN3RJWLGOdlJJ4aMsl7tCR8mgM+PVTttO5eesfQiZpxeFPr3fIQkdE8gSR45qArKx3P4NfnekjA9+2zsfxI7WZ27AxQ5HhUbZB4xGVfy69CHSs6Be26KQP/Rm9lHlQ6/b7zxciy+fdr/y2uHXMNijw7fQQ==";
        String decGet = decrypt(loadPrivateKeyByStr(priKey), enc);
        System.out.println(new String(decGet));
        
        CertInfo certInfo = CertUtil
				.getX509CertInfo("C:\\Users\\Zhym\\Desktop\\test\\wanglian-rsa.cer");
        RSAPublicKey rPubKey = (RSAPublicKey)certInfo.getPublicKey();
        String encGet = encrypt(rPubKey, reqXml);
        System.out.println(encGet);
	}
}  

