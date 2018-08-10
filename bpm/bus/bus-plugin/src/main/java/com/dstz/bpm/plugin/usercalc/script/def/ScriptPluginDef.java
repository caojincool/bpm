/*    */ package com.dstz.bpm.plugin.usercalc.script.def;
/*    */ 
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScriptPluginDef
/*    */   extends AbstractUserCalcPluginDef
/*    */ {
/*    */   @NotEmpty(message="脚本插件，脚本不能为空")
/* 12 */   private String script = "";
/*    */   @NotEmpty(message="脚本插件，脚本描述不能为空")
/* 14 */   private String description = "";
/*    */   
/*    */   public String getScript()
/*    */   {
/* 18 */     return this.script;
/*    */   }
/*    */   
/*    */   public void setScript(String script) {
/* 22 */     this.script = script;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 26 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 30 */     this.description = description;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\script\def\ScriptPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */