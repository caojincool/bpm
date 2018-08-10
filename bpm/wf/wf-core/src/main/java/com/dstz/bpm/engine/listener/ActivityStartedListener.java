/*    */ package com.dstz.bpm.engine.listener;
/*    */ 
/*    */ import com.dstz.bpm.act.listener.ActEventListener;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.core.manager.BpmTaskStackManager;
/*    */ import com.dstz.bpm.core.model.BpmTask;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import javax.annotation.Resource;
/*    */ import org.activiti.engine.delegate.event.ActivitiEvent;
/*    */ import org.activiti.engine.delegate.event.impl.ActivitiActivityEventImpl;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class ActivityStartedListener
/*    */   implements ActEventListener
/*    */ {
/*    */   @Resource
/*    */   BpmTaskStackManager aN;
/*    */   
/*    */   public void notify(ActivitiEvent event)
/*    */   {
/* 31 */     if (!(event instanceof ActivitiActivityEventImpl)) { return;
/*    */     }
/* 33 */     ActivitiActivityEventImpl activitEvent = (ActivitiActivityEventImpl)event;
/*    */     
/* 35 */     if (activitEvent.getActivityType().equals("callActivity")) {
/* 36 */       b(activitEvent);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   private void b(ActivitiActivityEventImpl activitEvent)
/*    */   {
/* 43 */     DefualtTaskActionCmd action = (DefualtTaskActionCmd)BpmContext.getActionModel();
/* 44 */     BpmTask task = new BpmTask();
/* 45 */     task.setId(activitEvent.getExecutionId());
/* 46 */     task.setName(activitEvent.getActivityName());
/* 47 */     task.setNodeId(activitEvent.getActivityId());
/* 48 */     task.setInstId(action.getInstanceId());
/*    */     
/* 50 */     this.aN.createStackByTask(task, action.getTaskStack());
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\ActivityStartedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */