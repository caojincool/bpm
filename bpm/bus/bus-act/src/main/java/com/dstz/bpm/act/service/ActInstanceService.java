package com.dstz.bpm.act.service;

import java.util.Collection;
import java.util.Map;

public abstract interface ActInstanceService
{
  public abstract String startProcessInstance(String paramString1, String paramString2, Map<String, Object> paramMap);
  
  public abstract String startProcessInstance(String paramString1, String paramString2, Map<String, Object> paramMap, String... paramVarArgs);
  
  public abstract Map<String, Object> getVariables(String paramString);
  
  public abstract void setVariable(String paramString1, String paramString2, Object paramObject);
  
  public abstract void setVariableLocal(String paramString1, String paramString2, Object paramObject);
  
  public abstract void setVariables(String paramString, Map<String, ? extends Object> paramMap);
  
  public abstract void setVariablesLocal(String paramString, Map<String, ? extends Object> paramMap);
  
  public abstract void removeVariable(String paramString1, String paramString2);
  
  public abstract void removeVariableLocal(String paramString1, String paramString2);
  
  public abstract void removeVariables(String paramString, Collection<String> paramCollection);
  
  public abstract void removeVariablesLocal(String paramString, Collection<String> paramCollection);
  
  public abstract boolean hasVariableLocal(String paramString1, String paramString2);
  
  public abstract Object getVariableLocal(String paramString1, String paramString2);
  
  public abstract boolean hasVariable(String paramString1, String paramString2);
  
  public abstract Object getVariable(String paramString1, String paramString2);
  
  public abstract Map<String, Object> getVariablesLocal(String paramString, Collection<String> paramCollection);
  
  public abstract Map<String, Object> getVariablesLocal(String paramString);
  
  public abstract Map<String, Object> getVariables(String paramString, Collection<String> paramCollection);
  
  public abstract void endProcessInstance(String paramString);
  
  public abstract void activateProcessInstanceById(String paramString);
  
  public abstract void suspendProcessInstanceById(String paramString);
  
  public abstract void deleteProcessInstance(String paramString1, String paramString2);
  
  public abstract Object getSuperVariable(String paramString1, String paramString2);
}


/* Location:              E:\repo\com\dstz\agilebpm\wf-act\1.1.5\wf-act-1.1.5-pg.jar!\com\dstz\bpm\act\service\ActInstanceService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */