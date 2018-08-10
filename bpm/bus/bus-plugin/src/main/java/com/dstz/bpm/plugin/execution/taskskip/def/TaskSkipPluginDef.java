/*    */ package com.dstz.bpm.plugin.execution.taskskip.def;
/*    */ 
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskSkipPluginDef
/*    */   extends AbstractBpmExecutionPluginDef
/*    */ {
/*    */   private String l;
/* 12 */   private String script = "";
/*    */   
/*    */   public String getScript()
/*    */   {
/* 16 */     return this.script;
/*    */   }
/*    */   
/*    */   public void setScript(String script)
/*    */   {
/* 21 */     this.script = script;
/*    */   }
/*    */   
/*    */   public String getSkipTypeArr()
/*    */   {
/* 26 */     return this.l;
/*    */   }
/*    */   
/*    */   public void setSkipTypeArr(String skipTypeArr)
/*    */   {
/* 31 */     this.l = skipTypeArr;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\execution\taskskip\def\TaskSkipPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */