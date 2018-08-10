 package com.dstz.bpm.engine.action.handler.task;
 
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.bpm.act.service.ActTaskService;
 import com.dstz.bpm.api.constant.EventType;
 import com.dstz.bpm.api.constant.NodeType;
 import com.dstz.bpm.api.engine.plugin.cmd.TaskCommand;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.api.model.inst.IBpmInstance;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.core.manager.BpmInstanceManager;
 import com.dstz.bpm.core.manager.BpmTaskManager;
 import com.dstz.bpm.core.model.BpmTask;
 import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
 import com.dstz.bpm.engine.action.handler.AbsActionHandler;
 import javax.annotation.Resource;
 
 public abstract class AbstractTaskActionHandler<T extends DefualtTaskActionCmd> extends AbsActionHandler<T>
 {
   @Resource
   protected ActTaskService ax;
   @Resource
   protected BpmTaskManager ay;
   @Resource
   protected TaskCommand az;
   
   public void a(T actionModel)
   {
     BpmTask bpmTask = (BpmTask)actionModel.getBpmTask();
     
     String taskId = bpmTask.getTaskId();
     
     String destinationNode = bpmTask.getBackNode();
     if (StringUtil.isEmpty(destinationNode)) {
       destinationNode = actionModel.getDestination();
     }
     
     if (StringUtil.isEmpty(destinationNode)) {
       this.ax.completeTask(taskId);
     }
     else
     {
       this.ax.completeTask(taskId, new String[] { destinationNode });
     }
   }
   
 
   protected void b(T data)
   {
     if (data.getBpmTask() != null) { return;
     }
     BpmTask task = (BpmTask)this.ay.get(data.getTaskId());
     if (task == null) {
       throw new BusinessException(BpmStatusCode.TASK_NOT_FOUND);
     }
     
     data.setBpmTask(task);
     data.setDefId(task.getDefId());
     data.setBpmInstance((IBpmInstance)this.f.get(task.getInstId()));
     
     l(data);
     
     a(data, this.a.getBpmNodeDef(task.getDefId(), task.getNodeId()));
   }
   
   public Boolean isSupport(BpmNodeDef nodeDef)
   {
     NodeType nodeType = nodeDef.getType();
     
     if ((nodeType == NodeType.USERTASK) || (nodeType == NodeType.SIGNTASK)) {
       return Boolean.valueOf(true);
     }
     
     return Boolean.valueOf(false);
   }
   
   protected void c(DefualtTaskActionCmd actionModel) {
     this.az.execute(EventType.TASK_PRE_COMPLETE_EVENT, actionModel);
   }
   
 
 
 
 
   public String getDefaultGroovyScript()
   {
     return "";
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\action\handler\task\AbstractTaskActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */