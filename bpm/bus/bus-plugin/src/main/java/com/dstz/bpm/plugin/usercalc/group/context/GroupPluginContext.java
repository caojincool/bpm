/*    */ package com.dstz.bpm.plugin.usercalc.group.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.JsonUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*    */ import com.dstz.bpm.plugin.usercalc.group.def.GroupPluginDef;
/*    */ import com.dstz.bpm.plugin.usercalc.group.runtime.GroupPlugin;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public class GroupPluginContext
/*    */   extends AbstractUserCalcPluginContext
/*    */ {
/*    */   private static final long serialVersionUID = -6084686546165511275L;
/*    */   
/*    */   public String getDescription()
/*    */   {
/* 23 */     GroupPluginDef def = (GroupPluginDef)getBpmPluginDef();
/* 24 */     if (def == null) { return "";
/*    */     }
/* 26 */     return String.format("%s[%s]", new Object[] { def.getTypeName(), def.getGroupName() });
/*    */   }
/*    */   
/*    */   public String getTitle()
/*    */   {
/* 31 */     return "组";
/*    */   }
/*    */   
/*    */   public Class<? extends RunTimePlugin> getPluginClass()
/*    */   {
/* 36 */     return GroupPlugin.class;
/*    */   }
/*    */   
/*    */   protected BpmUserCalcPluginDef parseJson(JSONObject pluginJson)
/*    */   {
/* 41 */     GroupPluginDef def = new GroupPluginDef();
/* 42 */     String groupType = JsonUtil.getString(pluginJson, "type");
/* 43 */     String groupTypeName = JsonUtil.getString(pluginJson, "typeName");
/*    */     
/* 45 */     def.setType(groupType);
/* 46 */     def.setTypeName(groupTypeName);
/*    */     
/* 48 */     String groupKey = JsonUtil.getString(pluginJson, "groupKey");
/* 49 */     String groupName = JsonUtil.getString(pluginJson, "groupName");
/* 50 */     def.setGroupKey(groupKey);
/* 51 */     def.setGroupName(groupName);
/* 52 */     return def;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\usercalc\group\context\GroupPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */