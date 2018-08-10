package com.dstz.bpm.core.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.TaskIdentityLink;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public abstract interface TaskIdentityLinkDao
  extends BaseDao<String, TaskIdentityLink>
{
  public abstract void removeByInstanceId(String paramString);
  
  public abstract void removeByTaskId(String paramString);
  
  public abstract void bulkCreate(List<TaskIdentityLink> paramList);
  
  public abstract int checkUserOperatorPermission(@Param("rights") Set<String> paramSet, @Param("taskId") String paramString1, @Param("instanceId") String paramString2);
  
  public abstract List<TaskIdentityLink> getByTaskId(String paramString);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\dao\TaskIdentityLinkDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */