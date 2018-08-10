package com.dstz.bpm.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.model.BpmTaskStack;

public abstract interface BpmTaskStackManager
  extends Manager<String, BpmTaskStack>
{
  public abstract BpmTaskStack getByTaskId(String paramString);
  
  public abstract BpmTaskStack createStackByTask(IBpmTask paramIBpmTask, BpmTaskStack paramBpmTaskStack);
  
  public abstract void removeByInstanceId(String paramString);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\BpmTaskStackManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */