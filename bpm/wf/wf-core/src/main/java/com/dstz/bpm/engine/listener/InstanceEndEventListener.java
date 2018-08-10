 package com.dstz.bpm.engine.listener;
 
 import com.dstz.bpm.api.constant.EventType;
 import com.dstz.bpm.api.constant.InstanceStatus;
 import com.dstz.bpm.api.constant.ScriptType;
 import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
 import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
 import com.dstz.bpm.api.engine.context.BpmContext;
 import com.dstz.bpm.api.model.inst.IBpmInstance;
 import com.dstz.bpm.core.manager.BpmInstanceManager;
 import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
 import com.dstz.bpm.core.manager.BpmTaskStackManager;
 import com.dstz.bpm.core.model.BpmInstance;
 import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
 import java.util.Date;
 import javax.annotation.Resource;
 import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
 import org.slf4j.Logger;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 @Component
 public class InstanceEndEventListener
   extends AbstractInstanceListener
 {
   @Resource
   BpmTaskOpinionManager aO;
   @Resource
   BpmInstanceManager f;
   @Resource
   BpmTaskStackManager aA;
   
   public EventType getBeforeTriggerEventType()
   {
     return EventType.END_EVENT;
   }
   
   public EventType getAfterTriggerEventType()
   {
     return EventType.END_POST_EVENT;
   }
   
   public void a(InstanceActionCmd instanceActionModel)
   {
     this.LOG.debug("流程实例【{}】,ID【{}】开始触发终止事件", instanceActionModel.getBpmInstance().getSubject(), instanceActionModel.getBpmInstance().getId());
   }
   
   public void b(InstanceActionCmd instanceActionModel)
   {
     this.aO.createOpinionByInstance(instanceActionModel.getBpmInstance(), instanceActionModel.getFormId(), false);
     
 
     BpmInstance instance = (BpmInstance)instanceActionModel.getBpmInstance();
     instance.setStatus(InstanceStatus.STATUS_END.getKey());
     instance.setEndTime(new Date());
     instance.setDuration(Long.valueOf(instance.getEndTime().getTime() - instance.getCreateTime().getTime()));
     this.f.update(instance);
   }
   
 
 
   public void c(InstanceActionCmd instanceActionModel)
   {
     this.aA.removeByInstanceId(instanceActionModel.getInstanceId());
     this.LOG.debug("流程实例【{}】,ID【{}】已经终止", instanceActionModel.getBpmInstance().getSubject(), instanceActionModel.getBpmInstance().getId());
   }
   
   protected ScriptType getScriptType()
   {
     return ScriptType.END;
   }
   
   protected InstanceActionCmd a(ExecutionEntity excutionEntity)
   {
     BaseActionCmd actionModle = (BaseActionCmd)BpmContext.getActionModel();
     
 
     DefaultInstanceActionCmd instanceModel = new DefaultInstanceActionCmd();
     instanceModel.setBpmInstance(actionModle.getBpmInstance());
     instanceModel.setBpmDefinition(actionModle.getBpmDefinition());
     instanceModel.setBizDataMap(actionModle.getBizDataMap());
     instanceModel.setBusinessKey(actionModle.getBusinessKey());
     instanceModel.setActionName("end");
     return instanceModel;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\InstanceEndEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */