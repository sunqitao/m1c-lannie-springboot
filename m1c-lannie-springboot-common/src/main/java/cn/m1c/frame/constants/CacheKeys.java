package cn.m1c.frame.constants;

import java.io.Serializable;

/**
 * 2016年7月27日 cache 时所有的key 定义基类
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public abstract class CacheKeys implements Serializable{

	private static final long serialVersionUID = 7231535423107406281L;
	/**通用实体缓存*/
	public static final String SINGLE_ENTITY = "ENTITY:[%s]:%s";
	/***公用 1分钟*****/
	public static final int EXPIRE_1MINUTE = 1 * 60;
	/***公用 2分钟*****/
	public static final int EXPIRE_2MINUTE = 2 * 60;
	/***公用 5分钟*****/
	public static final int EXPIRE_5MINUTE = 5 * 60;
	/***公用 30秒*****/
	public static final int EXPIRE_30SECOND = 30;
	/***公用 30分钟*****/
	public static final int EXPIRE_30MINUTE = 30*60;
	/***公用 60分钟*****/
	public static final int EXPIRE_60MINUTE = 60*60;
	/**公用 1天*/
    public static final int EXPIRE_1DAY = 1* 24 * 3600;
	/**公用 30天*/
    public static final int EXPIRE_30DAY = 30* 24 * 3600;
		
		
	public static <T> String getSingleEntityKey(Class<? extends T> entityClass, long id){
		return String.format(SINGLE_ENTITY, entityClass.getName(), id);
	}
}
