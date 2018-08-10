/*    */ package com.dstz.bpm.engine.listener;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.act.listener.ActEventListener;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*    */ import com.dstz.bpm.core.manager.BpmDefinitionManager;
/*    */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*    */ import com.dstz.bpm.core.manager.BpmTaskStackManager;
/*    */ import com.dstz.bpm.core.model.BpmDefinition;
/*    */ import com.dstz.bpm.core.model.BpmInstance;
/*    */ import com.dstz.bpm.core.model.BpmTaskStack;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import java.util.Date;
/*    */ import javax.annotation.Resource;
/*    */ import org.activiti.engine.delegate.event.ActivitiEvent;
/*    */ import org.activiti.engine.delegate.event.impl.ActivitiActivityEventImpl;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class ActivityComplatedListener
/*    */   implements ActEventListener
/*    */ {
/*    */   @Resource
/*    */   private BpmInstanceManager aM;
/*    */   @Resource
/*    */   private BpmDefinitionManager c;
/*    */   @Resource
/*    */   private BpmTaskStackManager aA;
/*    */   
/*    */   public void notify(ActivitiEvent event)
/*    */   {
/* 36 */     if (!(event instanceof ActivitiActivityEventImpl)) { return;
/*    */     }
/*    */     
/* 39 */     ActivitiActivityEventImpl activitEvent = (ActivitiActivityEventImpl)event;
/*    */     
/* 41 */     if (activitEvent.getActivityType().equals("callActivity"))
/*    */     {
/* 43 */       a(activitEvent);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void a(ActivitiActivityEventImpl activitEvent)
/*    */   {
/* 57 */     BaseActionCmd actionCmd = (BaseActionCmd)BpmContext.getActionModel();
/* 58 */     IBpmInstance childInstance = actionCmd.getBpmInstance();
/*    */     
/*    */ 
/* 61 */     if (StringUtil.isZeroEmpty(childInstance.getParentInstId())) {
/* 62 */       throw new BusinessException("子流程提供的线程变量中，流程实例信息异常！", BpmStatusCode.ACTIONCMD_ERROR);
/*    */     }
/*    */     
/* 65 */     BpmInstance bpmInstance = (BpmInstance)this.aM.get(childInstance.getParentInstId());
/* 66 */     if (!bpmInstance.getActInstId().equals(activitEvent.getProcessInstanceId())) {
/* 67 */       throw new BusinessException("子流程提供的父流程实例，与外部子流程ACTVITI actInstanceID 不一致！", BpmStatusCode.ACTIONCMD_ERROR);
/*    */     }
/*    */     
/*    */ 
/* 71 */     BpmTaskStack bpmTaskStack = c(activitEvent.getExecutionId());
/*    */     
/* 73 */     BpmDefinition bpmDefinition = (BpmDefinition)this.c.get(bpmInstance.getDefId());
/*    */     
/* 75 */     DefualtTaskActionCmd callActiviti = new DefualtTaskActionCmd();
/* 76 */     callActiviti.setBpmDefinition(bpmDefinition);
/* 77 */     callActiviti.setBpmInstance(bpmInstance);
/* 78 */     callActiviti.setTaskStack(bpmTaskStack);
/*    */     
/*    */ 
/* 81 */     BpmContext.setActionModel(callActiviti);
/*    */   }
/*    */   
/*    */ 
/*    */   private BpmTaskStack c(String executionId)
/*    */   {
/* 87 */     BpmTaskStack bpmTaskStack = this.aA.getByTaskId(executionId);
/*    */     
/* 89 */     bpmTaskStack.setEndTime(new Date());
/* 90 */     this.aA.update(bpmTaskStack);
/* 91 */     return bpmTaskStack;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\ActivityComplatedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */