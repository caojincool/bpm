 package com.dstz.bpm.engine.listener;
 
 import com.dstz.bpm.act.listener.ActEventListener;
 import com.dstz.bpm.api.engine.context.BpmContext;
 import com.dstz.bpm.core.manager.BpmTaskStackManager;
 import com.dstz.bpm.core.model.BpmTask;
 import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
 import javax.annotation.Resource;
 import org.activiti.engine.delegate.event.ActivitiEvent;
 import org.activiti.engine.delegate.event.impl.ActivitiActivityEventImpl;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 
 
 
 
 @Component
 public class ActivityStartedListener
   implements ActEventListener
 {
   @Resource
   BpmTaskStackManager aN;
   
   public void notify(ActivitiEvent event)
   {
     if (!(event instanceof ActivitiActivityEventImpl)) { return;
     }
     ActivitiActivityEventImpl activitEvent = (ActivitiActivityEventImpl)event;
     
     if (activitEvent.getActivityType().equals("callActivity")) {
       b(activitEvent);
     }
   }
   
 
   private void b(ActivitiActivityEventImpl activitEvent)
   {
     DefualtTaskActionCmd action = (DefualtTaskActionCmd)BpmContext.getActionModel();
     BpmTask task = new BpmTask();
     task.setId(activitEvent.getExecutionId());
     task.setName(activitEvent.getActivityName());
     task.setNodeId(activitEvent.getActivityId());
     task.setInstId(action.getInstanceId());
     
     this.aN.createStackByTask(task, action.getTaskStack());
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\ActivityStartedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */