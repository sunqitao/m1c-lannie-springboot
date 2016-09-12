package cn.m1c.frame.utils;

/**
 * 2016年8月3日 密码加密算法
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public   class PWDEncoder {

	/**
	 * 
	 * 参数 rawPass
	 * 参数 sugar
	 * 返回
	 * String
	 * 
	 * 链接 com.yifangming.person.PassportService#encodePassword(Long objectId, String rawPass, String sugar)
	 */
	public static String encodePassword(String rawPass, String sugar){
		rawPass = reverse(CodingUtil.MD5Encoding(sugar + rawPass));
		return BCrypt.hashpw(rawPass, BCrypt.gensalt(12));
	}


	/**
	 * 
	 * 参数 rawPass
	 * 参数 encPass
	 * 参数 sugar
	 * 返回
	 * boolean
	 * 
	 * 链接 com.yifangming.person.PassportService#check(Long objectId, String rawPass, String encPass)
	 */
	public static boolean check(String rawPass, String encPass, String sugar) {
		rawPass = reverse(CodingUtil.MD5Encoding(sugar + rawPass));
		return BCrypt.checkpw(rawPass, encPass);
	}

	/**
	 * 反转字符串
	 * 参数 p
	 * 返回
	 * String
	 */
	public static String reverse(String p){
		return new StringBuffer(p).reverse().toString();
	}


	public static void main(String[] args) {
	}
}
