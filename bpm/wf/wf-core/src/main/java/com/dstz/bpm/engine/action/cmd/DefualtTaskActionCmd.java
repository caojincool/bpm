/*     */ package com.dstz.bpm.engine.action.cmd;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.exception.WorkFlowException;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.core.model.BpmTaskStack;
/*     */ import com.dstz.bpm.engine.action.handler.AbsActionHandler;
/*     */ import com.dstz.bpm.engine.constant.TaskSkipType;
/*     */ import java.util.Map;
/*     */ import org.activiti.engine.delegate.DelegateTask;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefualtTaskActionCmd
/*     */   extends BaseActionCmd
/*     */   implements TaskActionCmd
/*     */ {
/*     */   private String taskId;
/*     */   private IBpmTask ao;
/*     */   private DelegateTask ap;
/*     */   private String ai;
/*     */   private BpmTaskStack aq;
/*     */   private BpmTaskStack ar;
/*  38 */   private TaskSkipType as = TaskSkipType.NO_SKIP;
/*     */   
/*     */ 
/*     */   public DefualtTaskActionCmd() {}
/*     */   
/*     */   public DefualtTaskActionCmd(String flowParam)
/*     */   {
/*  45 */     super(flowParam);
/*     */   }
/*     */   
/*     */   public String getTaskId() {
/*  49 */     if (this.ao != null) {
/*  50 */       return this.ao.getId();
/*     */     }
/*     */     
/*  53 */     return this.taskId;
/*     */   }
/*     */   
/*     */   public void setTaskId(String taskId) {
/*  57 */     this.taskId = taskId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void initSpecialParam(JSONObject flowParam)
/*     */   {
/*  64 */     String taskId = flowParam.getString("taskId");
/*  65 */     if (StringUtil.isEmpty(taskId)) {
/*  66 */       throw new BusinessException("taskId 不能为空", BpmStatusCode.TASK_NOT_FOUND);
/*     */     }
/*  68 */     setTaskId(taskId);
/*     */     
/*  70 */     String destination = flowParam.getString("destination");
/*  71 */     setDestination(destination);
/*     */     
/*  73 */     String opinion = flowParam.getString("opinion");
/*  74 */     setOpinion(opinion);
/*     */   }
/*     */   
/*     */   public IBpmTask getBpmTask() {
/*  78 */     return this.ao;
/*     */   }
/*     */   
/*     */   public void setBpmTask(IBpmTask task) {
/*  82 */     this.ao = task;
/*     */   }
/*     */   
/*     */   public DelegateTask getDelagateTask() {
/*  86 */     if (this.ap == null) {}
/*     */     
/*     */ 
/*  89 */     return this.ap;
/*     */   }
/*     */   
/*     */   public void setDelagateTask(DelegateTask task) {
/*  93 */     this.ap = task;
/*     */   }
/*     */   
/*     */   public String getNodeId()
/*     */   {
/*  98 */     return this.ao.getNodeId();
/*     */   }
/*     */   
/*     */   public String getOpinion()
/*     */   {
/* 103 */     return this.ai;
/*     */   }
/*     */   
/*     */   public void setOpinion(String opinion) {
/* 107 */     this.ai = opinion;
/*     */   }
/*     */   
/*     */   public BpmTaskStack getTaskStack() {
/* 111 */     return this.aq;
/*     */   }
/*     */   
/*     */   public void setTaskStack(BpmTaskStack taskStack) {
/* 115 */     this.aq = taskStack;
/*     */   }
/*     */   
/*     */   public BpmTaskStack getParentTaskStack() {
/* 119 */     return this.ar;
/*     */   }
/*     */   
/*     */   public void setParentTaskStack(BpmTaskStack parentTaskStack) {
/* 123 */     this.ar = parentTaskStack;
/*     */   }
/*     */   
/*     */   public void addVariable(String variableName, Object value)
/*     */   {
/* 128 */     if (this.ap == null) {
/* 129 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*     */     }
/* 131 */     this.ap.setVariable(variableName, value);
/*     */   }
/*     */   
/*     */   public Object getVariable(String variableName)
/*     */   {
/* 136 */     if (this.ap == null) {
/* 137 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*     */     }
/* 139 */     return this.ap.getVariable(variableName);
/*     */   }
/*     */   
/*     */   public boolean hasVariable(String variableName)
/*     */   {
/* 144 */     if (this.ap == null) {
/* 145 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*     */     }
/* 147 */     return this.ap.hasVariable(variableName);
/*     */   }
/*     */   
/*     */   public void removeVariable(String variableName)
/*     */   {
/* 152 */     if (this.ap == null) {
/* 153 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Map<String, Object> getVariables()
/*     */   {
/* 160 */     return this.ap.getVariables();
/*     */   }
/*     */   
/*     */   public synchronized String a()
/*     */   {
/* 165 */     if (this.hasExecuted) {
/* 166 */       throw new BusinessException("action cmd caonot be invoked twice", BpmStatusCode.PARAM_ILLEGAL);
/*     */     }
/* 168 */     this.hasExecuted = true;
/*     */     
/* 170 */     ActionType actonType = ActionType.fromKey(getActionName());
/*     */     
/* 172 */     AbsActionHandler handler = (AbsActionHandler)AppUtil.getBean(actonType.getBeanId());
/* 173 */     if (handler == null) {
/* 174 */       throw new BusinessException("action beanId cannot be found :" + actonType.getName(), BpmStatusCode.NO_TASK_ACTION);
/*     */     }
/* 176 */     handler.g(this);
/* 177 */     return handler.getActionType().getName();
/*     */   }
/*     */   
/*     */   public TaskSkipType b() {
/* 181 */     return this.as;
/*     */   }
/*     */   
/*     */   public void setHasSkipThisTask(TaskSkipType isSkip) {
/* 185 */     this.as = isSkip;
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\cmd\DefualtTaskActionCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */