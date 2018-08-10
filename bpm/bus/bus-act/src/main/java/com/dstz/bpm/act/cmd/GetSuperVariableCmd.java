/*    */ package com.dstz.bpm.act.cmd;
/*    */ 
/*    */ import org.activiti.engine.impl.interceptor.Command;
/*    */ import org.activiti.engine.impl.interceptor.CommandContext;
/*    */ import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
/*    */ import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
/*    */ 
/*    */ 
/*    */ public class GetSuperVariableCmd
/*    */   implements Command<Object>
/*    */ {
/* 12 */   private String d = "";
/* 13 */   private String e = "";
/*    */   
/*    */   public void setBpmnInstId(String bpmnInstId) {
/* 16 */     this.d = bpmnInstId;
/*    */   }
/*    */   
/*    */   public void setVarName(String varName) {
/* 20 */     this.e = varName;
/*    */   }
/*    */   
/*    */   public GetSuperVariableCmd() {}
/*    */   
/*    */   public GetSuperVariableCmd(String bpmnInstId, String varName)
/*    */   {
/* 27 */     this.d = bpmnInstId;
/* 28 */     this.e = varName;
/*    */   }
/*    */   
/*    */   public Object execute(CommandContext context)
/*    */   {
/* 33 */     ExecutionEntity execution = context.getExecutionEntityManager().findExecutionById(this.d);
/* 34 */     if (execution.getSuperExecution() == null) {
/* 35 */       return null;
/*    */     }
/* 37 */     ExecutionEntity superExecution = execution.getSuperExecution();
/* 38 */     return superExecution.getVariable(this.e);
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-act\1.1.5\wf-act-1.1.5-pg.jar!\com\dstz\bpm\act\cmd\GetSuperVariableCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */