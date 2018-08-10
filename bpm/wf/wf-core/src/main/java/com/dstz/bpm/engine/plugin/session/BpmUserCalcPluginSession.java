package com.dstz.bpm.engine.plugin.session;

import com.dstz.bpm.api.model.task.IBpmTask;

public abstract interface BpmUserCalcPluginSession
  extends BpmPluginSession
{
  public abstract Boolean isPreVrewModel();
  
  public abstract IBpmTask getBpmTask();
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\session\BpmUserCalcPluginSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */