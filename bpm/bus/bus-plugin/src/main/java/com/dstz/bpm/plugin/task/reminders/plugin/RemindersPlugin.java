/*    */ package com.dstz.bpm.plugin.task.reminders.plugin;
/*    */ 
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmTaskPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
/*    */ import com.dstz.bpm.plugin.task.reminders.def.Reminder;
/*    */ import com.dstz.bpm.plugin.task.reminders.def.RemindersPluginDef;
/*    */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemindersPlugin
/*    */   extends AbstractBpmTaskPlugin<BpmTaskPluginSession, RemindersPluginDef>
/*    */ {
/*    */   @Resource
/*    */   IGroovyScriptEngine groovyScriptEngine;
/*    */   
/*    */   public Void a(BpmTaskPluginSession pluginSession, RemindersPluginDef pluginDef)
/*    */   {
/* 33 */     if (pluginSession.getEventType() == EventType.TASK_COMPLETE_EVENT)
/*    */     {
/* 35 */       return null;
/*    */     }
/*    */     
/* 38 */     RemindersPluginDef reminderDef = pluginDef;
/* 39 */     List<Reminder> reminderList = reminderDef.getReminderList();
/* 40 */     for (Reminder reminder : reminderList) {
/* 41 */       a(reminder, pluginSession);
/*    */     }
/*    */     
/* 44 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */   private void a(Reminder reminder, BpmTaskPluginSession pluginSession)
/*    */   {
/* 50 */     String condition = reminder.getCondition();
/*    */     
/* 52 */     if (StringUtil.isNotEmpty(condition)) {
/* 53 */       Object object = null;
/* 54 */       if (((object instanceof Boolean)) && 
/* 55 */         (!((Boolean)object).booleanValue())) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\reminders\plugin\RemindersPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */