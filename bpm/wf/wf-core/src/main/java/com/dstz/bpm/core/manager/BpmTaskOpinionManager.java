package com.dstz.bpm.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import java.util.List;

public abstract interface BpmTaskOpinionManager
  extends Manager<String, BpmTaskOpinion>
{
  public abstract BpmTaskOpinion getByTaskId(String paramString);
  
  public abstract void createOpinionByInstance(IBpmInstance paramIBpmInstance, String paramString, boolean paramBoolean);
  
  public abstract void createOpinionByTask(TaskActionCmd paramTaskActionCmd);
  
  public abstract List<BpmTaskOpinion> getByInstAndNode(String paramString1, String paramString2);
  
  public abstract List<BpmTaskOpinion> getByInstId(String paramString);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\BpmTaskOpinionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */