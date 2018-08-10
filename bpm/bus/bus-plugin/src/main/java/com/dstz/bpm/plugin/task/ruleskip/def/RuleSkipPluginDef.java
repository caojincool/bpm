/*    */ package com.dstz.bpm.plugin.task.ruleskip.def;
/*    */ 
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmTaskPluginDef;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.validation.Valid;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RuleSkipPluginDef
/*    */   extends AbstractBpmTaskPluginDef
/*    */ {
/*    */   @Valid
/* 15 */   private List<JumpRule> S = new ArrayList();
/*    */   
/*    */   public List<JumpRule> getJumpRules()
/*    */   {
/* 19 */     return this.S;
/*    */   }
/*    */   
/*    */   public void setJumpRules(List<JumpRule> jumpRules) {
/* 23 */     this.S = jumpRules;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\ruleskip\def\RuleSkipPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */