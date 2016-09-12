package cn.m1c.frame.mongodb;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import cn.m1c.frame.utils.StringUtil;

/**
 * 2016年7月27日  用于mongodb查询
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public abstract class AbstractConditions implements Conditions {

	private static final long serialVersionUID = 1L;

	static{
		ORDER_TYPE.put(false, "倒排序");
		ORDER_TYPE.put(true, "正排序");
	}


	///////////////////////Order by////////////////
    protected String orderField;

    protected boolean isAsc = false;

    private OrderBean orderBean;

    protected boolean conditioned = false;



    ///////////////////////////////////////////////////Building Query////////////////////////////////////////////////////////
    /* (non-Javadoc)
     * @see org.howsun.dao.Seeker#buildQuery()
     */
    @Override
    public Query buildQuery(){
    	Criteria criteria = buildCriteria();
    	return criteria == null ? new Query() : Query.query(criteria);
    }

	@Override
	public Criteria buildCriteria(){
		return null;
	}


	///////////////////////////////////////////////////Building Order////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.howsun.dao.Seeker#getOrderBean()
	 */
	@Override
	public OrderBean getOrderBean() {
		if(this.orderBean == null && StringUtil.hasLengthBytrim(this.orderField)){
			this.orderBean = new OrderBean(orderField, isAsc);
		}
		return orderBean;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		setIsAsc(isAsc);
	}
	
	public void setIsAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}

	public boolean hasConditioned(){
		return this.conditioned;
	}

	public void setConditioned(boolean conditioned) {
		this.conditioned = conditioned;
	}

	public static void removeStartCharSequence(StringBuilder rql, String str){
		if(rql.indexOf(str) == 0){
			rql.delete(0, str.length());
		}
	}
	public static void removeEndCharSequence(StringBuilder rql, String str){
		int ol = rql.length();
		if(ol > 0){
			int l = str.length();
			if(rql.indexOf(str) == (ol - l)){
				rql.delete(ol - l, ol - 1);
			}
		}
	}

}
