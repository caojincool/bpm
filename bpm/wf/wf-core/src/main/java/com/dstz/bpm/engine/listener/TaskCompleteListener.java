/*     */ package com.dstz.bpm.engine.listener;
/*     */ 
/*     */ import com.dstz.base.core.util.BeanUtils;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.constant.EventType;
/*     */ import com.dstz.bpm.api.constant.InstanceStatus;
/*     */ import com.dstz.bpm.api.constant.OpinionStatus;
/*     */ import com.dstz.bpm.api.constant.ScriptType;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskStackManager;
/*     */ import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
/*     */ import com.dstz.bpm.core.model.BpmInstance;
/*     */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*     */ import com.dstz.bpm.core.model.BpmTaskStack;
/*     */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.activiti.engine.impl.persistence.entity.TaskEntity;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class TaskCompleteListener
/*     */   extends AbstractTaskListener<DefualtTaskActionCmd>
/*     */ {
/*     */   private static final long serialVersionUID = 6844821899585103714L;
/*     */   @Resource
/*     */   BpmInstanceManager f;
/*     */   @Resource
/*     */   BpmTaskManager aQ;
/*     */   @Resource
/*     */   TaskIdentityLinkManager i;
/*     */   @Resource
/*     */   BpmTaskOpinionManager aO;
/*     */   @Resource
/*     */   BpmTaskStackManager aA;
/*     */   
/*     */   public EventType getBeforeTriggerEventType()
/*     */   {
/*  53 */     return EventType.TASK_COMPLETE_EVENT;
/*     */   }
/*     */   
/*     */   public EventType getAfterTriggerEventType()
/*     */   {
/*  58 */     return EventType.TASK_POST_COMPLETE_EVENT;
/*     */   }
/*     */   
/*     */   public void g(DefualtTaskActionCmd taskActionModel)
/*     */   {
/*  63 */     this.LOG.debug("任务【{}】执行完成事件 - TaskID: {}", taskActionModel.getBpmTask().getName(), taskActionModel.getBpmTask().getId());
/*     */     
/*  65 */     Map<String, Object> actionVariables = taskActionModel.getActionVariables();
/*  66 */     if (BeanUtils.isEmpty(actionVariables)) {
/*  67 */       return;
/*     */     }
/*     */     
/*  70 */     for (String key : actionVariables.keySet()) {
/*  71 */       taskActionModel.addVariable(key, actionVariables.get(key));
/*     */     }
/*  73 */     this.LOG.debug("设置流程变量【{}】", actionVariables.keySet().toString());
/*     */   }
/*     */   
/*     */ 
/*     */   public void h(DefualtTaskActionCmd taskActionModel)
/*     */   {
/*  79 */     DefualtTaskActionCmd complateModel = taskActionModel;
/*     */     
/*  81 */     this.LOG.trace("执行任务完成动作=====》更新任务意见状态");
/*  82 */     j(complateModel);
/*     */     
/*  84 */     this.LOG.trace("执行任务完成动作=====》更新任务堆栈记录");
/*  85 */     k(complateModel);
/*     */     
/*  87 */     this.LOG.trace("执行任务完成动作=====》删除任务相关信息 - 任务、任务后续人");
/*  88 */     l(complateModel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void i(DefualtTaskActionCmd taskActionModel) {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected ScriptType getScriptType()
/*     */   {
/*  99 */     return ScriptType.COMPLETE;
/*     */   }
/*     */   
/*     */   public DefualtTaskActionCmd b(TaskEntity taskEntity)
/*     */   {
/* 104 */     DefualtTaskActionCmd model = (DefualtTaskActionCmd)BpmContext.getActionModel();
/* 105 */     model.setDelagateTask(taskEntity);
/* 106 */     return model;
/*     */   }
/*     */   
/*     */   private void j(DefualtTaskActionCmd taskActionModel)
/*     */   {
/* 111 */     InstanceStatus flowStatus = InstanceStatus.getByActionName(taskActionModel.getActionName());
/*     */     
/*     */ 
/* 114 */     BpmInstance instance = (BpmInstance)taskActionModel.getBpmInstance();
/* 115 */     if (!flowStatus.getKey().equals(instance.getStatus())) {
/* 116 */       instance.setStatus(flowStatus.getKey());
/* 117 */       this.f.update(instance);
/*     */     }
/*     */     
/*     */ 
/* 121 */     BpmTaskOpinion bpmTaskOpinion = this.aO.getByTaskId(taskActionModel.getTaskId());
/* 122 */     if (bpmTaskOpinion == null) { return;
/*     */     }
/* 124 */     OpinionStatus opnionStatus = d(taskActionModel.getActionName());
/* 125 */     bpmTaskOpinion.setStatus(opnionStatus.getKey());
/* 126 */     bpmTaskOpinion.setApproveTime(new Date());
/*     */     
/* 128 */     bpmTaskOpinion.setDurMs(Long.valueOf(bpmTaskOpinion.getApproveTime().getTime() - bpmTaskOpinion.getCreateTime().getTime()));
/* 129 */     bpmTaskOpinion.setOpinion(taskActionModel.getOpinion());
/*     */     
/* 131 */     IUser user = ContextUtil.getCurrentUser();
/* 132 */     if (user != null) {
/* 133 */       bpmTaskOpinion.setApprover(user.getUserId());
/* 134 */       bpmTaskOpinion.setApproverName(user.getFullname());
/*     */     }
/*     */     
/* 137 */     this.aO.update(bpmTaskOpinion);
/*     */   }
/*     */   
/*     */   private OpinionStatus d(String actionName)
/*     */   {
/* 142 */     ActionType action = ActionType.fromKey(actionName);
/*     */     
/* 144 */     switch (action) {
/*     */     case AGREE: 
/* 146 */       return OpinionStatus.AGREE;
/* 147 */     case OPPOSE:  return OpinionStatus.OPPOSE;
/* 148 */     case REJECT:  return OpinionStatus.REJECT;
/* 149 */     case REJECT2START:  return OpinionStatus.REJECT_TO_START;
/* 150 */     case RECOVER:  return OpinionStatus.REVOKER;
/* 151 */     case START:  return OpinionStatus.START;
/* 152 */     case MANUALEND:  return OpinionStatus.MANUAL_END;
/*     */     }
/* 154 */     return OpinionStatus.AWAITING_CHECK;
/*     */   }
/*     */   
/*     */ 
/*     */   private void k(DefualtTaskActionCmd taskActionModel)
/*     */   {
/* 160 */     BpmTaskStack bpmTaskStack = this.aA.getByTaskId(taskActionModel.getTaskId());
/*     */     
/* 162 */     bpmTaskStack.setEndTime(new Date());
/* 163 */     this.aA.update(bpmTaskStack);
/* 164 */     taskActionModel.setTaskStack(bpmTaskStack);
/*     */   }
/*     */   
/*     */   private void l(DefualtTaskActionCmd taskActionModel) {
/* 168 */     this.i.removeByTaskId(taskActionModel.getTaskId());
/* 169 */     this.aQ.remove(taskActionModel.getTaskId());
/*     */   }
/*     */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\TaskCompleteListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */