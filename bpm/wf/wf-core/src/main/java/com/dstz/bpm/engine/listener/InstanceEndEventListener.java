/*    */ package com.dstz.bpm.engine.listener;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.constant.InstanceStatus;
/*    */ import com.dstz.bpm.api.constant.ScriptType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*    */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*    */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*    */ import com.dstz.bpm.core.manager.BpmTaskStackManager;
/*    */ import com.dstz.bpm.core.model.BpmInstance;
/*    */ import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
/*    */ import java.util.Date;
/*    */ import javax.annotation.Resource;
/*    */ import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
/*    */ import org.slf4j.Logger;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class InstanceEndEventListener
/*    */   extends AbstractInstanceListener
/*    */ {
/*    */   @Resource
/*    */   BpmTaskOpinionManager aO;
/*    */   @Resource
/*    */   BpmInstanceManager f;
/*    */   @Resource
/*    */   BpmTaskStackManager aA;
/*    */   
/*    */   public EventType getBeforeTriggerEventType()
/*    */   {
/* 38 */     return EventType.END_EVENT;
/*    */   }
/*    */   
/*    */   public EventType getAfterTriggerEventType()
/*    */   {
/* 43 */     return EventType.END_POST_EVENT;
/*    */   }
/*    */   
/*    */   public void a(InstanceActionCmd instanceActionModel)
/*    */   {
/* 48 */     this.LOG.debug("流程实例【{}】,ID【{}】开始触发终止事件", instanceActionModel.getBpmInstance().getSubject(), instanceActionModel.getBpmInstance().getId());
/*    */   }
/*    */   
/*    */   public void b(InstanceActionCmd instanceActionModel)
/*    */   {
/* 53 */     this.aO.createOpinionByInstance(instanceActionModel.getBpmInstance(), instanceActionModel.getFormId(), false);
/*    */     
/*    */ 
/* 56 */     BpmInstance instance = (BpmInstance)instanceActionModel.getBpmInstance();
/* 57 */     instance.setStatus(InstanceStatus.STATUS_END.getKey());
/* 58 */     instance.setEndTime(new Date());
/* 59 */     instance.setDuration(Long.valueOf(instance.getEndTime().getTime() - instance.getCreateTime().getTime()));
/* 60 */     this.f.update(instance);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void c(InstanceActionCmd instanceActionModel)
/*    */   {
/* 67 */     this.aA.removeByInstanceId(instanceActionModel.getInstanceId());
/* 68 */     this.LOG.debug("流程实例【{}】,ID【{}】已经终止", instanceActionModel.getBpmInstance().getSubject(), instanceActionModel.getBpmInstance().getId());
/*    */   }
/*    */   
/*    */   protected ScriptType getScriptType()
/*    */   {
/* 73 */     return ScriptType.END;
/*    */   }
/*    */   
/*    */   protected InstanceActionCmd a(ExecutionEntity excutionEntity)
/*    */   {
/* 78 */     BaseActionCmd actionModle = (BaseActionCmd)BpmContext.getActionModel();
/*    */     
/*    */ 
/* 81 */     DefaultInstanceActionCmd instanceModel = new DefaultInstanceActionCmd();
/* 82 */     instanceModel.setBpmInstance(actionModle.getBpmInstance());
/* 83 */     instanceModel.setBpmDefinition(actionModle.getBpmDefinition());
/* 84 */     instanceModel.setBizDataMap(actionModle.getBizDataMap());
/* 85 */     instanceModel.setBusinessKey(actionModle.getBusinessKey());
/* 86 */     instanceModel.setActionName("end");
/* 87 */     return instanceModel;
/*    */   }
/*    */ }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\InstanceEndEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */