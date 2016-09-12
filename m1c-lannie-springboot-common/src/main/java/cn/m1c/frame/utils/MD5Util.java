package cn.m1c.frame.utils;

import java.security.MessageDigest;


/**
 * 2016年8月9日  md5加密
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public class MD5Util {

	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	private static MessageDigest md; 
	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (Exception e) {}
	};
	public static String byteArrayToHexString(byte[] b) { 
		StringBuffer resultSb = new StringBuffer();

		for (int i = 0; i < b.length; ++i) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString(); 
	}

	private static String byteToHexString(byte b){
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encoding(String origin) {
		String resultString = null;
		try{
			resultString = new String(origin);
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		}
		catch (Exception localException) {
		}
		return resultString;
	}
}
