package com.dstz.bpm.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bpm.core.model.BpmBusLink;
import java.util.List;

public abstract interface BpmBusLinkManager
  extends Manager<String, BpmBusLink>
{
  public abstract List<BpmBusLink> getByInstanceId(String paramString);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\core\manager\BpmBusLinkManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */