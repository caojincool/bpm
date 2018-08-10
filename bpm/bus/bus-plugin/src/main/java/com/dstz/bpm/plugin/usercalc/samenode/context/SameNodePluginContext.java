/*    */ package com.dstz.bpm.plugin.usercalc.samenode.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.JsonUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*    */ import com.dstz.bpm.plugin.usercalc.samenode.def.SameNodePluginDef;
/*    */ import com.dstz.bpm.plugin.usercalc.samenode.runtime.SameNodePlugin;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public class SameNodePluginContext
/*    */   extends AbstractUserCalcPluginContext<SameNodePluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = 919433269116580830L;
/*    */   
/*    */   public String getDescription()
/*    */   {
/* 24 */     SameNodePluginDef def = (SameNodePluginDef)getBpmPluginDef();
/* 25 */     if (def == null) return "";
/* 26 */     return "节点：" + def.getNodeId();
/*    */   }
/*    */   
/*    */   public String getTitle()
/*    */   {
/* 31 */     return "相同节点执行人";
/*    */   }
/*    */   
/*    */   public Class<? extends RunTimePlugin> getPluginClass()
/*    */   {
/* 36 */     return SameNodePlugin.class;
/*    */   }
/*    */   
/*    */   protected SameNodePluginDef b(JSONObject pluginJson)
/*    */   {
/* 41 */     SameNodePluginDef def = new SameNodePluginDef();
/* 42 */     String nodeId = JsonUtil.getString(pluginJson, "nodeId");
/* 43 */     def.setNodeId(nodeId);
/* 44 */     return def;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\samenode\context\SameNodePluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */