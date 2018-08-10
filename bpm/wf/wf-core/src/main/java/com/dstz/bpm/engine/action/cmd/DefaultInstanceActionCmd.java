/*    */ package com.dstz.bpm.engine.action.cmd;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.bpm.api.exception.WorkFlowException;
/*    */ import com.dstz.bpm.api.model.def.IBpmDefinition;
/*    */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*    */ import java.util.Map;
/*    */ import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultInstanceActionCmd
/*    */   extends BaseActionCmd
/*    */   implements InstanceActionCmd
/*    */ {
/*    */   protected ExecutionEntity an;
/*    */   
/*    */   public DefaultInstanceActionCmd(String flowParam)
/*    */   {
/* 23 */     super(flowParam);
/*    */   }
/*    */   
/*    */ 
/*    */   public DefaultInstanceActionCmd() {}
/*    */   
/*    */   public String getFlowKey()
/*    */   {
/* 31 */     return getBpmDefinition().getKey();
/*    */   }
/*    */   
/*    */   public String getSubject()
/*    */   {
/* 36 */     return getBpmInstance().getSubject();
/*    */   }
/*    */   
/*    */   public ExecutionEntity getExecutionEntity()
/*    */   {
/* 41 */     return this.an;
/*    */   }
/*    */   
/*    */   public void setExecutionEntity(ExecutionEntity executionEntity) {
/* 45 */     this.an = executionEntity;
/*    */   }
/*    */   
/*    */   public Object getVariable(String variableName)
/*    */   {
/* 50 */     if (this.an == null) {
/* 51 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*    */     }
/* 53 */     return this.an.getVariable(variableName);
/*    */   }
/*    */   
/*    */   public boolean hasVariable(String variableName)
/*    */   {
/* 58 */     if (this.an == null) {
/* 59 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*    */     }
/* 61 */     return this.an.hasVariable(variableName);
/*    */   }
/*    */   
/*    */   public void removeVariable(String variableName)
/*    */   {
/* 66 */     if (this.an == null) {
/* 67 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*    */     }
/*    */     
/* 70 */     this.an.removeVariable(variableName);
/*    */   }
/*    */   
/*    */   public void addVariable(String name, Object value)
/*    */   {
/* 75 */     if (this.an == null) {
/* 76 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*    */     }
/* 78 */     this.an.setVariable(name, value);
/*    */   }
/*    */   
/*    */   public Map<String, Object> getVariables()
/*    */   {
/* 83 */     return this.an.getVariables();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void initSpecialParam(JSONObject flowParam) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public String getNodeId()
/*    */   {
/* 94 */     return this.an.getActivityId();
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\cmd\DefaultInstanceActionCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */