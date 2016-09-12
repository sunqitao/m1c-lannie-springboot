package cn.m1c.frame.dao;

import java.util.List;

import cn.m1c.frame.model.BaseModel;

/**
 * 2016年7月27日  base DAO
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public interface IBaseDao {
//	
	int deleteByPrimaryKey(String id);
	
    int insert(BaseModel record);
	
    int insertSelective(BaseModel record);
    BaseModel selectByPrimaryKey(String id);
	
    int updateByPrimaryKeySelective(BaseModel record);
    int updateByPrimaryKey(BaseModel record);
    List<BaseModel> queryByModel(BaseModel model);

}
