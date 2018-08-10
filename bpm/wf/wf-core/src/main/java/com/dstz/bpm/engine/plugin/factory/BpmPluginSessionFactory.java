 package com.dstz.bpm.engine.plugin.factory;
 
 import com.dstz.bpm.api.constant.EventType;
 import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
 import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
 import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
 import com.dstz.bpm.api.engine.context.BpmContext;
 import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
 import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
 import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
 import com.dstz.bpm.engine.plugin.session.BpmPluginSession;
 import com.dstz.bpm.engine.plugin.session.BpmTaskPluginSession;
 import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
 import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmExecutionPluginSession;
 import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmTaskPluginSession;
 import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmUserCalcPluginSession;
 
 
 public class BpmPluginSessionFactory
 {
   public static BpmExecutionPluginSession buildExecutionPluginSession(InstanceActionCmd actionModel, EventType eventType)
   {
     DefaultBpmExecutionPluginSession executionPluginSession = new DefaultBpmExecutionPluginSession();
     executionPluginSession.setBoDatas(actionModel.getBizDataMap());
     executionPluginSession.setBpmInstance(actionModel.getBpmInstance());
     executionPluginSession.setEventType(eventType);
     executionPluginSession.setVariableScope(((DefaultInstanceActionCmd)actionModel).getExecutionEntity());
     return executionPluginSession;
   }
   
   public static BpmTaskPluginSession buildTaskPluginSession(TaskActionCmd actionModel, EventType eventType) {
     DefualtTaskActionCmd taskActionModel = (DefualtTaskActionCmd)actionModel;
     
     DefaultBpmTaskPluginSession session = new DefaultBpmTaskPluginSession();
     session.setBoDatas(actionModel.getBizDataMap());
     session.setBpmInstance(actionModel.getBpmInstance());
     session.setEventType(eventType);
     session.setVariableScope(taskActionModel.getDelagateTask());
     session.setBpmTask(taskActionModel.getBpmTask());
     return session;
   }
   
   public static DefaultBpmExecutionPluginSession buildExecutionPluginSession(TaskActionCmd actionModel, EventType eventType)
   {
     DefualtTaskActionCmd taskActionModel = (DefualtTaskActionCmd)actionModel;
     
     DefaultBpmExecutionPluginSession executionPluginSession = new DefaultBpmExecutionPluginSession();
     
     executionPluginSession.setBoDatas(actionModel.getBizDataMap());
     executionPluginSession.setBpmInstance(actionModel.getBpmInstance());
     executionPluginSession.setVariableScope(taskActionModel.getDelagateTask());
     executionPluginSession.setEventType(eventType);
     return executionPluginSession;
   }
   
 
 
 
 
 
   public static BpmUserCalcPluginSession buildBpmUserCalcPluginSession(BpmPluginSession pluginSession)
   {
     DefaultBpmExecutionPluginSession plugin = (DefaultBpmExecutionPluginSession)pluginSession;
     
     DefaultBpmUserCalcPluginSession userCalcPluginSession = new DefaultBpmUserCalcPluginSession();
     userCalcPluginSession.setBoDatas(pluginSession.getBoDatas());
     userCalcPluginSession.setVariableScope(plugin.getVariableScope());
     
     if ((pluginSession instanceof BpmTaskPluginSession)) {
       BpmTaskPluginSession taskSession = (BpmTaskPluginSession)pluginSession;
       
       userCalcPluginSession.setBpmTask(taskSession.getBpmTask());
     }
     ActionCmd action = BpmContext.getActionModel();
     if ((action != null) && ((action instanceof TaskActionCmd)))
     {
       TaskActionCmd taskCmd = (TaskActionCmd)action;
       userCalcPluginSession.setBpmTask(taskCmd.getBpmTask());
     }
     
     return userCalcPluginSession;
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\plugin\factory\BpmPluginSessionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */