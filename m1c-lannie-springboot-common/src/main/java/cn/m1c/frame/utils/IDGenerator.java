package cn.m1c.frame.utils;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * 2016年8月9日 整数 主键 生成器
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public class IDGenerator{

	static int SERVER_ID = 1;
	static int DB_COUNT  = 9;
	public final static long ID_BEGIN_TIME = new GregorianCalendar(2015, 0, 1, 0, 0, 0).getTimeInMillis();


	/**
	 * 获取唯一主键ID
	 * 返回
	 * 异常 Exception
	 */
	public static synchronized long getUniqueID(){

		long destId = System.currentTimeMillis() - ID_BEGIN_TIME;

		destId = (destId << 8) | SERVER_ID;

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return destId;
	}

	/**
	 * 生成唯一ID,该ID的dbIndex与sourceID一至
	 * 注：最大支持库      ：512个
	 *     最大支持时间：4240-01-01
	 * 参数 sourceID，如主站web、wap、xx客户端
	 * 返回
	 * 异常 Exception
	 */
	public static synchronized long getUniqueID(long sourceId){
		int sourceIndex = getDBIndex(sourceId);
		long destId = System.currentTimeMillis() - ID_BEGIN_TIME;

		destId = (destId << 9) | sourceIndex;
		destId = (destId << 8) | SERVER_ID;

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return destId;
	}

	/**
	 * 获取ID所对应该的数据库编号
	 * 参数 ID
	 * 返回 数据库
	 */
	public static int getDBIndex(long id) {
		return (int)((id >> 8) & (DB_COUNT - 1));
	}

	public static void main(String[] args) throws Exception {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new GregorianCalendar(2015, 0, 1, 0, 0, 0).getTime()));
		for(int i = 0; i <10; i++) {
			System.out.println(getUniqueID() + "\t" + System.currentTimeMillis());
		}
	}
}
