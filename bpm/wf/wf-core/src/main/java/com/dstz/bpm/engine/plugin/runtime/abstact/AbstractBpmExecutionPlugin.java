 package com.dstz.bpm.engine.plugin.runtime.abstact;
 
 import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
 import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
 import com.dstz.bpm.api.engine.context.BpmContext;
 import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
 import com.dstz.bpm.api.model.task.IBpmTask;
 import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
 import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
 import org.activiti.engine.delegate.VariableScope;
 import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
 import org.activiti.engine.impl.persistence.entity.TaskEntity;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 public abstract class AbstractBpmExecutionPlugin<S extends BpmExecutionPluginSession, M extends BpmExecutionPluginDef>
   implements BpmExecutionPlugin<S, M>
 {
   protected Logger LOG = LoggerFactory.getLogger(getClass());
   
   protected boolean isTask() {
     ActionCmd actionCmd = BpmContext.getActionModel();
     
     if ((actionCmd instanceof TaskActionCmd)) {
       return true;
     }
     
     return false;
   }
   
   protected IBpmTask getTaskIfTask() {
     ActionCmd actionCmd = BpmContext.getActionModel();
     
     if ((actionCmd instanceof TaskActionCmd)) {
       TaskActionCmd taskCmd = (TaskActionCmd)actionCmd;
       return taskCmd.getBpmTask();
     }
     
     return null;
   }
   
   protected String getActivitiId(BpmExecutionPluginSession session) {
     VariableScope scope = session.getVariableScope();
     
 
     if ((scope instanceof ExecutionEntity)) {
       ExecutionEntity execution = (ExecutionEntity)scope;
       return execution.getActivityId(); }
     if ((scope instanceof TaskEntity)) {
       TaskEntity task = (TaskEntity)scope;
       return task.getTaskDefinitionKey();
     }
     
     return null;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\runtime\abstact\AbstractBpmExecutionPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */