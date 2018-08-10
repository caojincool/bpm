/*    */ package com.dstz.bpm.engine.plugin.factory;
/*    */ 
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*    */ import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
/*    */ import com.dstz.bpm.engine.plugin.runtime.BpmTaskPlugin;
/*    */ import java.util.List;
/*    */ 
/*    */ public class BpmPluginFactory
/*    */ {
/*    */   public static BpmExecutionPlugin buildExecutionPlugin(BpmPluginContext bpmPluginContext, EventType eventType)
/*    */   {
/* 14 */     if (bpmPluginContext.getEventTypes().contains(eventType)) {
/*    */       try {
/* 16 */         return (BpmExecutionPlugin)AppUtil.getBean(bpmPluginContext.getPluginClass());
/*    */       }
/*    */       catch (Exception localException) {}
/*    */     }
/*    */     
/*    */ 
/* 22 */     return null;
/*    */   }
/*    */   
/*    */   public static BpmTaskPlugin buildTaskPlugin(BpmPluginContext bpmPluginContext, EventType eventType)
/*    */   {
/* 27 */     if (bpmPluginContext.getEventTypes().contains(eventType)) {
/* 28 */       return (BpmTaskPlugin)AppUtil.getBean(bpmPluginContext.getPluginClass());
/*    */     }
/* 30 */     return null;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\factory\BpmPluginFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */