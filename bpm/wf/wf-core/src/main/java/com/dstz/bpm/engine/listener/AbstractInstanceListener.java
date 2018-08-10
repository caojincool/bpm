 package com.dstz.bpm.engine.listener;
 
 import com.dstz.bpm.act.listener.ActEventListener;
 import com.dstz.bpm.api.constant.EventType;
 import com.dstz.bpm.api.constant.ScriptType;
 import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
 import com.dstz.bpm.api.engine.plugin.cmd.ExecutionCommand;
 import javax.annotation.Resource;
 import org.activiti.engine.delegate.event.ActivitiEvent;
 import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
 import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 public abstract class AbstractInstanceListener
   implements ActEventListener
 {
   protected Logger LOG = LoggerFactory.getLogger(getClass());
   
 
 
 
   @Resource
   private ExecutionCommand aL;
   
 
 
 
 
   public abstract EventType getBeforeTriggerEventType();
   
 
 
 
 
   public abstract EventType getAfterTriggerEventType();
   
 
 
 
 
   public abstract void a(InstanceActionCmd paramInstanceActionCmd);
   
 
 
 
   public abstract void b(InstanceActionCmd paramInstanceActionCmd);
   
 
 
 
   public abstract void c(InstanceActionCmd paramInstanceActionCmd);
   
 
 
 
   public void notify(ActivitiEvent event)
   {
     ActivitiEntityEventImpl processEvent = (ActivitiEntityEventImpl)event;
     ExecutionEntity excutionEntity = (ExecutionEntity)processEvent.getEntity();
     InstanceActionCmd actionModel = a(excutionEntity);
     
 
 
     a(actionModel);
     
 
     this.LOG.debug("============【{}】插件执行开始=============", getBeforeTriggerEventType().getValue());
     if (getBeforeTriggerEventType() != null) {
       this.aL.execute(getBeforeTriggerEventType(), actionModel);
     }
     this.LOG.debug("============【{}】插件执行完毕============", getBeforeTriggerEventType().getValue());
     
 
     b(actionModel);
     
 
 
     this.LOG.debug("============【{}】插件执行开始=============", getAfterTriggerEventType().getValue());
     if (getAfterTriggerEventType() != null) {
       this.aL.execute(getAfterTriggerEventType(), actionModel);
     }
     this.LOG.debug("============【{}】插件执行完毕============", getAfterTriggerEventType().getValue());
     
     c(actionModel);
   }
   
   protected abstract InstanceActionCmd a(ExecutionEntity paramExecutionEntity);
   
   protected abstract ScriptType getScriptType();
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\AbstractInstanceListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */