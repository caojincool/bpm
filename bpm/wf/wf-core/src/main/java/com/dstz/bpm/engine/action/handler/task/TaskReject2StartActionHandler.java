/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.model.def.NodeProperties;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class TaskReject2StartActionHandler
/*    */   extends TaskRejectActionHandler
/*    */ {
/*    */   protected String a(DefualtTaskActionCmd actionModel, NodeProperties nodeProperties)
/*    */   {
/* 32 */     List<BpmNodeDef> nodeDefs = this.a.getStartNodes(actionModel.getDefId());
/*    */     
/* 34 */     if (nodeDefs.size() > 1) {}
/*    */     
/*    */ 
/*    */ 
/* 38 */     return ((BpmNodeDef)nodeDefs.get(0)).getNodeId();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public ActionType getActionType()
/*    */   {
/* 45 */     return ActionType.REJECT2START;
/*    */   }
/*    */   
/*    */   public int getSn()
/*    */   {
/* 50 */     return 4;
/*    */   }
/*    */   
/*    */   public Boolean isDefault()
/*    */   {
/* 55 */     return Boolean.valueOf(false);
/*    */   }
/*    */   
/*    */   public String getConfigPage()
/*    */   {
/* 60 */     return "/bpm/task/taskOpinionDialog.html";
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskReject2StartActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */