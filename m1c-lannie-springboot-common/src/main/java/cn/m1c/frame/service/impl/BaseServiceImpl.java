package cn.m1c.frame.service.impl;

import java.util.Date;
import java.util.List;

import cn.m1c.frame.model.BaseModel;
import cn.m1c.frame.service.BaseService;
import cn.m1c.frame.utils.UUIDGenerator;
/**
 * 2016年7月27日  BaseService
 * @author  phil(s@m1c.cn,m1c softCo.,ltd)
 * @version lannie
 */
public abstract class BaseServiceImpl implements BaseService{
    public int insert(BaseModel model) {
    	setDefaultData(model);
        return getDao().insert(model);
    }
	// 增
    public int insertSelective(BaseModel model) {
    	setDefaultData(model);
        return getDao().insertSelective(model);
    }

    // 根据Id删除
    public int deleteByPrimaryKey(String id) {
        return getDao().deleteByPrimaryKey(id);
    }

    // 根据传入对象ID修改
    public int updateByPrimaryKey(BaseModel model) {
    	if(model.getUpdated()==null){
    		model.setUpdated(new Date());
    	}
        return getDao().updateByPrimaryKey(model);
    }
    
    // 根据传入对象ID,选择性修改
    public int updateByPrimaryKeySelective(BaseModel model) {
    	if(model.getUpdated()==null){
    		model.setUpdated(new Date());
    	}
        return getDao().updateByPrimaryKeySelective(model);
    }

    // 根据Id查询
    public BaseModel selectByPrimaryKey(String id) {
    	BaseModel cargoCatalog = getDao().selectByPrimaryKey(id);
        return cargoCatalog;
    }
    
  //根据对象查询列表
    public List<BaseModel> queryByModel(BaseModel model){
    	return getDao().queryByModel(model);
    }
    
    private void setDefaultData(BaseModel model){
    	if(model.getId()==null){
    		model.setId(UUIDGenerator.getUUID());
    	}
    	if(model.getCreated()==null){
    		model.setCreated(new Date());
    	}
    	if(model.getDeleted()==null){
    		model.setDeleted(false);
    	}
    }
}
