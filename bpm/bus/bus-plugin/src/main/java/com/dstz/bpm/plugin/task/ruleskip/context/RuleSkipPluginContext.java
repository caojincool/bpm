/*    */ package com.dstz.bpm.plugin.task.ruleskip.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractBpmTaskPluginContext;
/*    */ import com.dstz.bpm.plugin.task.ruleskip.def.JumpRule;
/*    */ import com.dstz.bpm.plugin.task.ruleskip.def.RuleSkipPluginDef;
/*    */ import com.dstz.bpm.plugin.task.ruleskip.plugin.RuleSkipPlugin;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public class RuleSkipPluginContext
/*    */   extends AbstractBpmTaskPluginContext<RuleSkipPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = 8784633971785686365L;
/*    */   
/*    */   public List getEventTypes()
/*    */   {
/* 24 */     List<EventType> eventTypes = new ArrayList();
/* 25 */     eventTypes.add(EventType.TASK_PRE_COMPLETE_EVENT);
/* 26 */     return eventTypes;
/*    */   }
/*    */   
/*    */   public Class<? extends RunTimePlugin> getPluginClass()
/*    */   {
/* 31 */     return RuleSkipPlugin.class;
/*    */   }
/*    */   
/*    */   public String getTitle()
/*    */   {
/* 36 */     return "任务消息插件";
/*    */   }
/*    */   
/*    */   protected RuleSkipPluginDef e(JSON json)
/*    */   {
/* 41 */     RuleSkipPluginDef def = new RuleSkipPluginDef();
/*    */     
/* 43 */     List<JumpRule> list = JSON.parseArray(json.toJSONString(), JumpRule.class);
/* 44 */     def.setJumpRules(list);
/* 45 */     return def;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-plugin\1.1.5\wf-plugin-1.1.5-pg.jar!\com\dstz\bpm\plugin\task\ruleskip\context\RuleSkipPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */