/*     */ package com.dstz.bpm.act.service.impl;
/*     */ 
/*     */ import com.dstz.bpm.act.cmd.GetSuperVariableCmd;
/*     */ import com.dstz.bpm.act.cmd.ProcessInstanceEndCmd;
/*     */ import com.dstz.bpm.act.service.ActInstanceService;
/*     */ import com.dstz.bpm.act.util.ActivitiUtil;
/*     */ import com.dstz.bpm.api.model.def.IBpmDefinition;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.RuntimeService;
/*     */ import org.activiti.engine.impl.ProcessEngineImpl;
/*     */ import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
/*     */ import org.activiti.engine.impl.cmd.GetExecutionVariableCmd;
/*     */ import org.activiti.engine.impl.identity.Authentication;
/*     */ import org.activiti.engine.impl.interceptor.CommandExecutor;
/*     */ import org.activiti.engine.runtime.ProcessInstance;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service
/*     */ public class ActInstanceServiceImpl implements ActInstanceService
/*     */ {
/*  30 */   private static Logger log = LoggerFactory.getLogger(ActInstanceServiceImpl.class);
/*     */   
/*     */ 
/*     */   @Resource
/*     */   RuntimeService runtimeService;
/*     */   
/*     */ 
/*     */   @Resource
/*     */   org.activiti.engine.ProcessEngine processEngine;
/*     */   
/*     */   @Resource
/*     */   RepositoryService repositoryService;
/*     */   
/*     */   @Resource
/*     */   BpmProcessDefService h;
/*     */   
/*     */ 
/*     */   public String startProcessInstance(String actDefId, String businessKey, Map<String, Object> variables, String[] aryDestination)
/*     */   {
/*  49 */     String defId = this.h.getDefinitionByActDefId(actDefId).getId();
/*  50 */     BpmNodeDef bpmNodeDef = this.h.getStartEvent(defId);
/*  51 */     String nodeId = bpmNodeDef.getNodeId();
/*     */     
/*     */ 
/*  54 */     Map<String, Object> activityMap = ActivitiUtil.a(actDefId, nodeId, aryDestination);
/*     */     
/*  56 */     String actInstId = "";
/*     */     try {
/*  58 */       actInstId = startProcessInstance(actDefId, businessKey, variables);
/*     */     } catch (Exception ex) {
/*  60 */       throw new RuntimeException(ex);
/*     */     }
/*     */     finally {
/*  63 */       ActivitiUtil.a(activityMap);
/*     */     }
/*  65 */     return actInstId;
/*     */   }
/*     */   
/*     */ 
/*     */   public String startProcessInstance(String actDefId, String businessKey, Map<String, Object> variables)
/*     */   {
/*     */     try
/*     */     {
/*  73 */       IUser user = ContextUtil.getCurrentUser();
/*  74 */       Authentication.setAuthenticatedUserId(user.getUserId());
/*     */       
/*  76 */       ProcessInstance instance = this.runtimeService.startProcessInstanceById(actDefId, businessKey, variables);
/*  77 */       return instance.getId();
/*     */     } catch (Exception ex) {
/*  79 */       throw new RuntimeException(ex);
/*     */     } finally {
/*  81 */       Authentication.setAuthenticatedUserId(null);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Map<String, Object> getVariables(String processInstanceId)
/*     */   {
/*  88 */     return this.runtimeService.getVariables(processInstanceId);
/*     */   }
/*     */   
/*     */   public void setVariable(String executionId, String variableName, Object value)
/*     */   {
/*  93 */     this.runtimeService.setVariable(executionId, variableName, value);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setVariableLocal(String executionId, String variableName, Object value)
/*     */   {
/*  99 */     this.runtimeService.setVariableLocal(executionId, variableName, value);
/*     */   }
/*     */   
/*     */   public void setVariables(String executionId, Map<String, ? extends Object> variables)
/*     */   {
/* 104 */     this.runtimeService.setVariables(executionId, variables);
/*     */   }
/*     */   
/*     */   public void setVariablesLocal(String executionId, Map<String, ? extends Object> variables)
/*     */   {
/* 109 */     this.runtimeService.setVariablesLocal(executionId, variables);
/*     */   }
/*     */   
/*     */   public void removeVariable(String executionId, String variableName)
/*     */   {
/* 114 */     this.runtimeService.removeVariable(executionId, variableName);
/*     */   }
/*     */   
/*     */   public void removeVariableLocal(String executionId, String variableName)
/*     */   {
/* 119 */     this.runtimeService.removeVariableLocal(executionId, variableName);
/*     */   }
/*     */   
/*     */ 
/*     */   public void removeVariables(String executionId, Collection<String> variableNames)
/*     */   {
/* 125 */     this.runtimeService.removeVariables(executionId, variableNames);
/*     */   }
/*     */   
/*     */   public void removeVariablesLocal(String executionId, Collection<String> variableNames)
/*     */   {
/* 130 */     this.runtimeService.removeVariablesLocal(executionId, variableNames);
/*     */   }
/*     */   
/*     */   public boolean hasVariableLocal(String executionId, String variableName)
/*     */   {
/* 135 */     return this.runtimeService.hasVariableLocal(executionId, variableName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getVariableLocal(String executionId, String variableName)
/*     */   {
/* 148 */     CommandExecutor executor = ActivitiUtil.getCommandExecutor();
/* 149 */     GetExecutionVariableCmd cmd = new GetExecutionVariableCmd(executionId, variableName, true);
/* 150 */     return executor.execute(cmd);
/*     */   }
/*     */   
/*     */   public boolean hasVariable(String executionId, String variableName)
/*     */   {
/* 155 */     return this.runtimeService.hasVariable(executionId, variableName);
/*     */   }
/*     */   
/*     */   public Object getVariable(String executionId, String variableName)
/*     */   {
/* 160 */     return this.runtimeService.getVariable(executionId, variableName);
/*     */   }
/*     */   
/*     */   public Map<String, Object> getVariablesLocal(String executionId, Collection<String> variableNames)
/*     */   {
/* 165 */     return this.runtimeService.getVariablesLocal(executionId);
/*     */   }
/*     */   
/*     */   public Map<String, Object> getVariablesLocal(String executionId)
/*     */   {
/* 170 */     return this.runtimeService.getVariablesLocal(executionId);
/*     */   }
/*     */   
/*     */   public Map<String, Object> getVariables(String executionId, Collection<String> variableNames)
/*     */   {
/* 175 */     return this.runtimeService.getVariables(executionId, variableNames);
/*     */   }
/*     */   
/*     */   public void endProcessInstance(String bpmnInstanceId)
/*     */   {
/* 180 */     ProcessEngineImpl engine = (ProcessEngineImpl)this.processEngine;
/* 181 */     CommandExecutor cmdExecutor = engine.getProcessEngineConfiguration().getCommandExecutor();
/* 182 */     cmdExecutor.execute(new ProcessInstanceEndCmd(bpmnInstanceId));
/*     */   }
/*     */   
/*     */   public void activateProcessInstanceById(String bpmnInstanceId)
/*     */   {
/* 187 */     this.runtimeService.activateProcessInstanceById(bpmnInstanceId);
/*     */   }
/*     */   
/*     */   public void suspendProcessInstanceById(String bpmnInstanceId)
/*     */   {
/* 192 */     this.runtimeService.suspendProcessInstanceById(bpmnInstanceId);
/*     */   }
/*     */   
/*     */ 
/*     */   public void deleteProcessInstance(String bpmnInstId, String reason)
/*     */   {
/* 198 */     this.runtimeService.deleteProcessInstance(bpmnInstId, reason);
/*     */   }
/*     */   
/*     */   public Object getSuperVariable(String bpmnId, String varName)
/*     */   {
/* 203 */     CommandExecutor executor = ActivitiUtil.getCommandExecutor();
/* 204 */     GetSuperVariableCmd cmd = new GetSuperVariableCmd(bpmnId, varName);
/* 205 */     return executor.execute(cmd);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-act\1.1.5\wf-act-1.1.5-pg.jar!\com\dstz\bpm\act\service\impl\ActInstanceServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */