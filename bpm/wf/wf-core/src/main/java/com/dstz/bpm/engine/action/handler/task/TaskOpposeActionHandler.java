/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.constant.NodeType;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class TaskOpposeActionHandler
/*    */   extends AbstractTaskActionHandler<DefualtTaskActionCmd>
/*    */ {
/*    */   public ActionType getActionType()
/*    */   {
/* 19 */     return ActionType.OPPOSE;
/*    */   }
/*    */   
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
/* 35 */     return 2;
/*    */   }
/*    */   
/*    */   public Boolean isSupport(BpmNodeDef nodeDef)
/*    */   {
/* 40 */     NodeType nodeType = nodeDef.getType();
/*    */     
/* 42 */     if ((nodeType == NodeType.USERTASK) || (nodeType == NodeType.SIGNTASK)) {
/* 43 */       return Boolean.valueOf(true);
/*    */     }
/*    */     
/* 46 */     return Boolean.valueOf(false);
/*    */   }
/*    */   
/*    */   public String getConfigPage()
/*    */   {
/* 51 */     return "/bpm/task/taskOpinionDialog.html";
/*    */   }
/*    */   
/*    */   public Boolean isDefault()
/*    */   {
/* 56 */     return Boolean.valueOf(false);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskOpposeActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */