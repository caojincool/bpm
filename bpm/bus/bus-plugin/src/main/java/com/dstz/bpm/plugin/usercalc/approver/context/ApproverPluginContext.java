/*    */ package com.dstz.bpm.plugin.usercalc.approver.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*    */ import com.dstz.bpm.plugin.usercalc.approver.def.ApproverPluginDef;
/*    */ import com.dstz.bpm.plugin.usercalc.approver.runtime.ApproverPlugin;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public class ApproverPluginContext
/*    */   extends AbstractUserCalcPluginContext<ApproverPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = 2164348894650802414L;
/*    */   
/*    */   public String getDescription()
/*    */   {
/* 25 */     return "流程历史审批人";
/*    */   }
/*    */   
/*    */   public String getTitle()
/*    */   {
/* 30 */     return "流程历史审批人";
/*    */   }
/*    */   
/*    */   public Class<? extends RunTimePlugin> getPluginClass()
/*    */   {
/* 35 */     return ApproverPlugin.class;
/*    */   }
/*    */   
/*    */   protected ApproverPluginDef a(JSONObject pluginJson)
/*    */   {
/* 40 */     ApproverPluginDef def = new ApproverPluginDef();
/* 41 */     return def;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\approver\context\ApproverPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */