package com.dstz.bpm.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.model.TaskIdentityLink;
import com.dstz.sys.api.model.SysIdentity;
import java.util.List;
import java.util.Set;

public abstract interface TaskIdentityLinkManager
  extends Manager<String, TaskIdentityLink>
{
  public abstract void removeByTaskId(String paramString);
  
  public abstract void bulkCreate(List<TaskIdentityLink> paramList);
  
  public abstract void removeByInstanceId(String paramString);
  
  public abstract Boolean checkUserOperatorPermission(String paramString1, String paramString2, String paramString3);
  
  public abstract void createIdentityLink(IBpmTask paramIBpmTask, List<SysIdentity> paramList);
  
  public abstract Set<String> getUserRights(String paramString);
  
  public abstract List<TaskIdentityLink> getByTaskId(String paramString);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\TaskIdentityLinkManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */