package com.dstz.bpm.engine.plugin.session;

import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bus.api.model.IBusinessData;
import java.util.Map;
import org.activiti.engine.delegate.VariableScope;

public abstract interface BpmPluginSession
  extends Map<String, Object>
{
  public abstract IBpmInstance getBpmInstance();
  
  public abstract Map<String, IBusinessData> getBoDatas();
  
  public abstract VariableScope getVariableScope();
  
  public abstract EventType getEventType();
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\session\BpmPluginSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */