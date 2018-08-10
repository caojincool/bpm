/*    */ package com.dstz.bpm.plugin.execution.taskskip.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractBpmExecutionPluginContext;
/*    */ import com.dstz.bpm.plugin.execution.taskskip.def.TaskSkipPluginDef;
/*    */ import com.dstz.bpm.plugin.execution.taskskip.plugin.TaskSkipPlugin;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public class TaskSkipPluginContext
/*    */   extends AbstractBpmExecutionPluginContext<TaskSkipPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = -8171025388788811778L;
/*    */   
/*    */   public List<EventType> getEventTypes()
/*    */   {
/* 25 */     List<EventType> list = new ArrayList();
/* 26 */     list.add(EventType.TASK_POST_CREATE_EVENT);
/* 27 */     return list;
/*    */   }
/*    */   
/*    */   public Class<? extends RunTimePlugin> getPluginClass()
/*    */   {
/* 32 */     return TaskSkipPlugin.class;
/*    */   }
/*    */   
/*    */ 
/*    */   protected TaskSkipPluginDef c(JSON pluginJson)
/*    */   {
/* 38 */     TaskSkipPluginDef def = (TaskSkipPluginDef)JSON.toJavaObject(pluginJson, TaskSkipPluginDef.class);
/* 39 */     return def;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getTitle()
/*    */   {
/* 45 */     return "任务跳过";
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\execution\taskskip\context\TaskSkipPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */