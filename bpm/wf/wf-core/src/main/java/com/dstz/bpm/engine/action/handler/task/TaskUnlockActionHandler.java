/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.constant.TaskStatus;
/*    */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*    */ import com.dstz.bpm.core.model.BpmTask;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import javax.annotation.Resource;
/*    */ 
/*    */ @org.springframework.stereotype.Component
/*    */ public class TaskUnlockActionHandler extends AbstractTaskActionHandler<DefualtTaskActionCmd>
/*    */ {
/*    */   @Resource
/*    */   BpmTaskManager aC;
/*    */   
/*    */   public ActionType getActionType()
/*    */   {
/* 19 */     return ActionType.UNLOCK;
/*    */   }
/*    */   
/*    */   public void f(DefualtTaskActionCmd model)
/*    */   {
/* 24 */     b(model);
/* 25 */     k(model);
/*    */     
/* 27 */     BpmTask task = (BpmTask)model.getBpmTask();
/* 28 */     if (!task.getStatus().equals(TaskStatus.LOCK.getKey())) {
/* 29 */       throw new BusinessException("该任务并非锁定状态,或已经被解锁锁定，解锁失败");
/*    */     }
/*    */     
/* 32 */     this.aC.unLockTask(task.getId());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   protected void d(DefualtTaskActionCmd actionModel) {}
/*    */   
/*    */ 
/*    */ 
/*    */   protected void e(DefualtTaskActionCmd actionModel) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public int getSn()
/*    */   {
/* 47 */     return 6;
/*    */   }
/*    */   
/*    */   public Boolean isDefault()
/*    */   {
/* 52 */     return Boolean.valueOf(false);
/*    */   }
/*    */   
/*    */   public String getDefaultGroovyScript()
/*    */   {
/* 57 */     return "return task.getStatus().equals(TaskStatus.LOCK.getKey();";
/*    */   }
/*    */   
/*    */   public String getConfigPage()
/*    */   {
/* 62 */     return "";
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskUnlockActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */