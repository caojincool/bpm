/*    */ package com.dstz.bpm.engine.plugin.session.impl;
/*    */ 
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ 
/*    */ public class DefaultBpmUserCalcPluginSession
/*    */   extends DefaultBpmExecutionPluginSession
/*    */   implements BpmUserCalcPluginSession
/*    */ {
/*    */   private static final long serialVersionUID = 1132300282829841447L;
/*    */   private IBpmTask ao;
/* 12 */   private Boolean bO = Boolean.valueOf(false);
/*    */   
/*    */   public IBpmTask getBpmTask() {
/* 15 */     return this.ao;
/*    */   }
/*    */   
/*    */   public void setBpmTask(IBpmTask bpmTask) {
/* 19 */     put("bpmTask", bpmTask);
/* 20 */     this.ao = bpmTask;
/*    */   }
/*    */   
/*    */   public Boolean isPreVrewModel()
/*    */   {
/* 25 */     return this.bO;
/*    */   }
/*    */   
/*    */   public void setIsPreVrewModel(Boolean isPreVrewModel) {
/* 29 */     this.bO = isPreVrewModel;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\session\impl\DefaultBpmUserCalcPluginSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */