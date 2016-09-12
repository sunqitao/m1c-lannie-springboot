package cn.m1c.frame.utils;
/**
 * 2016年7月29日 io tools
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public class IOUtil {
	public static void close(AutoCloseable... closeable){
		for (AutoCloseable c : closeable) {
			if(c != null){
				try {c.close();} catch (Exception e) {e.printStackTrace();}
			}
		}
	}
}
