/*    */ package com.dstz.bpm.plugin.task.sign.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractBpmTaskPluginContext;
/*    */ import com.dstz.bpm.plugin.task.sign.def.SignTaskPluginDef;
/*    */ import com.dstz.bpm.plugin.task.sign.plugin.SignTaskPlugin;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public class SignTaskPluginContext
/*    */   extends AbstractBpmTaskPluginContext<SignTaskPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = 8784633971785686365L;
/*    */   
/*    */   public List getEventTypes()
/*    */   {
/* 38 */     List<EventType> eventTypes = new ArrayList();
/* 39 */     eventTypes.add(EventType.TASK_CREATE_EVENT);
/* 40 */     return eventTypes;
/*    */   }
/*    */   
/*    */   public Class<? extends RunTimePlugin> getPluginClass()
/*    */   {
/* 45 */     return SignTaskPlugin.class;
/*    */   }
/*    */   
/*    */   public String getTitle()
/*    */   {
/* 50 */     return "会签任务插件";
/*    */   }
/*    */   
/*    */   protected SignTaskPluginDef f(JSON json)
/*    */   {
/* 55 */     SignTaskPluginDef def = (SignTaskPluginDef)JSON.parseObject(json.toJSONString(), SignTaskPluginDef.class);
/* 56 */     return def;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\sign\context\SignTaskPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */