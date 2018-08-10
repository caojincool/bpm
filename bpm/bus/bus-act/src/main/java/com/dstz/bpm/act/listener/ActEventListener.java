package com.dstz.bpm.act.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;

public abstract interface ActEventListener
{
  public abstract void notify(ActivitiEvent paramActivitiEvent);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-act\1.1.5\wf-act-1.1.5-pg.jar!\com\dstz\bpm\act\listener\ActEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */