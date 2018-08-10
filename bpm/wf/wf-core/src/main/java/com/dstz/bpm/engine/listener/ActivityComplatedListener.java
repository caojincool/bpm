 package com.dstz.bpm.engine.listener;
 
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.bpm.act.listener.ActEventListener;
 import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
 import com.dstz.bpm.api.engine.context.BpmContext;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.api.model.inst.IBpmInstance;
 import com.dstz.bpm.core.manager.BpmDefinitionManager;
 import com.dstz.bpm.core.manager.BpmInstanceManager;
 import com.dstz.bpm.core.manager.BpmTaskStackManager;
 import com.dstz.bpm.core.model.BpmDefinition;
 import com.dstz.bpm.core.model.BpmInstance;
 import com.dstz.bpm.core.model.BpmTaskStack;
 import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
 import java.util.Date;
 import javax.annotation.Resource;
 import org.activiti.engine.delegate.event.ActivitiEvent;
 import org.activiti.engine.delegate.event.impl.ActivitiActivityEventImpl;
 import org.springframework.stereotype.Component;
 
 @Component
 public class ActivityComplatedListener
   implements ActEventListener
 {
   @Resource
   private BpmInstanceManager aM;
   @Resource
   private BpmDefinitionManager c;
   @Resource
   private BpmTaskStackManager aA;
   
   public void notify(ActivitiEvent event)
   {
     if (!(event instanceof ActivitiActivityEventImpl)) { return;
     }
     
     ActivitiActivityEventImpl activitEvent = (ActivitiActivityEventImpl)event;
     
     if (activitEvent.getActivityType().equals("callActivity"))
     {
       a(activitEvent);
     }
   }
   
 
 
 
 
 
 
 
 
   private void a(ActivitiActivityEventImpl activitEvent)
   {
     BaseActionCmd actionCmd = (BaseActionCmd)BpmContext.getActionModel();
     IBpmInstance childInstance = actionCmd.getBpmInstance();
     
 
     if (StringUtil.isZeroEmpty(childInstance.getParentInstId())) {
       throw new BusinessException("子流程提供的线程变量中，流程实例信息异常！", BpmStatusCode.ACTIONCMD_ERROR);
     }
     
     BpmInstance bpmInstance = (BpmInstance)this.aM.get(childInstance.getParentInstId());
     if (!bpmInstance.getActInstId().equals(activitEvent.getProcessInstanceId())) {
       throw new BusinessException("子流程提供的父流程实例，与外部子流程ACTVITI actInstanceID 不一致！", BpmStatusCode.ACTIONCMD_ERROR);
     }
     
 
     BpmTaskStack bpmTaskStack = c(activitEvent.getExecutionId());
     
     BpmDefinition bpmDefinition = (BpmDefinition)this.c.get(bpmInstance.getDefId());
     
     DefualtTaskActionCmd callActiviti = new DefualtTaskActionCmd();
     callActiviti.setBpmDefinition(bpmDefinition);
     callActiviti.setBpmInstance(bpmInstance);
     callActiviti.setTaskStack(bpmTaskStack);
     
 
     BpmContext.setActionModel(callActiviti);
   }
   
 
   private BpmTaskStack c(String executionId)
   {
     BpmTaskStack bpmTaskStack = this.aA.getByTaskId(executionId);
     
     bpmTaskStack.setEndTime(new Date());
     this.aA.update(bpmTaskStack);
     return bpmTaskStack;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\ActivityComplatedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */