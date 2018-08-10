/*     */ package com.dstz.bpm.engine.listener;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.constant.EventType;
/*     */ import com.dstz.bpm.api.constant.NodeType;
/*     */ import com.dstz.bpm.api.constant.ScriptType;
/*     */ import com.dstz.bpm.api.constant.TaskStatus;
/*     */ import com.dstz.bpm.api.constant.TaskType;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.exception.WorkFlowException;
/*     */ import com.dstz.bpm.api.model.def.NodeProperties;
/*     */ import com.dstz.bpm.api.model.form.BpmForm;
/*     */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskStackManager;
/*     */ import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
/*     */ import com.dstz.bpm.core.model.BpmTask;
/*     */ import com.dstz.bpm.core.model.BpmTaskStack;
/*     */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.activiti.engine.impl.persistence.entity.TaskEntity;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class TaskCreateListener
/*     */   extends AbstractTaskListener<DefualtTaskActionCmd>
/*     */ {
/*     */   private static final long serialVersionUID = -7836822392037648008L;
/*     */   @Resource
/*     */   private BpmTaskManager aQ;
/*     */   @Resource
/*     */   private BpmProcessDefService a;
/*     */   @Resource
/*     */   private BpmTaskOpinionManager aO;
/*     */   @Resource
/*     */   private BpmTaskStackManager aR;
/*     */   @Resource
/*     */   private TaskIdentityLinkManager i;
/*     */   
/*     */   public EventType getBeforeTriggerEventType()
/*     */   {
/*  60 */     return EventType.TASK_CREATE_EVENT;
/*     */   }
/*     */   
/*     */   public EventType getAfterTriggerEventType()
/*     */   {
/*  65 */     return EventType.TASK_POST_CREATE_EVENT;
/*     */   }
/*     */   
/*     */   public void g(DefualtTaskActionCmd taskActionModel)
/*     */   {
/*  70 */     this.LOG.debug("任务【{}】执行创建过程 - taskID: {}", taskActionModel.getBpmTask().getName(), taskActionModel.getBpmTask().getId());
/*     */   }
/*     */   
/*     */ 
/*     */   public void h(DefualtTaskActionCmd taskActionModel)
/*     */   {
/*  76 */     IBpmTask task = taskActionModel.getBpmTask();
/*     */     
/*  78 */     d(taskActionModel);
/*     */     
/*  80 */     this.aQ.create((BpmTask)task);
/*     */     
/*  82 */     this.aO.createOpinionByTask(taskActionModel);
/*     */     
/*  84 */     BpmTaskStack stack = this.aR.createStackByTask(task, taskActionModel.getParentTaskStack());
/*  85 */     taskActionModel.setTaskStack(stack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void i(DefualtTaskActionCmd taskActionModel) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ScriptType getScriptType()
/*     */   {
/*  99 */     return ScriptType.CREATE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void d(TaskActionCmd taskActionModel)
/*     */   {
/* 107 */     IBpmTask bpmTask = taskActionModel.getBpmTask();
/*     */     
/* 109 */     List<SysIdentity> identityList = taskActionModel.getBpmIdentity(bpmTask.getNodeId());
/* 110 */     BpmNodeDef nodeDef = this.a.getBpmNodeDef(bpmTask.getDefId(), bpmTask.getNodeId());
/*     */     
/* 112 */     if ((!nodeDef.getNodeProperties().isAllowExecutorEmpty()) && (BeanUtils.isEmpty(identityList))) {
/* 113 */       throw new WorkFlowException(bpmTask.getNodeId() + "任务候选人为空", BpmStatusCode.NO_ASSIGN_USER);
/*     */     }
/*     */     
/* 116 */     if (BeanUtils.isEmpty(identityList)) { return;
/*     */     }
/*     */     
/*     */ 
/* 120 */     SysIdentity firstIdentity = (SysIdentity)identityList.get(0);
/*     */     
/* 122 */     if ((identityList.size() == 1) && (firstIdentity.getType().equals("user"))) {
/* 123 */       bpmTask.setAssigneeId(firstIdentity.getId());
/* 124 */       bpmTask.setAssigneeNames(firstIdentity.getName());
/*     */     } else {
/* 126 */       bpmTask.setAssigneeId("0");
/*     */       
/* 128 */       List<String> names = new ArrayList();
/* 129 */       for (SysIdentity identity : identityList) {
/* 130 */         names.add(identity.getName());
/*     */       }
/*     */       
/* 133 */       bpmTask.setAssigneeNames(StringUtil.convertCollectionAsString(names));
/*     */     }
/*     */     
/* 136 */     this.i.createIdentityLink(bpmTask, identityList);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefualtTaskActionCmd b(TaskEntity taskEntity)
/*     */   {
/* 145 */     BaseActionCmd model = (BaseActionCmd)BpmContext.getActionModel();
/* 146 */     if (!taskEntity.getProcessInstanceId().equals(model.getBpmInstance().getActInstId())) {
/* 147 */       throw new BusinessException("数据异常，actioncdm 与 TaskEntity数据不一致，请检查！");
/*     */     }
/*     */     
/*     */ 
/* 151 */     BpmTask task = a(taskEntity, model.getBpmInstance());
/*     */     
/* 153 */     DefualtTaskActionCmd createModel = new DefualtTaskActionCmd();
/* 154 */     createModel.setBpmInstance(model.getBpmInstance());
/* 155 */     createModel.setBpmDefinition(model.getBpmDefinition());
/* 156 */     createModel.setBizDataMap(model.getBizDataMap());
/* 157 */     createModel.setBpmIdentities(model.getBpmIdentities());
/* 158 */     createModel.setBusinessKey(model.getBusinessKey());
/* 159 */     createModel.setActionName(ActionType.CREATE.getKey());
/*     */     
/* 161 */     createModel.setBpmTask(task);
/* 162 */     createModel.setDelagateTask(taskEntity);
/*     */     
/* 164 */     if ((model instanceof DefualtTaskActionCmd)) {
/* 165 */       createModel.setParentTaskStack(((DefualtTaskActionCmd)model).getTaskStack());
/*     */     }
/*     */     
/* 168 */     BpmContext.setActionModel(createModel);
/*     */     
/* 170 */     return createModel;
/*     */   }
/*     */   
/*     */   private BpmTask a(TaskEntity taskEntity, IBpmInstance iBpmInstance)
/*     */   {
/* 175 */     BpmNodeDef nodeDef = this.a.getBpmNodeDef(iBpmInstance.getDefId(), taskEntity.getTaskDefinitionKey());
/* 176 */     int isSupportMobile = 1;
/* 177 */     if ((nodeDef.getMobileForm() != null) && (nodeDef.getMobileForm().isFormEmpty())) {
/* 178 */       isSupportMobile = 0;
/*     */     }
/*     */     
/* 181 */     BpmTask task = new BpmTask();
/* 182 */     task.setActExecutionId(taskEntity.getExecutionId());
/* 183 */     task.setActInstId(taskEntity.getProcessInstanceId());
/* 184 */     task.setDefId(iBpmInstance.getDefId());
/* 185 */     task.setId(taskEntity.getId());
/* 186 */     task.setInstId(iBpmInstance.getId());
/* 187 */     task.setName(taskEntity.getName());
/* 188 */     task.setNodeId(taskEntity.getTaskDefinitionKey());
/* 189 */     task.setParentId("0");
/* 190 */     task.setPriority(Integer.valueOf(taskEntity.getPriority()));
/* 191 */     task.setStatus(TaskType.NORMAL.getKey());
/* 192 */     task.setTaskType(a(nodeDef.getType()));
/* 193 */     task.setSubject(iBpmInstance.getSubject());
/* 194 */     task.setSupportMobile(Integer.valueOf(isSupportMobile));
/* 195 */     task.setStatus(TaskStatus.NORMAL.getKey());
/* 196 */     task.setTaskId(taskEntity.getId());
/* 197 */     task.setTypeId(iBpmInstance.getTypeId());
/*     */     
/* 199 */     return task;
/*     */   }
/*     */   
/*     */   private String a(NodeType type) {
/* 203 */     switch (1.aS[type.ordinal()]) {
/* 204 */     case 1:  return TaskType.SIGN.getKey();
/*     */     case 2: 
/* 206 */       return TaskType.SUBFLOW.getKey();
/*     */     case 3: 
/* 208 */       return TaskType.NORMAL.getKey();
/*     */     }
/* 210 */     return TaskType.NORMAL.getKey();
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\TaskCreateListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */