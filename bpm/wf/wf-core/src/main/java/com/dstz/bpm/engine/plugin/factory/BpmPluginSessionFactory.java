/*    */ package com.dstz.bpm.engine.plugin.factory;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmExecutionPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmTaskPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmUserCalcPluginSession;
/*    */ 
/*    */ 
/*    */ public class BpmPluginSessionFactory
/*    */ {
/*    */   public static BpmExecutionPluginSession buildExecutionPluginSession(InstanceActionCmd actionModel, EventType eventType)
/*    */   {
/* 23 */     DefaultBpmExecutionPluginSession executionPluginSession = new DefaultBpmExecutionPluginSession();
/* 24 */     executionPluginSession.setBoDatas(actionModel.getBizDataMap());
/* 25 */     executionPluginSession.setBpmInstance(actionModel.getBpmInstance());
/* 26 */     executionPluginSession.setEventType(eventType);
/* 27 */     executionPluginSession.setVariableScope(((DefaultInstanceActionCmd)actionModel).getExecutionEntity());
/* 28 */     return executionPluginSession;
/*    */   }
/*    */   
/*    */   public static BpmTaskPluginSession buildTaskPluginSession(TaskActionCmd actionModel, EventType eventType) {
/* 32 */     DefualtTaskActionCmd taskActionModel = (DefualtTaskActionCmd)actionModel;
/*    */     
/* 34 */     DefaultBpmTaskPluginSession session = new DefaultBpmTaskPluginSession();
/* 35 */     session.setBoDatas(actionModel.getBizDataMap());
/* 36 */     session.setBpmInstance(actionModel.getBpmInstance());
/* 37 */     session.setEventType(eventType);
/* 38 */     session.setVariableScope(taskActionModel.getDelagateTask());
/* 39 */     session.setBpmTask(taskActionModel.getBpmTask());
/* 40 */     return session;
/*    */   }
/*    */   
/*    */   public static DefaultBpmExecutionPluginSession buildExecutionPluginSession(TaskActionCmd actionModel, EventType eventType)
/*    */   {
/* 45 */     DefualtTaskActionCmd taskActionModel = (DefualtTaskActionCmd)actionModel;
/*    */     
/* 47 */     DefaultBpmExecutionPluginSession executionPluginSession = new DefaultBpmExecutionPluginSession();
/*    */     
/* 49 */     executionPluginSession.setBoDatas(actionModel.getBizDataMap());
/* 50 */     executionPluginSession.setBpmInstance(actionModel.getBpmInstance());
/* 51 */     executionPluginSession.setVariableScope(taskActionModel.getDelagateTask());
/* 52 */     executionPluginSession.setEventType(eventType);
/* 53 */     return executionPluginSession;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static BpmUserCalcPluginSession buildBpmUserCalcPluginSession(BpmPluginSession pluginSession)
/*    */   {
/* 63 */     DefaultBpmExecutionPluginSession plugin = (DefaultBpmExecutionPluginSession)pluginSession;
/*    */     
/* 65 */     DefaultBpmUserCalcPluginSession userCalcPluginSession = new DefaultBpmUserCalcPluginSession();
/* 66 */     userCalcPluginSession.setBoDatas(pluginSession.getBoDatas());
/* 67 */     userCalcPluginSession.setVariableScope(plugin.getVariableScope());
/*    */     
/* 69 */     if ((pluginSession instanceof BpmTaskPluginSession)) {
/* 70 */       BpmTaskPluginSession taskSession = (BpmTaskPluginSession)pluginSession;
/*    */       
/* 72 */       userCalcPluginSession.setBpmTask(taskSession.getBpmTask());
/*    */     }
/* 74 */     ActionCmd action = BpmContext.getActionModel();
/* 75 */     if ((action != null) && ((action instanceof TaskActionCmd)))
/*    */     {
/* 77 */       TaskActionCmd taskCmd = (TaskActionCmd)action;
/* 78 */       userCalcPluginSession.setBpmTask(taskCmd.getBpmTask());
/*    */     }
/*    */     
/* 81 */     return userCalcPluginSession;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\factory\BpmPluginSessionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */