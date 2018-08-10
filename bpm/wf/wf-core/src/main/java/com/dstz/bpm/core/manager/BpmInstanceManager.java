package com.dstz.bpm.core.manager;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.model.def.IBpmDefinition;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskApprove;
import java.util.List;

public abstract interface BpmInstanceManager
  extends Manager<String, BpmInstance>
{
  public abstract boolean isSuspendByInstId(String paramString);
  
  public abstract List<BpmInstance> getApplyList(String paramString, QueryFilter paramQueryFilter);
  
  public abstract List<BpmTaskApprove> getApproveHistoryList(String paramString, QueryFilter paramQueryFilter);
  
  public abstract BpmInstance getTopInstance(BpmInstance paramBpmInstance);
  
  public abstract BpmInstance genInstanceByDefinition(IBpmDefinition paramIBpmDefinition);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\BpmInstanceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */