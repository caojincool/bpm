/*    */ package com.dstz.bpm.plugin.usercalc.script.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.JsonUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*    */ import com.dstz.bpm.plugin.usercalc.script.def.ScriptPluginDef;
/*    */ import com.dstz.bpm.plugin.usercalc.script.runtime.ScriptPlugin;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public class ScriptPluginContext
/*    */   extends AbstractUserCalcPluginContext<ScriptPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = -2353875054502587417L;
/*    */   
/*    */   public String getDescription()
/*    */   {
/* 21 */     ScriptPluginDef def = (ScriptPluginDef)getBpmPluginDef();
/* 22 */     if (def == null) return "";
/* 23 */     return def.getDescription();
/*    */   }
/*    */   
/*    */   public Class<? extends RunTimePlugin> getPluginClass()
/*    */   {
/* 28 */     return ScriptPlugin.class;
/*    */   }
/*    */   
/*    */   public String getTitle()
/*    */   {
/* 33 */     return "脚本";
/*    */   }
/*    */   
/*    */   protected ScriptPluginDef c(JSONObject pluginJson)
/*    */   {
/* 38 */     ScriptPluginDef def = new ScriptPluginDef();
/* 39 */     String script = pluginJson.getString("script");
/* 40 */     String description = JsonUtil.getString(pluginJson, "description", "脚本");
/* 41 */     def.setScript(script);
/* 42 */     def.setDescription(description);
/* 43 */     return def;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\script\context\ScriptPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */