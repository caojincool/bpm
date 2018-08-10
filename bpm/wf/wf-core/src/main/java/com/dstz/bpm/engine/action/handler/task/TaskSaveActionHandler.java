/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class TaskSaveActionHandler extends AbstractTaskActionHandler<DefualtTaskActionCmd>
/*    */ {
/*    */   public ActionType getActionType()
/*    */   {
/* 12 */     return ActionType.SAVE;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void a(DefualtTaskActionCmd actionModel) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void d(DefualtTaskActionCmd actionModel) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void e(DefualtTaskActionCmd actionModel) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getSn()
/*    */   {
/* 35 */     return 1;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getConfigPage()
/*    */   {
/* 41 */     return "";
/*    */   }
/*    */   
/*    */   public Boolean isDefault()
/*    */   {
/* 46 */     return Boolean.valueOf(true);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskSaveActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */