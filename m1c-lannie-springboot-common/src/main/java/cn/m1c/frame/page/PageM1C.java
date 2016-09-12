package cn.m1c.frame.page;

/**
 * 2016年7月27日  mybatis ,mongodb 分页查询
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public class PageM1C {

    public static final int DEFAULT_PAGE_SIZE = 10;
//每页数量
    private int pageSize;
//    当前页码
    private int pageNo;
//    总页码
    private int totalPage;
//    总数量
    private int totalCount;
  //第一条下标
  	protected int startNum;
    
  //sql条件
    private String sqlCondition;
  //排序
    private String orderBy;

    public String getSqlCondition() {
		return sqlCondition;
	}

	public void setSqlCondition(String sqlCondition) {
		this.sqlCondition = sqlCondition;
	}

	
	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public PageM1C() {
        this.pageNo = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    /**
     * 
     */
    public PageM1C(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}
