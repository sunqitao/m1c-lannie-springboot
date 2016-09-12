package cn.m1c.frame.mongodb;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


/**
 * 2016年7月27日  查询条件封装
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
//@FunctionalInterface
public interface Conditions extends Serializable {

	/**排序字段**/
	public static final Map<String, String> ORDER_FIELDS = new LinkedHashMap<String, String>();

	/**排序方式**/
	public static final Map<Boolean, String> ORDER_TYPE = new LinkedHashMap<Boolean, String>(2,1);

	/**
	 * 获取排序字段
	 */
	public String getOrderField();

	/**覆盖排序字段**/
	public void setOrderField(String orderField);

	/**排序方式**/
	public boolean isAsc();

	/**重新设置排序方式**/
	public void setAsc(boolean isAsc);

	/**是否有条件**/
	public boolean hasConditioned();

	/**ID**/
	public Set<? extends Serializable> getIds();
	
	///////////////////////////////////////////////////Building MongoDB Querrier////////////////////////////////////////////////////////
	/**
	 * 创建查询器
	 */
	public Query buildQuery();

	/**
	 * 创建条件
	 */
	public Criteria buildCriteria();

	///////////////////////////////////////////////////Building Order////////////////////////////////////////////////////////
	/*
	 * 添加排序字段
	 * @param addField
	 * @param isAsc
	 * @param isReplace 是否保留已有的排序字段
	 * @return
	 用getOrderBean().add(field, isAsc)代替;
	public OrderBean addOrderBean(String field, boolean isAsc, boolean isReplace);
	 */
	
	/**
	 * 获取排序对象
	 */
	public OrderBean getOrderBean();


}