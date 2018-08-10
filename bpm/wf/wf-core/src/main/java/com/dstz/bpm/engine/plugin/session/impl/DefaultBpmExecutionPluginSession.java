/*    */ package com.dstz.bpm.engine.plugin.session.impl;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import com.dstz.bus.api.model.IBusinessData;
/*    */ import com.dstz.sys.util.ContextUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.activiti.engine.delegate.VariableScope;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultBpmExecutionPluginSession
/*    */   extends HashMap<String, Object>
/*    */   implements BpmExecutionPluginSession
/*    */ {
/*    */   private static final long serialVersionUID = 4225343560381914372L;
/*    */   private Map<String, IBusinessData> bM;
/*    */   private EventType bN;
/*    */   private VariableScope variableScope;
/*    */   private IBpmInstance bpmInstance;
/*    */   
/*    */   public DefaultBpmExecutionPluginSession()
/*    */   {
/* 33 */     BaseActionCmd cmd = (BaseActionCmd)BpmContext.submitActionModel();
/* 34 */     ActionType actionType = cmd.getActionType();
/* 35 */     put("submitActionDesc", actionType.getName());
/* 36 */     put("submitActionName", actionType.getKey());
/*    */     
/* 38 */     if ((cmd instanceof DefualtTaskActionCmd)) {
/* 39 */       DefualtTaskActionCmd taskCmd = (DefualtTaskActionCmd)cmd;
/* 40 */       put("isTask", Boolean.valueOf(true));
/* 41 */       put("submitOpinion", taskCmd.getOpinion());
/* 42 */       put("currentUser", ContextUtil.getCurrentUser());
/* 43 */       put("submitTaskname", taskCmd.getBpmTask().getName());
/*    */     }
/*    */   }
/*    */   
/*    */   public Map<String, IBusinessData> getBoDatas()
/*    */   {
/* 49 */     return this.bM;
/*    */   }
/*    */   
/*    */   public void setBoDatas(Map<String, IBusinessData> boDatas) {
/* 53 */     putAll(boDatas);
/* 54 */     this.bM = boDatas;
/*    */   }
/*    */   
/*    */   public IBpmInstance getBpmInstance() {
/* 58 */     return this.bpmInstance;
/*    */   }
/*    */   
/*    */   public void setBpmInstance(IBpmInstance bpmInstance) {
/* 62 */     put("bpmInstance", bpmInstance);
/* 63 */     this.bpmInstance = bpmInstance;
/*    */   }
/*    */   
/*    */   public EventType getEventType()
/*    */   {
/* 68 */     return this.bN;
/*    */   }
/*    */   
/*    */   public void setEventType(EventType eventType) {
/* 72 */     put("eventType", eventType.getKey());
/* 73 */     this.bN = eventType;
/*    */   }
/*    */   
/*    */   public VariableScope getVariableScope() {
/* 77 */     return this.variableScope;
/*    */   }
/*    */   
/*    */   public void setVariableScope(VariableScope variableScope) {
/* 81 */     put("variableScope", variableScope);
/* 82 */     this.variableScope = variableScope;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\session\impl\DefaultBpmExecutionPluginSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */