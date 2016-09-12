package cn.m1c.frame.service;

import java.util.List;

import cn.m1c.frame.component.DataSource;
import cn.m1c.frame.dao.IBaseDao;
import cn.m1c.frame.model.BaseModel;
/**
 * 2016年7月27日 BaseService
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public interface BaseService {
	 // 增
	@DataSource("master")
    public int insert(BaseModel model) ;
    // 增
	@DataSource("master")
    public int insertSelective(BaseModel model) ;

    // 根据Id删除
	@DataSource("master")
    public int deleteByPrimaryKey(String id) ;

    // 根据传入对象ID修改
	@DataSource("master")
    public int updateByPrimaryKey(BaseModel model) ;
    
    // 根据传入对象ID,选择性修改
	@DataSource("master")
    public int updateByPrimaryKeySelective(BaseModel model)  ;

    // 根据Id查询
	@DataSource("slave")
    public BaseModel selectByPrimaryKey(String id)  ;
    //根据对象查询列表
	@DataSource("slave")
    public List<BaseModel> queryByModel(BaseModel model);
// 子类中必须要覆盖实现
    public abstract IBaseDao getDao();
}
