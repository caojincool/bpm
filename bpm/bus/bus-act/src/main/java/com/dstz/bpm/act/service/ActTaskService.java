package com.dstz.bpm.act.service;

import java.util.Collection;
import java.util.Map;
import org.activiti.engine.delegate.DelegateTask;

public abstract interface ActTaskService
{
  public abstract DelegateTask getByTaskId(String paramString);
  
  public abstract void save(DelegateTask paramDelegateTask);
  
  public abstract void completeTask(String paramString);
  
  public abstract void completeTask(String paramString, String... paramVarArgs);
  
  public abstract void completeTaskOnly(String paramString);
  
  public abstract Object getVariable(String paramString1, String paramString2);
  
  public abstract Object getVariableLocal(String paramString1, String paramString2);
  
  public abstract Map<String, Object> getVariables(String paramString);
  
  public abstract Map<String, Object> getVariables(String paramString, Collection<String> paramCollection);
  
  public abstract Map<String, Object> getVariablesLocal(String paramString);
  
  public abstract Map<String, Object> getVariablesLocal(String paramString, Collection<String> paramCollection);
  
  public abstract void setVariable(String paramString1, String paramString2, Object paramObject);
  
  public abstract void setVariableLocal(String paramString1, String paramString2, Object paramObject);
  
  public abstract void setVariables(String paramString, Map<String, ? extends Object> paramMap);
  
  public abstract void setVariablesLocal(String paramString, Map<String, ? extends Object> paramMap);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-act\1.1.5\wf-act-1.1.5-pg.jar!\com\dstz\bpm\act\service\ActTaskService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */