package com.dstz.bpm.core.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.BpmTaskStack;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public abstract interface BpmTaskStackDao
  extends BaseDao<String, BpmTaskStack>
{
  public abstract BpmTaskStack getByTaskId(String paramString);
  
  public abstract void removeByInstanceId(String paramString);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\dao\BpmTaskStackDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */