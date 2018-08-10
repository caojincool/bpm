/*    */ package com.dstz.bpm.plugin.task.userassign.def;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmTaskPluginDef;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.validation.Valid;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserAssignPluginDef
/*    */   extends AbstractBpmTaskPluginDef
/*    */ {
/*    */   @Valid
/* 17 */   List<UserAssignRule> Y = new ArrayList();
/*    */   
/*    */   public List<UserAssignRule> getRuleList()
/*    */   {
/* 21 */     return this.Y;
/*    */   }
/*    */   
/*    */   public void setRuleList(List<UserAssignRule> ruleList) {
/* 25 */     this.Y = ruleList;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\userassign\def\UserAssignPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */