/*    */ package com.dstz.bpm.plugin.usercalc.samenode.def;
/*    */ 
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ public class SameNodePluginDef extends com.dstz.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef {
/*    */   @NotEmpty(message="人员插件相同节点执行人，节点ID不能为空")
/*  7 */   private String a = "";
/*    */   
/*    */   public String getNodeId()
/*    */   {
/* 11 */     return this.a;
/*    */   }
/*    */   
/*    */   public void setNodeId(String nodeId) {
/* 15 */     this.a = nodeId;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\samenode\def\SameNodePluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */