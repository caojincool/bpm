/*    */ package com.dstz.bpm.engine.plugin.session.impl;
/*    */ 
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
/*    */ 
/*    */ public class DefaultBpmTaskPluginSession extends DefaultBpmExecutionPluginSession implements BpmTaskPluginSession
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private IBpmTask ao;
/*    */   
/*    */   public IBpmTask getBpmTask()
/*    */   {
/* 13 */     return this.ao;
/*    */   }
/*    */   
/*    */   public void setBpmTask(IBpmTask bpmTask) {
/* 17 */     put("bpmTask", bpmTask);
/* 18 */     this.ao = bpmTask;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\session\impl\DefaultBpmTaskPluginSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */