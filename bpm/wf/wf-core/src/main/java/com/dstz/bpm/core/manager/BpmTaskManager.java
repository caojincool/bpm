package com.dstz.bpm.core.manager;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.manager.Manager;
import com.dstz.bpm.core.model.BpmTask;
import java.util.List;

public abstract interface BpmTaskManager
  extends Manager<String, BpmTask>
{
  public abstract List<BpmTask> getByInstIdNodeId(String paramString1, String paramString2);
  
  public abstract List<BpmTask> getByInstId(String paramString);
  
  public abstract List<BpmTask> getTodoList(String paramString, QueryFilter paramQueryFilter);
  
  public abstract void assigneeTask(String paramString1, String paramString2, String paramString3);
  
  public abstract void unLockTask(String paramString);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\BpmTaskManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */