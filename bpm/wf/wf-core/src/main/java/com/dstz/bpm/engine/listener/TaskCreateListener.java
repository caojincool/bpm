 package com.dstz.bpm.engine.listener;
 
 import com.dstz.base.api.exception.BusinessException;
 import com.dstz.base.core.util.BeanUtils;
 import com.dstz.base.core.util.StringUtil;
 import com.dstz.bpm.api.constant.ActionType;
 import com.dstz.bpm.api.constant.EventType;
 import com.dstz.bpm.api.constant.NodeType;
 import com.dstz.bpm.api.constant.ScriptType;
 import com.dstz.bpm.api.constant.TaskStatus;
 import com.dstz.bpm.api.constant.TaskType;
 import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
 import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
 import com.dstz.bpm.api.engine.context.BpmContext;
 import com.dstz.bpm.api.exception.BpmStatusCode;
 import com.dstz.bpm.api.exception.WorkFlowException;
 import com.dstz.bpm.api.model.def.NodeProperties;
 import com.dstz.bpm.api.model.form.BpmForm;
 import com.dstz.bpm.api.model.inst.IBpmInstance;
 import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
 import com.dstz.bpm.api.model.task.IBpmTask;
 import com.dstz.bpm.api.service.BpmProcessDefService;
 import com.dstz.bpm.core.manager.BpmTaskManager;
 import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
 import com.dstz.bpm.core.manager.BpmTaskStackManager;
 import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
 import com.dstz.bpm.core.model.BpmTask;
 import com.dstz.bpm.core.model.BpmTaskStack;
 import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
 import com.dstz.sys.api.model.SysIdentity;
 import java.util.ArrayList;
 import java.util.List;
 import javax.annotation.Resource;
 import org.activiti.engine.impl.persistence.entity.TaskEntity;
 import org.slf4j.Logger;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 @Component
 public class TaskCreateListener
   extends AbstractTaskListener<DefualtTaskActionCmd>
 {
   private static final long serialVersionUID = -7836822392037648008L;
   @Resource
   private BpmTaskManager aQ;
   @Resource
   private BpmProcessDefService a;
   @Resource
   private BpmTaskOpinionManager aO;
   @Resource
   private BpmTaskStackManager aR;
   @Resource
   private TaskIdentityLinkManager i;
   
   public EventType getBeforeTriggerEventType()
   {
     return EventType.TASK_CREATE_EVENT;
   }
   
   public EventType getAfterTriggerEventType()
   {
     return EventType.TASK_POST_CREATE_EVENT;
   }
   
   public void g(DefualtTaskActionCmd taskActionModel)
   {
     this.LOG.debug("任务【{}】执行创建过程 - taskID: {}", taskActionModel.getBpmTask().getName(), taskActionModel.getBpmTask().getId());
   }
   
 
   public void h(DefualtTaskActionCmd taskActionModel)
   {
     IBpmTask task = taskActionModel.getBpmTask();
     
     d(taskActionModel);
     
     this.aQ.create((BpmTask)task);
     
     this.aO.createOpinionByTask(taskActionModel);
     
     BpmTaskStack stack = this.aR.createStackByTask(task, taskActionModel.getParentTaskStack());
     taskActionModel.setTaskStack(stack);
   }
   
 
 
 
 
   public void i(DefualtTaskActionCmd taskActionModel) {}
   
 
 
 
   protected ScriptType getScriptType()
   {
     return ScriptType.CREATE;
   }
   
 
 
 
   private void d(TaskActionCmd taskActionModel)
   {
     IBpmTask bpmTask = taskActionModel.getBpmTask();
     
     List<SysIdentity> identityList = taskActionModel.getBpmIdentity(bpmTask.getNodeId());
     BpmNodeDef nodeDef = this.a.getBpmNodeDef(bpmTask.getDefId(), bpmTask.getNodeId());
     
     if ((!nodeDef.getNodeProperties().isAllowExecutorEmpty()) && (BeanUtils.isEmpty(identityList))) {
       throw new WorkFlowException(bpmTask.getNodeId() + "任务候选人为空", BpmStatusCode.NO_ASSIGN_USER);
     }
     
     if (BeanUtils.isEmpty(identityList)) { return;
     }
     
 
     SysIdentity firstIdentity = (SysIdentity)identityList.get(0);
     
     if ((identityList.size() == 1) && (firstIdentity.getType().equals("user"))) {
       bpmTask.setAssigneeId(firstIdentity.getId());
       bpmTask.setAssigneeNames(firstIdentity.getName());
     } else {
       bpmTask.setAssigneeId("0");
       
       List<String> names = new ArrayList();
       for (SysIdentity identity : identityList) {
         names.add(identity.getName());
       }
       
       bpmTask.setAssigneeNames(StringUtil.convertCollectionAsString(names));
     }
     
     this.i.createIdentityLink(bpmTask, identityList);
   }
   
 
 
 
 
   public DefualtTaskActionCmd b(TaskEntity taskEntity)
   {
     BaseActionCmd model = (BaseActionCmd)BpmContext.getActionModel();
     if (!taskEntity.getProcessInstanceId().equals(model.getBpmInstance().getActInstId())) {
       throw new BusinessException("数据异常，actioncdm 与 TaskEntity数据不一致，请检查！");
     }
     
 
     BpmTask task = a(taskEntity, model.getBpmInstance());
     
     DefualtTaskActionCmd createModel = new DefualtTaskActionCmd();
     createModel.setBpmInstance(model.getBpmInstance());
     createModel.setBpmDefinition(model.getBpmDefinition());
     createModel.setBizDataMap(model.getBizDataMap());
     createModel.setBpmIdentities(model.getBpmIdentities());
     createModel.setBusinessKey(model.getBusinessKey());
     createModel.setActionName(ActionType.CREATE.getKey());
     
     createModel.setBpmTask(task);
     createModel.setDelagateTask(taskEntity);
     
     if ((model instanceof DefualtTaskActionCmd)) {
       createModel.setParentTaskStack(((DefualtTaskActionCmd)model).getTaskStack());
     }
     
     BpmContext.setActionModel(createModel);
     
     return createModel;
   }
   
   private BpmTask a(TaskEntity taskEntity, IBpmInstance iBpmInstance)
   {
     BpmNodeDef nodeDef = this.a.getBpmNodeDef(iBpmInstance.getDefId(), taskEntity.getTaskDefinitionKey());
     int isSupportMobile = 1;
     if ((nodeDef.getMobileForm() != null) && (nodeDef.getMobileForm().isFormEmpty())) {
       isSupportMobile = 0;
     }
     
     BpmTask task = new BpmTask();
     task.setActExecutionId(taskEntity.getExecutionId());
     task.setActInstId(taskEntity.getProcessInstanceId());
     task.setDefId(iBpmInstance.getDefId());
     task.setId(taskEntity.getId());
     task.setInstId(iBpmInstance.getId());
     task.setName(taskEntity.getName());
     task.setNodeId(taskEntity.getTaskDefinitionKey());
     task.setParentId("0");
     task.setPriority(Integer.valueOf(taskEntity.getPriority()));
     task.setStatus(TaskType.NORMAL.getKey());
     task.setTaskType(a(nodeDef.getType()));
     task.setSubject(iBpmInstance.getSubject());
     task.setSupportMobile(Integer.valueOf(isSupportMobile));
     task.setStatus(TaskStatus.NORMAL.getKey());
     task.setTaskId(taskEntity.getId());
     task.setTypeId(iBpmInstance.getTypeId());
     
     return task;
   }
   
   private String a(NodeType type) {
     switch (1.aS[type.ordinal()]) {
     case 1:  return TaskType.SIGN.getKey();
     case 2: 
       return TaskType.SUBFLOW.getKey();
     case 3: 
       return TaskType.NORMAL.getKey();
     }
     return TaskType.NORMAL.getKey();
   }
 }


/* Location:              E:\repo\com\dstz\agilebpm\wf-core\1.1.5\wf-core-1.1.5-pg.jar!\com\dstz\bpm\engine\listener\TaskCreateListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */