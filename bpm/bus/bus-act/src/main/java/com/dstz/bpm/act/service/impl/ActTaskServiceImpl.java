/*     */ package com.dstz.bpm.act.service.impl;
/*     */ 
/*     */ import com.dstz.bpm.act.service.ActTaskService;
/*     */ import com.dstz.bpm.act.util.ActivitiUtil;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.TaskService;
/*     */ import org.activiti.engine.delegate.DelegateTask;
/*     */ import org.activiti.engine.impl.persistence.entity.TaskEntity;
/*     */ import org.activiti.engine.task.TaskQuery;
/*     */ 
/*     */ @org.springframework.stereotype.Service
/*     */ public class ActTaskServiceImpl implements ActTaskService
/*     */ {
/*     */   @Resource
/*     */   TaskService taskService;
/*     */   @Resource
/*     */   RepositoryService repositoryService;
/*     */   
/*     */   public DelegateTask getByTaskId(String taskId)
/*     */   {
/*  24 */     TaskEntity task = (TaskEntity)((TaskQuery)this.taskService.createTaskQuery().taskId(taskId)).singleResult();
/*  25 */     return task;
/*     */   }
/*     */   
/*     */   public void save(DelegateTask delegateTask)
/*     */   {
/*  30 */     this.taskService.saveTask((TaskEntity)delegateTask);
/*     */   }
/*     */   
/*     */   public Object getVariable(String taskId, String variableName)
/*     */   {
/*  35 */     return this.taskService.getVariable(taskId, variableName);
/*     */   }
/*     */   
/*     */   public Object getVariableLocal(String taskId, String variableName)
/*     */   {
/*  40 */     return this.taskService.getVariableLocal(taskId, variableName);
/*     */   }
/*     */   
/*     */   public Map<String, Object> getVariables(String taskId)
/*     */   {
/*  45 */     return this.taskService.getVariables(taskId);
/*     */   }
/*     */   
/*     */   public Map<String, Object> getVariables(String taskId, Collection<String> variableNames)
/*     */   {
/*  50 */     return this.taskService.getVariables(taskId, variableNames);
/*     */   }
/*     */   
/*     */ 
/*     */   public Map<String, Object> getVariablesLocal(String taskId)
/*     */   {
/*  56 */     return this.taskService.getVariablesLocal(taskId);
/*     */   }
/*     */   
/*     */   public Map<String, Object> getVariablesLocal(String taskId, Collection<String> variableNames)
/*     */   {
/*  61 */     return this.taskService.getVariablesLocal(taskId, variableNames);
/*     */   }
/*     */   
/*     */   public void completeTask(String taskId)
/*     */   {
/*  66 */     this.taskService.complete(taskId);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setVariable(String taskId, String variableName, Object value)
/*     */   {
/*  72 */     this.taskService.setVariable(taskId, variableName, value);
/*     */   }
/*     */   
/*     */   public void setVariableLocal(String taskId, String variableName, Object value)
/*     */   {
/*  77 */     this.taskService.setVariableLocal(taskId, variableName, value);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setVariables(String taskId, Map<String, ? extends Object> variables)
/*     */   {
/*  83 */     this.taskService.setVariables(taskId, variables);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setVariablesLocal(String taskId, Map<String, ? extends Object> variables)
/*     */   {
/*  89 */     this.taskService.setVariablesLocal(taskId, variables);
/*     */   }
/*     */   
/*     */   public void completeTask(String taskId, String... aryDestination)
/*     */   {
/*  94 */     TaskEntity task = (TaskEntity)((TaskQuery)this.taskService.createTaskQuery().taskId(taskId)).singleResult();
/*     */     
/*  96 */     String curNodeId = task.getTaskDefinitionKey();
/*  97 */     String actDefId = task.getProcessDefinitionId();
/*     */     
/*  99 */     Map<String, Object> activityMap = ActivitiUtil.a(actDefId, curNodeId, aryDestination);
/*     */     try {
/* 101 */       this.taskService.complete(taskId);
/*     */     } catch (Exception ex) {
/* 103 */       throw new RuntimeException(ex);
/*     */     }
/*     */     finally {
/* 106 */       ActivitiUtil.a(activityMap);
/*     */     }
/*     */   }
/*     */   
/*     */   public void completeTaskOnly(String taskId)
/*     */   {
/* 112 */     completeTask(taskId, null);
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-act\1.1.5\wf-act-1.1.5-pg.jar!\com\dstz\bpm\act\service\impl\ActTaskServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */