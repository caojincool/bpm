/*    */ package com.dstz.bpm.plugin.task.reminders.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractBpmTaskPluginContext;
/*    */ import com.dstz.bpm.plugin.task.reminders.def.RemindersPluginDef;
/*    */ import com.dstz.bpm.plugin.task.reminders.plugin.RemindersPlugin;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class RemindersPluginContext
/*    */   extends AbstractBpmTaskPluginContext<RemindersPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = -8171025388788811778L;
/*    */   
/*    */   public List<EventType> getEventTypes()
/*    */   {
/* 20 */     List<EventType> list = new ArrayList();
/* 21 */     list.add(EventType.TASK_CREATE_EVENT);
/* 22 */     list.add(EventType.TASK_COMPLETE_EVENT);
/* 23 */     return list;
/*    */   }
/*    */   
/*    */   public Class<? extends RunTimePlugin> getPluginClass() {
/* 27 */     return RemindersPlugin.class;
/*    */   }
/*    */   
/*    */ 
/*    */   protected RemindersPluginDef d(JSON pluginJson)
/*    */   {
/* 33 */     RemindersPluginDef def = (RemindersPluginDef)JSON.toJavaObject(pluginJson, RemindersPluginDef.class);
/* 34 */     return def;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getTitle()
/*    */   {
/* 40 */     return "任务催办";
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\reminders\context\RemindersPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */