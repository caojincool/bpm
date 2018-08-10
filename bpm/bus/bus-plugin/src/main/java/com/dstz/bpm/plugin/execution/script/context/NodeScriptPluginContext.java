/*    */ package com.dstz.bpm.plugin.execution.script.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractBpmExecutionPluginContext;
/*    */ import com.dstz.bpm.plugin.execution.script.def.NodeScriptPluginDef;
/*    */ import com.dstz.bpm.plugin.execution.script.plugin.NodeScriptPlugin;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public class NodeScriptPluginContext
/*    */   extends AbstractBpmExecutionPluginContext<NodeScriptPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = -5958682303600423597L;
/*    */   
/*    */   public List<EventType> getEventTypes()
/*    */   {
/* 27 */     List<EventType> list = new ArrayList();
/* 28 */     list.add(EventType.START_EVENT);
/* 29 */     list.add(EventType.END_EVENT);
/* 30 */     list.add(EventType.TASK_COMPLETE_EVENT);
/* 31 */     list.add(EventType.TASK_CREATE_EVENT);
/* 32 */     return list;
/*    */   }
/*    */   
/*    */   public Class<? extends RunTimePlugin> getPluginClass() {
/* 36 */     return NodeScriptPlugin.class;
/*    */   }
/*    */   
/*    */ 
/*    */   protected NodeScriptPluginDef b(JSON pluginJson)
/*    */   {
/* 42 */     JSONObject jsonObject = (JSONObject)pluginJson;
/* 43 */     NodeScriptPluginDef def = new NodeScriptPluginDef();
/* 44 */     for (String key : jsonObject.keySet()) {
/*    */       try {
/* 46 */         EventType event = EventType.fromKey(key);
/* 47 */         def.a(event, jsonObject.getString(key));
/*    */       }
/*    */       catch (Exception e) {}
/*    */     }
/*    */     
/*    */ 
/* 53 */     return def;
/*    */   }
/*    */   
/*    */   public String getTitle()
/*    */   {
/* 58 */     return "脚本";
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\execution\script\context\NodeScriptPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */